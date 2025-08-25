package me.kall.enchantedpotato;

import me.kall.enchantedpotato.client.registry.ClientModEvents;
import me.kall.enchantedpotato.common.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(EnchantedPotato.MOD_ID)
public final class EnchantedPotato {
    public static final String MOD_ID = "enchantedpotato";
    public static final String MOD_NAME = "EnchantedPotato";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public EnchantedPotato(@NotNull FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ModEnchantments.register(modBus);
        ModAttributes.register(modBus);

        ModEvents.register(forgeBus);
        if (FMLLoader.getDist().isClient()) ClientModEvents.register(modBus, forgeBus);

        ModConfigs.register(context);
        ModPackets.register();
        LOGGER.info("Oh, potato, I'm enchanted by you.");
    }
}
