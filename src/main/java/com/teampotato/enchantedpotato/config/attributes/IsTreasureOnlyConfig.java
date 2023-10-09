package com.teampotato.enchantedpotato.config.attributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class IsTreasureOnlyConfig {
    public IsTreasureOnlyConfig() {
        File canApplyAtEnchantingTableConfig = new File(EarlySetupInitializer.attributesDir, "isTreasureOnly.json");
        if (!canApplyAtEnchantingTableConfig.exists()) {
            try {
                FileWriter writer = writeFile(canApplyAtEnchantingTableConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create isTreasureOnly.json");
                System.exit(-1);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(canApplyAtEnchantingTableConfig))) {
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.runLikeHell = configObject.get("runLikeHell").getAsBoolean();
            this.blackParade = configObject.get("blackParade").getAsBoolean();
            this.graceOfGungnir = configObject.get("graceOfGungnir").getAsBoolean();
            this.thisIsLeopard = configObject.get("thisIsLeopard").getAsBoolean();
            this.dyingOfLight = configObject.get("dyingOfLight").getAsBoolean();
            this.pressurizedCollapse = configObject.get("pressurizedCollapse").getAsBoolean();
            this.errorSpore = configObject.get("errorSpore").getAsBoolean();
            this.untouchable = configObject.get("untouchable").getAsBoolean();
            this.shieldBladeCommendation = configObject.get("shieldBladeCommendation").getAsBoolean();
            this.gaiaBlessing = configObject.get("gaiaBlessing").getAsBoolean();
            this.gurenNoYumiya = configObject.get("gurenNoYumiya").getAsBoolean();
            this.boneSuckalaka = configObject.get("boneSuckalaka").getAsBoolean();
            this.loraTrainer = configObject.get("loraTrainer").getAsBoolean();
            this.rippleOfDeath = configObject.get("rippleOfDeath").getAsBoolean();
            this.poisonOfTheLastBreath = configObject.get("poisonOfTheLastBreath").getAsBoolean();
            this.huaJin = configObject.get("huaJin").getAsBoolean();
            this.wonderEggPriority = configObject.get("wonderEggPriority").getAsBoolean();
            this.markFromTheBeneath = configObject.get("markFromTheBeneath").getAsBoolean();
            this.armorBreaking = configObject.get("armorBreaking").getAsBoolean();
            this.blessingOfTheNature = configObject.get("blessingOfTheNature").getAsBoolean();
            this.caressingMoonlight = configObject.get("caressingMoonlight").getAsBoolean();
            this.flameCross = configObject.get("flameCross").getAsBoolean();
            this.oceanHued = configObject.get("oceanHued").getAsBoolean();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read isTreasureOnly.json");
            System.exit(-1);
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("runLikeHell", false);
        defaultConfig.addProperty("blackParade", false);
        defaultConfig.addProperty("graceOfGungnir", false);
        defaultConfig.addProperty("thisIsLeopard", false);
        defaultConfig.addProperty("dyingOfLight", false);
        defaultConfig.addProperty("pressurizedCollapse", false);
        defaultConfig.addProperty("errorSpore", false);
        defaultConfig.addProperty("untouchable", false);
        defaultConfig.addProperty("shieldBladeCommendation", false);
        defaultConfig.addProperty("gaiaBlessing", false);
        defaultConfig.addProperty("gurenNoYumiya", false);
        defaultConfig.addProperty("boneSuckalaka", false);
        defaultConfig.addProperty("loraTrainer", false);
        defaultConfig.addProperty("rippleOfDeath", false);
        defaultConfig.addProperty("poisonOfTheLastBreath", false);
        defaultConfig.addProperty("huaJin", false);
        defaultConfig.addProperty("wonderEggPriority", false);
        defaultConfig.addProperty("markFromTheBeneath", false);
        defaultConfig.addProperty("armorBreaking", false);
        defaultConfig.addProperty("blessingOfTheNature", false);
        defaultConfig.addProperty("caressingMoonlight", false);
        defaultConfig.addProperty("flameCross", false);
        defaultConfig.addProperty("oceanHued", false);
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public boolean runLikeHell;
    public boolean blackParade;
    public boolean graceOfGungnir;
    public boolean thisIsLeopard;
    public boolean dyingOfLight;
    public boolean pressurizedCollapse;
    public boolean errorSpore;
    public boolean untouchable;
    public boolean shieldBladeCommendation;
    public boolean gaiaBlessing;
    public boolean gurenNoYumiya;
    public boolean boneSuckalaka;
    public boolean loraTrainer;
    public boolean rippleOfDeath;
    public boolean poisonOfTheLastBreath;
    public boolean huaJin;
    public boolean wonderEggPriority;
    public boolean markFromTheBeneath;
    public boolean armorBreaking;
    public boolean blessingOfTheNature;
    public boolean caressingMoonlight;
    public boolean flameCross;
    public boolean oceanHued;
}
