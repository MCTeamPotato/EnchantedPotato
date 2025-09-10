package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.config.*;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;

public class ModConfigs {
    public static void register(@NotNull ModLoadingContext context) {
        context.registerConfig(ModConfig.Type.COMMON, RunLikeHellConfig.INSTANCE, EnchantedPotato.MOD_ID + "/RunLikeHell.toml");
        context.registerConfig(ModConfig.Type.COMMON, BlackParadeConfig.INSTANCE, EnchantedPotato.MOD_ID + "/BlackParade.toml");
        context.registerConfig(ModConfig.Type.COMMON, UntouchableConfig.INSTANCE, EnchantedPotato.MOD_ID + "/Untouchable.toml");
        context.registerConfig(ModConfig.Type.COMMON, GraceOfGaiaConfig.INSTANCE, EnchantedPotato.MOD_ID + "/GraceOfGaia.toml");
        context.registerConfig(ModConfig.Type.COMMON, GurenNoYumiyaConfig.INSTANCE, EnchantedPotato.MOD_ID + "/GurenNoYumiya.toml");
        context.registerConfig(ModConfig.Type.COMMON, LoRATrainerConfig.INSTANCE, EnchantedPotato.MOD_ID + "/LoRATrainer.toml");
        context.registerConfig(ModConfig.Type.COMMON, RippleOfDeathConfig.INSTANCE, EnchantedPotato.MOD_ID + "/RippleOfDeath.toml");
        context.registerConfig(ModConfig.Type.COMMON, DissolveConfig.INSTANCE, EnchantedPotato.MOD_ID + "/Dissolve.toml");
        context.registerConfig(ModConfig.Type.COMMON, PressurizedCollapseConfig.INSTANCE, EnchantedPotato.MOD_ID + "/PressurizedCollapse.toml");
        context.registerConfig(ModConfig.Type.COMMON, MarkFromTheBeneathConfig.INSTANCE, EnchantedPotato.MOD_ID + "/MarkFromTheBeneath.toml");
        context.registerConfig(ModConfig.Type.COMMON, ArmorBreakingConfig.INSTANCE, EnchantedPotato.MOD_ID + "/ArmorBreaking.toml");
        context.registerConfig(ModConfig.Type.COMMON, NatureBlessingConfig.INSTANCE, EnchantedPotato.MOD_ID + "/NatureBlessing.toml");
        context.registerConfig(ModConfig.Type.COMMON, CaressingMoonlightConfig.INSTANCE, EnchantedPotato.MOD_ID + "/CaressingMoonlight.toml");
        context.registerConfig(ModConfig.Type.COMMON, OceanHuedConfig.INSTANCE, EnchantedPotato.MOD_ID + "/OceanHued.toml");
        context.registerConfig(ModConfig.Type.COMMON, MineCarveConfig.INSTANCE, EnchantedPotato.MOD_ID + "/MineCarve.toml");
        context.registerConfig(ModConfig.Type.COMMON, UniteStonesOfAllConfig.INSTANCE, EnchantedPotato.MOD_ID + "/UniteStonesOfAll.toml");
        context.registerConfig(ModConfig.Type.COMMON, MendingMirrorConfig.INSTANCE, EnchantedPotato.MOD_ID + "/MendingMirror.toml");
        context.registerConfig(ModConfig.Type.COMMON, FinalPowerConfig.INSTANCE, EnchantedPotato.MOD_ID + "/FinalPower.toml");
        context.registerConfig(ModConfig.Type.COMMON, SpaceLeapfrogConfig.INSTANCE, EnchantedPotato.MOD_ID + "/SpaceLeapfrog.toml");

        context.registerConfig(ModConfig.Type.COMMON, DisableConfig.INSTANCE, EnchantedPotato.MOD_ID + "/disable/Disable.toml");

        if (FMLLoader.getDist().isClient()) {
            context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.INSTANCE, EnchantedPotato.MOD_ID + "/client/Rendering.toml");
        }
    }
}
