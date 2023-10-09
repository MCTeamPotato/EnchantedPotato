package com.teampotato.enchantedpotato.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class RarityConfig {
    public RarityConfig() {
        File enchantmentCategoryConfig = new File(EarlySetupInitializer.potatoDir, "rarity.json");
        if (!enchantmentCategoryConfig.exists()) {
            try {
                FileWriter writer = writeFile(enchantmentCategoryConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create rarity.json");
                System.exit(-1);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(enchantmentCategoryConfig))) {
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.runLikeHell = configObject.get("runLikeHell").getAsString();
            this.blackParade = configObject.get("blackParade").getAsString();
            this.graceOfGungnir = configObject.get("graceOfGungnir").getAsString();
            this.thisIsLeopard = configObject.get("thisIsLeopard").getAsString();
            this.dyingOfLight = configObject.get("dyingOfLight").getAsString();
            this.pressurizedCollapse = configObject.get("pressurizedCollapse").getAsString();
            this.errorSpore = configObject.get("errorSpore").getAsString();
            this.untouchable = configObject.get("untouchable").getAsString();
            this.shieldBladeCommendation = configObject.get("shieldBladeCommendation").getAsString();
            this.gaiaBlessing = configObject.get("gaiaBlessing").getAsString();
            this.gurenNoYumiya = configObject.get("gurenNoYumiya").getAsString();
            this.boneSuckalaka = configObject.get("boneSuckalaka").getAsString();
            this.loraTrainer = configObject.get("loraTrainer").getAsString();
            this.rippleOfDeath = configObject.get("rippleOfDeath").getAsString();
            this.poisonOfTheLastBreath = configObject.get("poisonOfTheLastBreath").getAsString();
            this.huaJin = configObject.get("huaJin").getAsString();
            this.wonderEggPriority = configObject.get("wonderEggPriority").getAsString();
            this.markFromTheBeneath = configObject.get("markFromTheBeneath").getAsString();
            this.armorBreaking = configObject.get("armorBreaking").getAsString();
            this.blessingOfTheNature = configObject.get("blessingOfTheNature").getAsString();
            this.caressingMoonlight = configObject.get("caressingMoonlight").getAsString();
            this.flameCross = configObject.get("flameCross").getAsString();
            this.oceanHued = configObject.get("oceanHued").getAsString();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read rarity.json");
            System.exit(-1);
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("runLikeHell", "RARE");
        defaultConfig.addProperty("blackParade", "RARE");
        defaultConfig.addProperty("graceOfGungnir", "RARE");
        defaultConfig.addProperty("thisIsLeopard", "RARE");
        defaultConfig.addProperty("dyingOfLight", "RARE");
        defaultConfig.addProperty("pressurizedCollapse", "RARE");
        defaultConfig.addProperty("errorSpore", "RARE");
        defaultConfig.addProperty("untouchable", "RARE");
        defaultConfig.addProperty("shieldBladeCommendation", "RARE");
        defaultConfig.addProperty("gaiaBlessing", "RARE");
        defaultConfig.addProperty("gurenNoYumiya", "RARE");
        defaultConfig.addProperty("boneSuckalaka", "RARE");
        defaultConfig.addProperty("loraTrainer", "RARE");
        defaultConfig.addProperty("rippleOfDeath", "RARE");
        defaultConfig.addProperty("poisonOfTheLastBreath", "RARE");
        defaultConfig.addProperty("huaJin", "RARE");
        defaultConfig.addProperty("wonderEggPriority", "RARE");
        defaultConfig.addProperty("markFromTheBeneath", "RARE");
        defaultConfig.addProperty("armorBreaking", "RARE");
        defaultConfig.addProperty("blessingOfTheNature", "RARE");
        defaultConfig.addProperty("caressingMoonlight", "RARE");
        defaultConfig.addProperty("flameCross", "RARE");
        defaultConfig.addProperty("oceanHued", "RARE");
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public String runLikeHell;
    public String blackParade;
    public String graceOfGungnir;
    public String thisIsLeopard;
    public String dyingOfLight;
    public String pressurizedCollapse;
    public String errorSpore;
    public String untouchable;
    public String shieldBladeCommendation;
    public String gaiaBlessing;
    public String gurenNoYumiya;
    public String boneSuckalaka;
    public String loraTrainer;
    public String rippleOfDeath;
    public String poisonOfTheLastBreath;
    public String huaJin;
    public String wonderEggPriority;
    public String markFromTheBeneath;
    public String armorBreaking;
    public String blessingOfTheNature;
    public String caressingMoonlight;
    public String flameCross;
    public String oceanHued;
}
