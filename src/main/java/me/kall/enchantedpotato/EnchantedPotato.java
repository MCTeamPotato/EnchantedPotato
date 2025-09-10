package me.kall.enchantedpotato;

import me.kall.enchantedpotato.client.registry.ClientRegistries;
import me.kall.enchantedpotato.common.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnchantedPotato.MOD_ID)
public final class EnchantedPotato {
    public static final String MOD_ID = "enchantedpotato";
    public static final String MOD_NAME = "EnchantedPotato";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public EnchantedPotato() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ModEnchantments.register(modBus);
        ModAttributes.register(modBus);

        ModEvents.register(forgeBus);
        if (FMLLoader.getDist().isClient()) ClientRegistries.registerEvents(forgeBus);

        ModConfigs.register(ModLoadingContext.get());
        ModPackets.register();
        LOGGER.info("Oh, potato, I'm enchanted by you.");
    }
}
