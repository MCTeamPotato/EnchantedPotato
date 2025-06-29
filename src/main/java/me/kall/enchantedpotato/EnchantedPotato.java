package me.kall.enchantedpotato;

import me.kall.enchantedpotato.common.registry.ModConfigs;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import me.kall.enchantedpotato.common.registry.ModEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(EnchantedPotato.MOD_ID)
public final class EnchantedPotato {
    public static final String MOD_ID = "enchantedpotato";
    public static final String MOD_NAME = "EnchantedPotato";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public EnchantedPotato(@NotNull FMLJavaModLoadingContext context) {
        ModEnchantments.register(context.getModEventBus());
        ModEvents.register(MinecraftForge.EVENT_BUS);
        ModConfigs.register(context);
        LOGGER.info("Oh, potato, I'm enchanted by you.");
    }
}
