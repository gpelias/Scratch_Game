package com.Scratch_Game;

import com.Scratch_Game.model.Config;
import com.Scratch_Game.model.Output;
import com.Scratch_Game.utils.*;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String input = String.join(" ", args);

        Map<String, Object> properties = IOUtils.fillPropertiesByArgs(input);

        try {
            Config config = ConfigUtils.readConfigFile(properties.get("config").toString());

            String[][] matrix = MatrixUtils.createMatrix(config);

            MatrixUtils.fillMatrix(matrix, config);

            Output output = OutputUtils.createAndFillOutput(config, properties, matrix);

            LogicUtils.calculate(output, config);

            IOUtils.writeOutput(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}