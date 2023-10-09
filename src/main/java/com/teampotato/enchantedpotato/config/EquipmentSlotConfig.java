package com.teampotato.enchantedpotato.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class EquipmentSlotConfig {
    public EquipmentSlotConfig() {
        File enchantmentCategoryConfig = new File(EarlySetupInitializer.potatoDir, "equipmentSlot.json");
        if (!enchantmentCategoryConfig.exists()) {
            try {
                FileWriter writer = writeFile(enchantmentCategoryConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create equipmentSlot.json");
                System.exit(-1);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(enchantmentCategoryConfig))) {
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.runLikeHell = configObject.get("runLikeHell").getAsString().split(";");
            this.blackParade = configObject.get("blackParade").getAsString().split(";");
            this.graceOfGungnir = configObject.get("graceOfGungnir").getAsString().split(";");
            this.thisIsLeopard = configObject.get("thisIsLeopard").getAsString().split(";");
            this.dyingOfLight = configObject.get("dyingOfLight").getAsString().split(";");
            this.pressurizedCollapse = configObject.get("pressurizedCollapse").getAsString().split(";");
            this.errorSpore = configObject.get("errorSpore").getAsString().split(";");
            this.untouchable = configObject.get("untouchable").getAsString().split(";");
            this.shieldBladeCommendation = configObject.get("shieldBladeCommendation").getAsString().split(";");
            this.gaiaBlessing = configObject.get("gaiaBlessing").getAsString().split(";");
            this.gurenNoYumiya = configObject.get("gurenNoYumiya").getAsString().split(";");
            this.boneSuckalaka = configObject.get("boneSuckalaka").getAsString().split(";");
            this.loraTrainer = configObject.get("loraTrainer").getAsString().split(";");
            this.rippleOfDeath = configObject.get("rippleOfDeath").getAsString().split(";");
            this.poisonOfTheLastBreath = configObject.get("poisonOfTheLastBreath").getAsString().split(";");
            this.huaJin = configObject.get("huaJin").getAsString().split(";");
            this.wonderEggPriority = configObject.get("wonderEggPriority").getAsString().split(";");
            this.markFromTheBeneath = configObject.get("markFromTheBeneath").getAsString().split(";");
            this.armorBreaking = configObject.get("armorBreaking").getAsString().split(";");
            this.blessingOfTheNature = configObject.get("blessingOfTheNature").getAsString().split(";");
            this.caressingMoonlight = configObject.get("caressingMoonlight").getAsString().split(";");
            this.flameCross = configObject.get("flameCross").getAsString().split(";");
            this.oceanHued = configObject.get("oceanHued").getAsString().split(";");
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read equipmentSlot.json");
            System.exit(-1);
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("runLikeHell", "FEET");
        defaultConfig.addProperty("blackParade", "FEET");
        defaultConfig.addProperty("graceOfGungnir", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("thisIsLeopard", "LEGS");
        defaultConfig.addProperty("dyingOfLight", "HEAD");
        defaultConfig.addProperty("pressurizedCollapse", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("errorSpore", "LEGS");
        defaultConfig.addProperty("untouchable", "LEGS");
        defaultConfig.addProperty("shieldBladeCommendation", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("gaiaBlessing", "FEET");
        defaultConfig.addProperty("gurenNoYumiya", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("boneSuckalaka", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("loraTrainer", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("rippleOfDeath", "FEET");
        defaultConfig.addProperty("poisonOfTheLastBreath", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("huaJin", "CHEST");
        defaultConfig.addProperty("wonderEggPriority", "HEAD");
        defaultConfig.addProperty("markFromTheBeneath", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("armorBreaking", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("blessingOfTheNature", "CHEST");
        defaultConfig.addProperty("caressingMoonlight", "CHEST");
        defaultConfig.addProperty("flameCross", "CHEST");
        defaultConfig.addProperty("oceanHued", "MAINHAND;OFFHAND");
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public String[] runLikeHell;

    public String[] blackParade;
    public String[] graceOfGungnir;
    public String[] thisIsLeopard;
    public String[] dyingOfLight;
    public String[] pressurizedCollapse;
    public String[] errorSpore;
    public String[] untouchable;
    public String[] shieldBladeCommendation;
    public String[] gaiaBlessing;
    public String[] gurenNoYumiya;
    public String[] boneSuckalaka;
    public String[] loraTrainer;
    public String[] rippleOfDeath;
    public String[] poisonOfTheLastBreath;
    public String[] huaJin;
    public String[] wonderEggPriority;
    public String[] markFromTheBeneath;
    public String[] armorBreaking;
    public String[] blessingOfTheNature;
    public String[] caressingMoonlight;
    public String[] flameCross;
    public String[] oceanHued;
}
