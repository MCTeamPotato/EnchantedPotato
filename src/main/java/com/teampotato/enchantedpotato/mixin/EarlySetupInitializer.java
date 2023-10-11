package com.teampotato.enchantedpotato.mixin;

import com.teampotato.enchantedpotato.config.json.attributes.*;
import com.teampotato.enchantedpotato.config.json.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.util.List;
import java.util.Set;

public class EarlySetupInitializer implements IMixinConfigPlugin {

    public static final String MOD_ID = "enchantedpotato";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static File configDir;
    public static File potatoDir;
    public static File attributesDir;

    public static CanApplyAtEnchantingTableConfig canApplyAtEnchantingTableConfig;
    public static IsAllowedOnBooksConfig isAllowedOnBooksConfig;
    public static IsCurseConfig isCurseConfig;
    public static IsDiscoverableConfig isDiscoverableConfig;
    public static IsTradeableConfig isTradeableConfig;
    public static IsTreasureOnlyConfig isTreasureOnlyConfig;
    public static EnchantmentCategoryConfig enchantmentCategoryConfig;
    public static EquipmentSlotConfig equipmentSlotConfig;
    public static RarityConfig rarityConfig;
    public static FunctionConfig functionConfig;
    public static MaxLevelConfig maxLevelConfig;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public EarlySetupInitializer() {
        configDir = new File("./config");
        configDir.mkdirs();
        potatoDir = new File(configDir, MOD_ID);
        potatoDir.mkdirs();
        attributesDir = new File(potatoDir, "attributes");
        attributesDir.mkdirs();

        canApplyAtEnchantingTableConfig = new CanApplyAtEnchantingTableConfig();
        isAllowedOnBooksConfig = new IsAllowedOnBooksConfig();
        isCurseConfig = new IsCurseConfig();
        isDiscoverableConfig = new IsDiscoverableConfig();
        isTradeableConfig = new IsTradeableConfig();
        isTreasureOnlyConfig = new IsTreasureOnlyConfig();
        enchantmentCategoryConfig = new EnchantmentCategoryConfig();
        equipmentSlotConfig = new EquipmentSlotConfig();
        rarityConfig = new RarityConfig();
        functionConfig = new FunctionConfig();
        maxLevelConfig = new MaxLevelConfig();
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, @NotNull String mixinClassName) {
        if (mixinClassName.contains("goldfish_fireworks")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.goldfishFireworks);
            return functionConfig.goldfishFireworks;
        }
        if (mixinClassName.contains("ender_ender")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.enderEnder);
            return functionConfig.enderEnder;
        }
        if (mixinClassName.contains("triback")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.triback);
            return functionConfig.triback;
        }
        if (mixinClassName.contains("sniper")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.sniper);
            return functionConfig.sniper;
        }
        if (mixinClassName.contains("wending_waters_serene_lotus")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.wendingWatersSereneLotus);
            return functionConfig.wendingWatersSereneLotus;
        }
        if (mixinClassName.contains("wonder_egg_priority")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.wonderEggPriority);
            return functionConfig.wonderEggPriority;
        }
        if (mixinClassName.contains("grace_of_gungnir")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.graceOfGungnir);
            return functionConfig.graceOfGungnir;
        }
        if (mixinClassName.contains("guren_no_yumiya")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.gurenNoYumiya);
            return functionConfig.gurenNoYumiya;
        }
        if (mixinClassName.contains("dying_of_light")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.dyingOfLight);
            return functionConfig.dyingOfLight;
        }
        if (mixinClassName.contains("blessing_of_the_nature")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.blessingOfTheNature);
            return functionConfig.blessingOfTheNature;
        }
        if (mixinClassName.contains("run_like_hell")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.runLikeHell);
            return functionConfig.runLikeHell;
        }
        if (mixinClassName.contains("pressurized_collapse")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.pressurizedCollapse);
            return functionConfig.pressurizedCollapse;
        }
        if (mixinClassName.contains("poison_of_the_last_breath")) {
            LOGGER.debug(mixinClassName + ": " + functionConfig.poisonOfTheLastBreath);
            return functionConfig.poisonOfTheLastBreath;
        }
        return true;
    }

    @Override public void onLoad(String s) {}
    @Override public String getRefMapperConfig() {return null;}
    @Override public void acceptTargets(Set<String> set, Set<String> set1) {}
    @Override public List<String> getMixins() {return null;}
    @Override public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
    @Override public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
}
