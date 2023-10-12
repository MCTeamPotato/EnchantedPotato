package com.teampotato.enchantedpotato.config.json.reloadable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class IsTreasureOnlyConfig {
    public static final File FILE = new File(EarlySetupInitializer.attributesDir, "isTreasureOnly.json");

    public IsTreasureOnlyConfig() {
        if (!FILE.exists()) {
            try {
                FileWriter writer = writeFile();
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create isTreasureOnly.json");
                
            }
        }

        readConfig(FILE);
    }

    public static void readConfig(File isTreasureOnlyConfig) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(isTreasureOnlyConfig));
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
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read isTreasureOnly.json");
            
        }
    }

    @NotNull
    private static FileWriter writeFile() throws IOException {
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
        defaultConfig.addProperty("trueMan", false);
        defaultConfig.addProperty("mineCarve", false);
        defaultConfig.addProperty("uniteStonesOfAll", false);
        defaultConfig.addProperty("wendingWatersSereneLotus", false);
        defaultConfig.addProperty("kingOfRiding", false);
        defaultConfig.addProperty("lawOfInertia", false);
        defaultConfig.addProperty("shootingStar", false);
        defaultConfig.addProperty("triback", false);
        defaultConfig.addProperty("sniper", false);
        defaultConfig.addProperty("musician", false);
        defaultConfig.addProperty("enderEnder", false);
        defaultConfig.addProperty("multiLoad", false);
        defaultConfig.addProperty("blackElegance", false);
        defaultConfig.addProperty("whiteInnocence", false);
        defaultConfig.addProperty("armsDrum", false);
        defaultConfig.addProperty("rickRod", false);
        defaultConfig.addProperty("goldfishFireworks", false);
        defaultConfig.addProperty("softTouch", false);
        defaultConfig.addProperty("copperholic", false);
        defaultConfig.addProperty("wellOfBlood", false);
        defaultConfig.addProperty("missile", false);
        FileWriter writer = new FileWriter(IsTreasureOnlyConfig.FILE);
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
