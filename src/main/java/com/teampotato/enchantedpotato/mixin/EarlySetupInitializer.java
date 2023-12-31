package com.teampotato.enchantedpotato.mixin;

import com.teampotato.enchantedpotato.config.*;
import net.neoforged.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;

import java.io.File;

public abstract class EarlySetupInitializer implements IMixinConfigPlugin {

    public static final String MOD_ID = "enchantedpotato";
    public static final Logger LOGGER = LogManager.getLogger("EnchantedPotato");
    public static File configDir;
    public static File potatoDir;
    public static File attributesDir;

    public static EnchantmentCategoryConfig enchantmentCategoryConfig;
    public static EquipmentSlotConfig equipmentSlotConfig;
    public static RarityConfig rarityConfig;
    public static FunctionConfig functionConfig;
    public static MaxLevelConfig maxLevelConfig;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public EarlySetupInitializer() {
        LOGGER.info("Oh, potato, I'm enchanted by you.");
        configDir = FMLLoader.getGamePath().resolve("config").toFile();
        configDir.mkdirs();
        potatoDir = new File(configDir, MOD_ID);
        potatoDir.mkdirs();
        attributesDir = new File(potatoDir, "attributes");
        attributesDir.mkdirs();

        enchantmentCategoryConfig = new EnchantmentCategoryConfig();
        equipmentSlotConfig = new EquipmentSlotConfig();
        rarityConfig = new RarityConfig();
        functionConfig = new FunctionConfig();
        maxLevelConfig = new MaxLevelConfig();
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, @NotNull String mixinClassName) {
        if (mixinClassName.contains("goldfish_fireworks")) return functionConfig.goldfishFireworks;
        if (mixinClassName.contains("ender_ender")) return functionConfig.enderEnder;
        if (mixinClassName.contains("triback")) return functionConfig.triback;
        if (mixinClassName.contains("sniper")) return functionConfig.sniper;
        if (mixinClassName.contains("wending_waters_serene_lotus")) return functionConfig.wendingWatersSereneLotus;
        if (mixinClassName.contains("wonder_egg_priority")) return functionConfig.wonderEggPriority;
        if (mixinClassName.contains("grace_of_gungnir")) return functionConfig.graceOfGungnir;
        if (mixinClassName.contains("guren_no_yumiya")) return functionConfig.gurenNoYumiya;
        if (mixinClassName.contains("dying_of_light")) return functionConfig.dyingOfLight;
        if (mixinClassName.contains("blessing_of_the_nature")) return functionConfig.blessingOfTheNature;
        if (mixinClassName.contains("run_like_hell")) return functionConfig.runLikeHell;
        if (mixinClassName.contains("pressurized_collapse")) return functionConfig.pressurizedCollapse;
        if (mixinClassName.contains("poison_of_the_last_breath")) return functionConfig.poisonOfTheLastBreath;
        return true;
    }
}
