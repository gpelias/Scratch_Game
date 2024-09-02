package com.Scratch_Game.utils;

import com.Scratch_Game.model.Config;
import com.Scratch_Game.model.Output;

import java.util.Map;

public final class OutputUtils {

    private OutputUtils() {
    }

    public static Output createAndFillOutput(Config config, Map<String, Object> properties, String[][] matrix) {
        Output output = new Output();

        output.setMatrix(matrix);
        output.setReward(Double.parseDouble(properties.get("betting-amount").toString()));
        output.setApplied_winning_combinations(LogicUtils.fillWinningCombinations(config, matrix));
        output.setApplied_bonus_symbol(LogicUtils.fillBonusCombinations(config, matrix));

        return output;
    }
}
