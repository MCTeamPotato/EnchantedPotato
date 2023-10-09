package com.teampotato.enchantedpotato;

import com.teampotato.enchantedpotato.config.*;
import com.teampotato.enchantedpotato.config.attributes.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Override public void onLoad(String s) {}
    @Override public String getRefMapperConfig() {return null;}
    @Override public boolean shouldApplyMixin(String s, String s1) {return true;}
    @Override public void acceptTargets(Set<String> set, Set<String> set1) {}
    @Override public List<String> getMixins() {return null;}
    @Override public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
    @Override public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
}
