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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(enchantmentCategoryConfig));
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
        defaultConfig.addProperty("trueMan", "BOW");
        defaultConfig.addProperty("mineCarve", "DIGGER");
        defaultConfig.addProperty("uniteStonesOfAll", "DIGGER");
        defaultConfig.addProperty("wendingWatersSereneLotus", "ARMOR_FEET");
        defaultConfig.addProperty("kingOfRiding", "ARMOR_LEGS");
        defaultConfig.addProperty("lawOfInertia", "WEAPON");
        defaultConfig.addProperty("shootingStar", "BREAKABLE");
        defaultConfig.addProperty("triback", "TRIDENT");
        defaultConfig.addProperty("sniper", "CROSSBOW");
        defaultConfig.addProperty("musician", "WEAPON");
        defaultConfig.addProperty("enderEnder", "WEAPON");
        defaultConfig.addProperty("multiLoad", "CROSSBOW");
        defaultConfig.addProperty("blackElegance", "ARMOR_LEGS");
        defaultConfig.addProperty("whiteInnocence", "ARMOR_LEGS");
        defaultConfig.addProperty("armsDrum", "BREAKABLE");
        defaultConfig.addProperty("rickRod", "BREAKABLE");
        defaultConfig.addProperty("goldfishFireworks", "CROSSBOW");
        defaultConfig.addProperty("softTouch", "WEAPON");
        defaultConfig.addProperty("copperholic", "ARMOR_FEET");
        defaultConfig.addProperty("wellOfBlood", "ARMOR_CHEST");
        defaultConfig.addProperty("missile", "TRIDENT");
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
