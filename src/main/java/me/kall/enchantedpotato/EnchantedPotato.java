package me.kall.enchantedpotato;

import me.kall.enchantedpotato.client.registry.ClientModEvents;
import me.kall.enchantedpotato.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Mod(EnchantedPotato.MOD_ID)
public final class EnchantedPotato {
    public static final String MOD_ID = "enchantedpotato";
    public static final String MOD_NAME = "EnchantedPotato";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public EnchantedPotato(IEventBus modBus, @NotNull Dist dist, ModContainer container) {
        IEventBus forgeBus = NeoForge.EVENT_BUS;

        ModAttributes.register(modBus);

        ModEvents.register(forgeBus);
        if (dist.isClient()) ClientModEvents.register(modBus, forgeBus);

        ModConfigs.register(container);

        modBus.addListener(ModPackets::register);

        LOGGER.info("Oh, potato, I'm enchanted by you.");
    }

    @Contract("_ -> new")
    public static @NotNull ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
