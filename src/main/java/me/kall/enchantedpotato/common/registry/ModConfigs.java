package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.config.*;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
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
        context.registerConfig(ModConfig.Type.COMMON, PressurizedCollapseConfig.INSTANCE, EnchantedPotato.MOD_ID + "/PressurizedCollapse.toml");
        context.registerConfig(ModConfig.Type.COMMON, MarkFromTheBeneathConfig.INSTANCE, EnchantedPotato.MOD_ID + "/MarkFromTheBeneath.toml");

        if (FMLLoader.getDist().isClient()) {
            context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.INSTANCE, EnchantedPotato.MOD_ID + "/client/Rendering.toml");
        }
    }
}
