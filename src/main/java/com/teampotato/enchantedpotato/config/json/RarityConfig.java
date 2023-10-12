package com.teampotato.enchantedpotato.config.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class RarityConfig {
    public RarityConfig() {
        File rarityConfig = new File(EarlySetupInitializer.potatoDir, "rarity.json");
        if (!rarityConfig.exists()) {
            try {
                FileWriter writer = writeFile(rarityConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create rarity.json");
                
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(rarityConfig));
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
            this.trueMan = configObject.get("trueMan").getAsString();
            this.mineCarve  = configObject.get("mineCarve").getAsString();
            this.uniteStonesOfAll = configObject.get("uniteStonesOfAll").getAsString();
            this.wendingWatersSereneLotus = configObject.get("wendingWatersSereneLotus").getAsString();
            this.kingOfRiding = configObject.get("kingOfRiding").getAsString();
            this.lawOfInertia = configObject.get("lawOfInertia").getAsString();
            this.shootingStar = configObject.get("shootingStar").getAsString();
            this.triback = configObject.get("triback").getAsString();
            this.sniper = configObject.get("sniper").getAsString();
            this.musician = configObject.get("musician").getAsString();
            this.enderEnder = configObject.get("enderEnder").getAsString();
            this.multiLoad = configObject.get("multiLoad").getAsString();
            this.blackElegance = configObject.get("blackElegance").getAsString();
            this.whiteInnocence = configObject.get("whiteInnocence").getAsString();
            this.armsDrum = configObject.get("armsDrum").getAsString();
            this.rickRod = configObject.get("rickRod").getAsString();
            this.goldfishFireworks = configObject.get("goldfishFireworks").getAsString();
            this.softTouch = configObject.get("softTouch").getAsString();
            this.copperholic = configObject.get("copperholic").getAsString();
            this.wellOfBlood = configObject.get("wellOfBlood").getAsString();
            this.missile = configObject.get("missile").getAsString();
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read rarity.json");
            
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
        defaultConfig.addProperty("trueMan", "RARE");
        defaultConfig.addProperty("mineCarve", "RARE");
        defaultConfig.addProperty("uniteStonesOfAll", "RARE");
        defaultConfig.addProperty("wendingWatersSereneLotus", "RARE");
        defaultConfig.addProperty("kingOfRiding", "RARE");
        defaultConfig.addProperty("lawOfInertia", "RARE");
        defaultConfig.addProperty("shootingStar", "RARE");
        defaultConfig.addProperty("triback", "RARE");
        defaultConfig.addProperty("sniper", "RARE");
        defaultConfig.addProperty("musician", "RARE");
        defaultConfig.addProperty("enderEnder", "RARE");
        defaultConfig.addProperty("multiLoad", "RARE");
        defaultConfig.addProperty("blackElegance", "RARE");
        defaultConfig.addProperty("whiteInnocence", "RARE");
        defaultConfig.addProperty("armsDrum", "RARE");
        defaultConfig.addProperty("rickRod", "RARE");
        defaultConfig.addProperty("goldfishFireworks", "RARE");
        defaultConfig.addProperty("softTouch", "RARE");
        defaultConfig.addProperty("copperholic", "RARE");
        defaultConfig.addProperty("wellOfBlood", "RARE");
        defaultConfig.addProperty("missile", "RARE");
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    
    public String runLikeHell, blackParade, graceOfGungnir, thisIsLeopard, dyingOfLight,
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
