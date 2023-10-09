package com.teampotato.enchantedpotato.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class MaxLevelConfig {
    public MaxLevelConfig() {
        File maxLevelConfig = new File(EarlySetupInitializer.potatoDir, "maxLevel.json");
        if (!maxLevelConfig.exists()) {
            try {
                FileWriter writer = writeFile(maxLevelConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create maxLevelConfig.json");
                System.exit(-1);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(maxLevelConfig))) {
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.errorSpore = configObject.get("errorSpore").getAsInt();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read maxLevelConfig.json");
            System.exit(-1);
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("errorSpore", 3);
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public int errorSpore;
}
