package com.teampotato.enchantedpotato.config.attributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class CanApplyAtEnchantingTableConfig {
    public static final File FILE = new File(EarlySetupInitializer.attributesDir, "canApplyAtEnchantingTable.json");

    public CanApplyAtEnchantingTableConfig() {
        if (!FILE.exists()) {
            try {
                FileWriter writer = writeFile();
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create canApplyAtEnchantingTable.json");
                
            }
        }

        readConfig(FILE);
    }

    public static void readConfig(File canApplyAtEnchantingTableConfig) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(canApplyAtEnchantingTableConfig));
            JsonObject configObject = JsonParser.parseReader(reader).getAsJsonObject();
            runLikeHell = configObject.get("runLikeHell").getAsBoolean();
            blackParade = configObject.get("blackParade").getAsBoolean();
            graceOfGungnir = configObject.get("graceOfGungnir").getAsBoolean();
            thisIsLeopard = configObject.get("thisIsLeopard").getAsBoolean();
            dyingOfLight = configObject.get("dyingOfLight").getAsBoolean();
            pressurizedCollapse = configObject.get("pressurizedCollapse").getAsBoolean();
            errorSpore = configObject.get("errorSpore").getAsBoolean();
            untouchable = configObject.get("untouchable").getAsBoolean();
            shieldBladeCommendation = configObject.get("shieldBladeCommendation").getAsBoolean();
            gaiaBlessing = configObject.get("gaiaBlessing").getAsBoolean();
            gurenNoYumiya = configObject.get("gurenNoYumiya").getAsBoolean();
            boneSuckalaka = configObject.get("boneSuckalaka").getAsBoolean();
            loraTrainer = configObject.get("loraTrainer").getAsBoolean();
            rippleOfDeath = configObject.get("rippleOfDeath").getAsBoolean();
            poisonOfTheLastBreath = configObject.get("poisonOfTheLastBreath").getAsBoolean();
            huaJin = configObject.get("huaJin").getAsBoolean();
            wonderEggPriority = configObject.get("wonderEggPriority").getAsBoolean();
            markFromTheBeneath = configObject.get("markFromTheBeneath").getAsBoolean();
            armorBreaking = configObject.get("armorBreaking").getAsBoolean();
            blessingOfTheNature = configObject.get("blessingOfTheNature").getAsBoolean();
            caressingMoonlight = configObject.get("caressingMoonlight").getAsBoolean();
            flameCross = configObject.get("flameCross").getAsBoolean();
            oceanHued = configObject.get("oceanHued").getAsBoolean();
            trueMan = configObject.get("trueMan").getAsBoolean();
            mineCarve = configObject.get("mineCarve").getAsBoolean();
            uniteStonesOfAll = configObject.get("uniteStonesOfAll").getAsBoolean();
            wendingWatersSereneLotus = configObject.get("wendingWatersSereneLotus").getAsBoolean();
            kingOfRiding = configObject.get("kingOfRiding").getAsBoolean();
            lawOfInertia = configObject.get("lawOfInertia").getAsBoolean();
            shootingStar = configObject.get("shootingStar").getAsBoolean();
            triback = configObject.get("triback").getAsBoolean();
            sniper = configObject.get("sniper").getAsBoolean();
            musician = configObject.get("musician").getAsBoolean();
            enderEnder = configObject.get("enderEnder").getAsBoolean();
            multiLoad = configObject.get("multiLoad").getAsBoolean();
            blackElegance = configObject.get("blackElegance").getAsBoolean();
            whiteInnocence = configObject.get("whiteInnocence").getAsBoolean();
            armsDrum = configObject.get("armsDrum").getAsBoolean();
            rickRod = configObject.get("rickRod").getAsBoolean();
            goldfishFireworks = configObject.get("goldfishFireworks").getAsBoolean();
            softTouch = configObject.get("softTouch").getAsBoolean();
            copperholic = configObject.get("copperholic").getAsBoolean();
            wellOfBlood  = configObject.get("wellOfBlood").getAsBoolean();
            missile = configObject.get("missile").getAsBoolean();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read canApplyAtEnchantingTable.json");
        }
    }

    @NotNull
    private static FileWriter writeFile() throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("runLikeHell", true);
        defaultConfig.addProperty("blackParade", true);
        defaultConfig.addProperty("graceOfGungnir", true);
        defaultConfig.addProperty("thisIsLeopard", true);
        defaultConfig.addProperty("dyingOfLight", true);
        defaultConfig.addProperty("pressurizedCollapse", true);
        defaultConfig.addProperty("errorSpore", true);
        defaultConfig.addProperty("untouchable", true);
        defaultConfig.addProperty("shieldBladeCommendation", true);
        defaultConfig.addProperty("gaiaBlessing", true);
        defaultConfig.addProperty("gurenNoYumiya", true);
        defaultConfig.addProperty("boneSuckalaka", true);
        defaultConfig.addProperty("loraTrainer", true);
        defaultConfig.addProperty("rippleOfDeath", true);
        defaultConfig.addProperty("poisonOfTheLastBreath", true);
        defaultConfig.addProperty("huaJin", true);
        defaultConfig.addProperty("wonderEggPriority", true);
        defaultConfig.addProperty("markFromTheBeneath", true);
        defaultConfig.addProperty("armorBreaking", true);
        defaultConfig.addProperty("blessingOfTheNature", true);
        defaultConfig.addProperty("caressingMoonlight", true);
        defaultConfig.addProperty("flameCross", true);
        defaultConfig.addProperty("oceanHued", true);
        defaultConfig.addProperty("trueMan", true);
        defaultConfig.addProperty("mineCarve", true);
        defaultConfig.addProperty("uniteStonesOfAll", true);
        defaultConfig.addProperty("wendingWatersSereneLotus", true);
        defaultConfig.addProperty("kingOfRiding", true);
        defaultConfig.addProperty("lawOfInertia", true);
        defaultConfig.addProperty("shootingStar", true);
        defaultConfig.addProperty("triback", true);
        defaultConfig.addProperty("sniper", true);
        defaultConfig.addProperty("musician", true);
        defaultConfig.addProperty("enderEnder", true);
        defaultConfig.addProperty("multiLoad", true);
        defaultConfig.addProperty("blackElegance", true);
        defaultConfig.addProperty("whiteInnocence", true);
        defaultConfig.addProperty("armsDrum", true);
        defaultConfig.addProperty("rickRod", true);
        defaultConfig.addProperty("goldfishFireworks", true);
        defaultConfig.addProperty("softTouch", true);
        defaultConfig.addProperty("copperholic", true);
        defaultConfig.addProperty("wellOfBlood", true);
        defaultConfig.addProperty("missile", true);
        FileWriter writer = new FileWriter(CanApplyAtEnchantingTableConfig.FILE);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public static boolean runLikeHell, blackParade, graceOfGungnir, thisIsLeopard, dyingOfLight,
    pressurizedCollapse, errorSpore, untouchable, shieldBladeCommendation,
    gaiaBlessing, gurenNoYumiya, boneSuckalaka, loraTrainer, rippleOfDeath, 
    poisonOfTheLastBreath, huaJin, wonderEggPriority, markFromTheBeneath, 
    armorBreaking, blessingOfTheNature, caressingMoonlight, flameCross, oceanHued, 
    trueMan, mineCarve, uniteStonesOfAll, wendingWatersSereneLotus, 
    kingOfRiding, lawOfInertia, shootingStar, triback, 
    sniper, musician, enderEnder, multiLoad, 
    blackElegance, whiteInnocence, armsDrum, rickRod, 
    goldfishFireworks, softTouch, copperholic, wellOfBlood, missile;
}
