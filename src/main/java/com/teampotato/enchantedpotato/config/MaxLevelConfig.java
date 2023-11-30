package com.teampotato.enchantedpotato.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
                
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(maxLevelConfig))) {
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.errorSpore = configObject.get("errorSpore").getAsInt();
            this.sniper = configObject.get("sniper").getAsInt();
            this.enderEnder = configObject.get("enderEnder").getAsInt();
            this.goldfishFireworks = configObject.get("goldfishFireworks").getAsInt();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read maxLevelConfig.json");
            
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("errorSpore", 3);
        defaultConfig.addProperty("sniper", 3);
        defaultConfig.addProperty("enderEnder", 3);
        defaultConfig.addProperty("goldfishFireworks", 2);
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public int errorSpore, sniper, enderEnder, goldfishFireworks;
}
