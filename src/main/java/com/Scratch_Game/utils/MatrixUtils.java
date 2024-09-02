package com.Scratch_Game.utils;

import com.Scratch_Game.model.Config;
import com.Scratch_Game.model.StandardSymbol;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class MatrixUtils {

    private MatrixUtils() {
    }

    public static String[][] createMatrix(Config config) {
        String[][] matrix = new String[config.getRows()][];

        Arrays.setAll(matrix, i -> new String[config.getColumns()]);

        return matrix;
    }

    public static void fillMatrix(String[][] matrix, Config config) {
        fillWithStandardSymbols(matrix, config);

        addBonusSymbols(matrix, config);
    }

    private static void fillCell(String[][] matrix, StandardSymbol standardSymbol) {
        Integer row = standardSymbol.getRow();
        Integer col = standardSymbol.getColumn();

        matrix[row][col] = LogicUtils.chooseSymbolBasedOnProbability(LogicUtils.calculateProbabilities(standardSymbol.getSymbols()));
    }

    public static String getMatrixPosition(String[][] matrix, String position) {
        String[] positions = position.split(":");

        return matrix[Integer.parseInt(positions[0])][Integer.parseInt(positions[1])];
    }

    private static void fillWithStandardSymbols(String[][] matrix, Config config) {
        List<StandardSymbol> standardSymbols = config.getProbabilities().getStandard_symbols();

        Map<String, Integer> defaultSymbols = standardSymbols
                .stream()
                .filter(a -> a.isPosition(0, 0))
                .findFirst()
                .map(StandardSymbol::getSymbols)
                .orElseThrow();

        standardSymbols.forEach(standardSymbol -> fillCell(matrix, standardSymbol));

        fillMissConfigCell(matrix, defaultSymbols);
    }

    private static void fillMissConfigCell(String[][] matrix, Map<String, Integer> defaultSymbols) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null)
                    matrix[i][j] = LogicUtils.chooseSymbolBasedOnProbability(LogicUtils.calculateProbabilities(defaultSymbols));
            }
        }
    }

    private static void addBonusSymbols(String[][] matrix, Config config) {
        Map<String, Integer> bonusSymbols = config.getProbabilities().getBonus_symbols().getSymbols();
        int totalBonusWeights = bonusSymbols.values().stream().mapToInt(Integer::intValue).sum();

        // Calculate probabilities for bonus symbols
        Map<String, Double> bonusProbabilities = bonusSymbols
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (double) entry.getValue() / totalBonusWeights));

        // Loop through each cell to randomly place bonus symbols based on their probabilities
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (Math.random() < 0.1)
                    matrix[row][col] = LogicUtils.chooseSymbolBasedOnProbability(bonusProbabilities);
            }
        }
    }
}
