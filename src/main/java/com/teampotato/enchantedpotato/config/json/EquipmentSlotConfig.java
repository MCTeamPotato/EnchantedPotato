package com.teampotato.enchantedpotato.config.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class EquipmentSlotConfig {
    public EquipmentSlotConfig() {
        File equipmentSlotConfig = new File(EarlySetupInitializer.potatoDir, "equipmentSlot.json");
        if (!equipmentSlotConfig.exists()) {
            try {
                FileWriter writer = writeFile(equipmentSlotConfig);
                writer.close();
            } catch (Exception e) {
                EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to create equipmentSlot.json");
                
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(equipmentSlotConfig));
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
            this.trueMan = configObject.get("trueMan").getAsString().split(";");
            this.mineCarve = configObject.get("mineCarve").getAsString().split(";");
            this.uniteStonesOfAll = configObject.get("uniteStonesOfAll").getAsString().split(";");
            this.wendingWatersSereneLotus = configObject.get("wendingWatersSereneLotus").getAsString().split(";");
            this.kingOfRiding = configObject.get("kingOfRiding").getAsString().split(";");
            this.lawOfInertia = configObject.get("lawOfInertia").getAsString().split(";");
            this.shootingStar = configObject.get("shootingStar").getAsString().split(";");
            this.triback = configObject.get("triback").getAsString().split(";");
            this.sniper = configObject.get("sniper").getAsString().split(";");
            this.musician = configObject.get("musician").getAsString().split(";");
            this.enderEnder = configObject.get("enderEnder").getAsString().split(";");
            this.multiLoad = configObject.get("multiLoad").getAsString().split(";");
            this.blackElegance = configObject.get("blackElegance").getAsString().split(";");
            this.whiteInnocence = configObject.get("whiteInnocence").getAsString().split(";");
            this.armsDrum = configObject.get("armsDrum").getAsString().split(";");
            this.rickRod = configObject.get("rickRod").getAsString().split(";");
            this.goldfishFireworks = configObject.get("goldfishFireworks").getAsString().split(";");
            this.softTouch = configObject.get("softTouch").getAsString().split(";");
            this.copperholic = configObject.get("copperholic").getAsString().split(";");
            this.wellOfBlood = configObject.get("wellOfBlood").getAsString().split(";");
            this.missile = configObject.get("missile").getAsString().split(";");
        } catch (Exception e) {
            EarlySetupInitializer.LOGGER.log(Level.FATAL, e.getMessage(), "Failed to read equipmentSlot.json");
            
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
        defaultConfig.addProperty("trueMan", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("mineCarve", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("uniteStonesOfAll", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("wendingWatersSereneLotus", "FEET");
        defaultConfig.addProperty("kingOfRiding", "LEGS");
        defaultConfig.addProperty("lawOfInertia", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("shootingStar", "CHEST");
        defaultConfig.addProperty("triback", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("sniper", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("musician", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("enderEnder", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("multiLoad", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("blackElegance", "LEGS");
        defaultConfig.addProperty("whiteInnocence", "LEGS");
        defaultConfig.addProperty("armsDrum", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("rickRod", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("goldfishFireworks", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("softTouch", "MAINHAND;OFFHAND");
        defaultConfig.addProperty("copperholic", "FEET");
        defaultConfig.addProperty("wellOfBlood", "CHEST");
        defaultConfig.addProperty("missile", "MAINHAND;OFFHAND");
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    public String[] runLikeHell, blackParade, graceOfGungnir, thisIsLeopard, dyingOfLight,
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
