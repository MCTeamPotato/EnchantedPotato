package com.teampotato.enchantedpotato.config.attributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class IsCurseConfig {
    public IsCurseConfig() {
        File isCurseConfig = new File(EarlySetupInitializer.attributesDir, "isCurse.json");
        if (!isCurseConfig.exists()) {
            try {
                FileWriter writer = writeFile(isCurseConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create isCurse.json");
                System.exit(-1);
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(isCurseConfig));
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
            this.trueMan = configObject.get("trueMan").getAsBoolean();
            this.mineCarve = configObject.get("mineCarve").getAsBoolean();
            this.uniteStonesOfAll = configObject.get("uniteStonesOfAll").getAsBoolean();
            this.wendingWatersSereneLotus = configObject.get("wendingWatersSereneLotus").getAsBoolean();
            this.kingOfRiding = configObject.get("kingOfRiding").getAsBoolean();
            this.lawOfInertia = configObject.get("lawOfInertia").getAsBoolean();
            this.shootingStar = configObject.get("shootingStar").getAsBoolean();
            this.triback = configObject.get("triback").getAsBoolean();
            this.sniper = configObject.get("sniper").getAsBoolean();
            this.musician = configObject.get("musician").getAsBoolean();
            this.enderEnder = configObject.get("enderEnder").getAsBoolean();
            this.multiLoad = configObject.get("multiLoad").getAsBoolean();
            this.blackElegance = configObject.get("blackElegance").getAsBoolean();
            this.whiteInnocence = configObject.get("whiteInnocence").getAsBoolean();
            this.armsDrum = configObject.get("armsDrum").getAsBoolean();
            this.rickRod = configObject.get("rickRod").getAsBoolean();
            this.goldfishFireworks = configObject.get("goldfishFireworks").getAsBoolean();
            this.softTouch = configObject.get("softTouch").getAsBoolean();
            this.copperholic = configObject.get("copperholic").getAsBoolean();
            this.wellOfBlood  = configObject.get("wellOfBlood").getAsBoolean();
            this.missile = configObject.get("missile").getAsBoolean();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read isCurse.json");
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
        defaultConfig.addProperty("dyingOfLight", true);
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

    
    public boolean runLikeHell, blackParade, graceOfGungnir, thisIsLeopard, dyingOfLight,
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
