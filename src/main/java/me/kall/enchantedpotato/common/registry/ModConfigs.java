package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.config.*;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

public class ModConfigs {
    public static void register(@NotNull FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.COMMON, RunLikeHellConfig.INSTANCE, EnchantedPotato.MOD_ID + "/RunLikeHell.toml");
        context.registerConfig(ModConfig.Type.COMMON, BlackParadeConfig.INSTANCE, EnchantedPotato.MOD_ID + "/BlackParade.toml");
        context.registerConfig(ModConfig.Type.COMMON, UntouchableConfig.INSTANCE, EnchantedPotato.MOD_ID + "/Untouchable.toml");
        context.registerConfig(ModConfig.Type.COMMON, GraceOfGaiaConfig.INSTANCE, EnchantedPotato.MOD_ID + "/GraceOfGaia.toml");
        context.registerConfig(ModConfig.Type.COMMON, GurenNoYumiyaConfig.INSTANCE, EnchantedPotato.MOD_ID + "/GurenNoYumiya.toml");
        context.registerConfig(ModConfig.Type.COMMON, LoRATrainerConfig.INSTANCE, EnchantedPotato.MOD_ID + "/LoRATrainer.toml");
        context.registerConfig(ModConfig.Type.COMMON, RippleOfDeathConfig.INSTANCE, EnchantedPotato.MOD_ID + "/RippleOfDeath.toml");
        context.registerConfig(ModConfig.Type.COMMON, DissolveConfig.INSTANCE, EnchantedPotato.MOD_ID + "/Dissolve.toml");
    }
}
