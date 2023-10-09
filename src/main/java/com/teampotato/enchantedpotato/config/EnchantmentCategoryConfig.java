package com.teampotato.enchantedpotato.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class EnchantmentCategoryConfig {
    public EnchantmentCategoryConfig() {
        File enchantmentCategoryConfig = new File(EarlySetupInitializer.potatoDir, "enchantmentCategory.json");
        if (!enchantmentCategoryConfig.exists()) {
            try {
                FileWriter writer = writeFile(enchantmentCategoryConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create enchantmentCategory.json");
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
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read enchantmentCategory.json");
            System.exit(-1);
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("runLikeHell", "ARMOR_FEET");
        defaultConfig.addProperty("blackParade", "ARMOR_FEET");
        defaultConfig.addProperty("graceOfGungnir", "BOW");
        defaultConfig.addProperty("thisIsLeopard", "ARMOR_LEGS");
        defaultConfig.addProperty("dyingOfLight", "ARMOR_HEAD");
        defaultConfig.addProperty("pressurizedCollapse", "BOW");
        defaultConfig.addProperty("errorSpore", "ARMOR_LEGS");
        defaultConfig.addProperty("untouchable", "ARMOR_LEGS");
        defaultConfig.addProperty("shieldBladeCommendation", "BREAKABLE");
        defaultConfig.addProperty("gaiaBlessing", "ARMOR_FEET");
        defaultConfig.addProperty("gurenNoYumiya", "BOW");
        defaultConfig.addProperty("boneSuckalaka", "BREAKABLE");
        defaultConfig.addProperty("loraTrainer", "BREAKABLE");
        defaultConfig.addProperty("rippleOfDeath", "ARMOR_FEET");
        defaultConfig.addProperty("poisonOfTheLastBreath", "WEAPON");
        defaultConfig.addProperty("huaJin", "ARMOR_CHEST");
        defaultConfig.addProperty("wonderEggPriority", "ARMOR_HEAD");
        defaultConfig.addProperty("markFromTheBeneath", "BREAKABLE");
        defaultConfig.addProperty("armorBreaking", "BREAKABLE");
        defaultConfig.addProperty("blessingOfTheNature", "ARMOR_CHEST");
        defaultConfig.addProperty("caressingMoonlight", "ARMOR_CHEST");
        defaultConfig.addProperty("flameCross", "ARMOR_HEAD");
        defaultConfig.addProperty("oceanHued", "WEAPON");
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
