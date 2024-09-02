package com.Scratch_Game.utils;

import com.Scratch_Game.model.Output;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IOUtils {

    private static final List<String> REGEX_LIST = new ArrayList<>() {{
        add("--(config)\\s+(\\w+.\\w+)");
        add("--(betting-amount)\\s+(\\d+)");
    }};

    private IOUtils() {
    }

    public static Map<String, Object> fillPropertiesByArgs(String input) {
        return REGEX_LIST
                .stream()
                .map(regex -> RegexUtils.getPropertyByRegex(input, regex))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void writeOutput(Output output) throws JsonProcessingException {
        String applied = "";
        String matrix = Stream
                .of(output.getMatrix())
                .map(line -> "[%s],".formatted(Stream.of(line).collect(Collectors.joining(", ", "\"", "\""))))
                .collect(Collectors.joining("\n"));

        if (!output.getReward().equals(0D)) {
            String adsasd = output.getApplied_winning_combinations()
                    .entrySet()
                    .stream()
                    .filter(winningCombination -> !winningCombination.getValue().isEmpty())
                    .map(winningCombination -> "\"%s\": [%s]".formatted(
                            winningCombination.getKey(),
                            winningCombination.getValue().stream().collect(Collectors.joining(",", "\"", "\""))
                    ))
                    .collect(Collectors.joining(",\n"));
            applied += """
                    ,
                    "applied_winning_combinations": {
                    %s
                    }"""
                    .formatted(adsasd);
            if (output.getApplied_bonus_symbol() != null) {
                applied += ",\n\"applied_bonus_symbol\": \"%s\"".formatted(output.getApplied_bonus_symbol());
            }
        }

        System.out.printf("""
                        {
                        "matrix": [
                        %s
                        ],
                        "reward": %f%s
                        }
                        """,
                matrix,
                output.getReward(),
                applied
        );
//        System.out.println(OBJECT_MAPPER.writeValueAsString(output));
    }

}
