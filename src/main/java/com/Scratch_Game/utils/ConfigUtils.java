package com.Scratch_Game.utils;

import com.Scratch_Game.model.Config;

import java.io.File;
import java.io.IOException;

public final class ConfigUtils {

    private ConfigUtils() {
    }

    public static Config readConfigFile(String path) throws IOException {
        return ObjectMapperUtils.getObjectMapper().readValue(new File(path), Config.class);
    }

}
