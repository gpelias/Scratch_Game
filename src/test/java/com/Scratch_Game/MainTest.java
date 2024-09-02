package com.Scratch_Game;

import com.Scratch_Game.model.Config;
import com.Scratch_Game.model.Output;
import com.Scratch_Game.utils.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class MainTest {

    private final static int TIMES_TO_GENERATE_MATRIX = 100;
    private static Config config;
    private static Map<String, Object> properties;

    @BeforeAll
    public static void loadConfiguration() throws IOException {
        String[] args = new String[]{"--config", "config.json", "--betting-amount", "100"};
        String input = String.join(" ", args);

        properties = IOUtils.fillPropertiesByArgs(input);
        config = ConfigUtils.readConfigFile(properties.get("config").toString());
    }

    @Test
    void testResult2() {
        String[][] matrix = new String[][]{
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"},
        };
        Map<String, List<String>> expectedQuestionMap = new HashMap<>() {{
            put("B", List.of("same_symbol_3_times"));
        }};

        Output output = OutputUtils.createAndFillOutput(config, properties, matrix);
        LogicUtils.calculate(output, config);

        assertAll(
                "heading",
                () -> assertEquals(3000D, output.getReward()),
                () -> assertEquals("10x", output.getApplied_bonus_symbol()),
                new TestAppliedWinningCombination(output, expectedQuestionMap)
        );
    }

    @Test
    void testResult1() {
        String[][] matrix = new String[][]{
                {"A", "B", "C"},
                {"E", "B", "5x"},
                {"F", "D", "C"},
        };

        Output output = OutputUtils.createAndFillOutput(config, properties, matrix);

        LogicUtils.calculate(output, config);

        assertAll(
                "heading",
                () -> assertEquals(0D, output.getReward()),
                () -> assertEquals("5x", output.getApplied_bonus_symbol()),
                () -> assertNull(output.getApplied_winning_combinations())
        );
    }

    @Test
    void generateMatrix() {
        assertAll(
                "heading",
                IntStream.range(0, TIMES_TO_GENERATE_MATRIX).mapToObj(i -> new FillMatrix())
        );
    }

    public static class TestAppliedWinningCombination implements Executable {

        private final Output output;
        private final Map<String, List<String>> expectedQuestionMap;

        public TestAppliedWinningCombination(Output output, Map<String, List<String>> expectedQuestionMap) {
            this.output = output;
            this.expectedQuestionMap = expectedQuestionMap;
        }

        @Override
        public void execute() {
            for (Map.Entry<String, List<String>> entry : output.getApplied_winning_combinations().entrySet()) {
                assertReflectionEquals(expectedQuestionMap.get(entry.getKey()), entry.getValue(), ReflectionComparatorMode.LENIENT_ORDER);
            }
        }
    }

    public static class FillMatrix implements Executable {

        @Override
        public void execute() {
            String[][] matrix = MatrixUtils.createMatrix(config);
            MatrixUtils.fillMatrix(matrix, config);

            assertTrue(Stream.of(matrix).flatMap(Stream::of).allMatch(Objects::nonNull), "kra");
        }
    }
}