package com.Scratch_Game.utils;

import com.Scratch_Game.model.Config;
import com.Scratch_Game.model.Output;
import com.Scratch_Game.model.Symbol;
import com.Scratch_Game.model.WinCombination;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LogicUtils {

    private LogicUtils() {
    }

    public static void calculate(Output output, Config config) {
        // (bet_amount x reward(symbol_A) x reward(same_symbol_5_times) x reward(same_symbols_vertically))
        // +
        // (bet_amount x reward(symbol_B) x reward(same_symbol_3_times) x reward(same_symbols_vertically))
        // (+/x)
        // reward(+1000)
        //
        // =
        // (100 x5 x5 x2)
        // + (100 x3 x1 x2)
        // + 1000
        //
        // =
        // 5000 + 600 + 1000
        //
        // =
        // 6600

        if (output.getApplied_winning_combinations() == null) {
            output.setReward(0D);
            return;
        }

        double reward = output.getApplied_winning_combinations()
                .entrySet()
                .stream()
                .mapToDouble(symbol -> {
                    Symbol symbolObject = config.getSymbols().get(symbol.getKey());
                    Double reward_multiplier = symbolObject.getReward_multiplier();

                    AtomicReference<Double> rewardTmp = new AtomicReference<>(output.getReward() * reward_multiplier);

                    symbol.getValue().forEach(symbolName -> rewardTmp.updateAndGet(value -> value * config.getWin_combinations().get(symbolName).getReward_multiplier()));

                    return rewardTmp.get();
                })
                .sum();

        output.setReward(reward);

        applyBonus(output, config);
    }

    public static void applyBonus(Output output, Config config) {
        if (output.getApplied_bonus_symbol() == null) return;

        Symbol symbol = config.getSymbols().get(output.getApplied_bonus_symbol());

        output.setReward(
                switch (symbol.getImpact()) {
                    case "extra_bonus" -> output.getReward() + symbol.getExtra();
                    case "multiply_reward" -> output.getReward() * symbol.getReward_multiplier();
                    default -> output.getReward();
                }
        );
    }

    public static String chooseSymbolBasedOnProbability(Map<String, Double> probabilities) {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (Map.Entry<String, Double> entry : probabilities.entrySet()) {
            cumulativeProbability += entry.getValue();

            if (randomValue <= cumulativeProbability) return entry.getKey();
        }

        return null;
    }

    public static Map<String, Double> calculateProbabilities(Map<String, Integer> symbols) {
        int sum = symbols.values().stream().mapToInt(Integer::intValue).sum();

        return symbols
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (double) entry.getValue() / sum));
    }

    public static Map<String, List<String>> fillWinningCombinations(Config config, String[][] matrix) {
        return mergeMapList(
                processSameSymbols(config, matrix),
                processLinearSymbols(config, matrix)
        );
    }

    public static String fillBonusCombinations(Config config, String[][] matrix) {
        Set<String> symbols = config.getSymbols()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getType().equals("bonus"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return Stream
                .of(matrix)
                .flatMap(Stream::of)
                .filter(symbol -> symbols.contains(symbol))
                .findAny()
                .orElse(null);
    }

    public static Map<String, List<String>> mergeMapList(Map<String, List<String>> a, Map<String, List<String>> b) {
        if (a == null) return null;

        return a.keySet()
                .stream()
                .map(key -> {
                    List<String> tmpList = new ArrayList<>();

                    if (a.containsKey(key) && !a.get(key).isEmpty()) tmpList.addAll(a.get(key));
                    if (b.containsKey(key) && !b.get(key).isEmpty()) tmpList.addAll(b.get(key));

                    return Map.entry(key, tmpList.stream().distinct().toList());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, List<String>> processSameSymbols(Config config, String[][] matrix) {
        Map<Long, String> countWinCombination = config.getWin_combinations()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWhen().equals("same_symbols"))
                .collect(Collectors.toMap(entry -> entry.getValue().getCount().longValue(), Map.Entry::getKey));

        Map<String, Long> symbolCount = Stream
                .of(matrix)
                .flatMap(Stream::of)
                .filter(symbol -> symbol.matches("^\\w$"))
                .collect(Collectors.groupingBy(value -> value, Collectors.counting()));

        Map<String, List<String>> nameSymbol = symbolCount
                .entrySet()
                .stream()
                .map(
                        entry -> Map.entry(
                                entry.getKey(),
                                countWinCombination.containsKey(entry.getValue()) ? List.of(countWinCombination.get(entry.getValue())) : new ArrayList<String>()
                        )
                )
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return nameSymbol.isEmpty() ? null : nameSymbol;
    }

    public static Map<String, List<String>> processLinearSymbols(Config config, String[][] matrix) {
        Map<String, List<String>> countWinCombination = new HashMap<>();

        List<String> symbols = config.getSymbols()
                .keySet()
                .stream()
                .filter(symbol -> symbol.matches("^\\w$"))
                .toList();

        Map<String, WinCombination> nameWinCombination = config.getWin_combinations()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWhen().equals("linear_symbols"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<String> winCombinationNames = nameWinCombination.keySet()
                .stream()
                .toList();

        for (String symbol : symbols) {
            countWinCombination.put(symbol, new ArrayList<>());

            for (String winCombinationName : winCombinationNames) {
                for (List<String> coveredArea : nameWinCombination.get(winCombinationName).getCovered_areas()) {
                    boolean validCombination = coveredArea
                            .stream()
                            .map(positions -> MatrixUtils.getMatrixPosition(matrix, positions))
                            .allMatch(value -> value.equals(symbol));

                    if (validCombination) countWinCombination.get(symbol).add(winCombinationName);
                }
            }
        }

        return countWinCombination
                .entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
