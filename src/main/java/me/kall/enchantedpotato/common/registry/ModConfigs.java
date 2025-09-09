package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.config.*;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

public class ModConfigs {
    public static void register(@NotNull ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, RunLikeHellConfig.INSTANCE, EnchantedPotato.MOD_ID + "-RunLikeHell.toml");
        container.registerConfig(ModConfig.Type.COMMON, BlackParadeConfig.INSTANCE, EnchantedPotato.MOD_ID + "-BlackParade.toml");
        container.registerConfig(ModConfig.Type.COMMON, UntouchableConfig.INSTANCE, EnchantedPotato.MOD_ID + "-Untouchable.toml");
        container.registerConfig(ModConfig.Type.COMMON, GraceOfGaiaConfig.INSTANCE, EnchantedPotato.MOD_ID + "-GraceOfGaia.toml");
        container.registerConfig(ModConfig.Type.COMMON, GurenNoYumiyaConfig.INSTANCE, EnchantedPotato.MOD_ID + "-GurenNoYumiya.toml");
        container.registerConfig(ModConfig.Type.COMMON, LoRATrainerConfig.INSTANCE, EnchantedPotato.MOD_ID + "-LoRATrainer.toml");
        container.registerConfig(ModConfig.Type.COMMON, RippleOfDeathConfig.INSTANCE, EnchantedPotato.MOD_ID + "-RippleOfDeath.toml");
        container.registerConfig(ModConfig.Type.COMMON, DissolveConfig.INSTANCE, EnchantedPotato.MOD_ID + "-Dissolve.toml");
        container.registerConfig(ModConfig.Type.COMMON, PressurizedCollapseConfig.INSTANCE, EnchantedPotato.MOD_ID + "-PressurizedCollapse.toml");
        container.registerConfig(ModConfig.Type.COMMON, MarkFromTheBeneathConfig.INSTANCE, EnchantedPotato.MOD_ID + "-MarkFromTheBeneath.toml");
        container.registerConfig(ModConfig.Type.COMMON, ArmorBreakingConfig.INSTANCE, EnchantedPotato.MOD_ID + "-ArmorBreaking.toml");
        container.registerConfig(ModConfig.Type.COMMON, NatureBlessingConfig.INSTANCE, EnchantedPotato.MOD_ID + "-NatureBlessing.toml");
        container.registerConfig(ModConfig.Type.COMMON, CaressingMoonlightConfig.INSTANCE, EnchantedPotato.MOD_ID + "-CaressingMoonlight.toml");
        container.registerConfig(ModConfig.Type.COMMON, OceanHuedConfig.INSTANCE, EnchantedPotato.MOD_ID + "-OceanHued.toml");
        container.registerConfig(ModConfig.Type.COMMON, MineCarveConfig.INSTANCE, EnchantedPotato.MOD_ID + "-MineCarve.toml");
        container.registerConfig(ModConfig.Type.COMMON, UniteStonesOfAllConfig.INSTANCE, EnchantedPotato.MOD_ID + "-UniteStonesOfAll.toml");
        container.registerConfig(ModConfig.Type.COMMON, MendingMirrorConfig.INSTANCE, EnchantedPotato.MOD_ID + "-MendingMirror.toml");
        container.registerConfig(ModConfig.Type.COMMON, FinalPowerConfig.INSTANCE, EnchantedPotato.MOD_ID + "-FinalPower.toml");
        container.registerConfig(ModConfig.Type.COMMON, SpaceLeapfrogConfig.INSTANCE, EnchantedPotato.MOD_ID + "-SpaceLeapfrog.toml");

        DisableConfig.init();

        if (FMLLoader.getDist().isClient()) {
            container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.INSTANCE, EnchantedPotato.MOD_ID + "-client-Rendering.toml");
        }
    }
}
