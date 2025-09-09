package me.kall.enchantedpotato.common.datagen;

import me.kall.enchantedpotato.EnchantedPotato;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EnchantedPotato.MOD_ID)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput output = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        dataGenerator.addProvider(event.includeServer(), new RegistryDataGenerator(output, lookupProvider));
    }
}
