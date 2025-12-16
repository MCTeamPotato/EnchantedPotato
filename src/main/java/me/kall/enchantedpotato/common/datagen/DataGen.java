package me.kall.enchantedpotato.common.datagen;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EnchantedPotato.MOD_ID)
public class DataGen {
    public static final RegistrySetBuilder DATAPACK_BUILDER = new RegistrySetBuilder().add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public static HolderLookup.Provider createLookup() {
        RegistryAccess.Frozen registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return DATAPACK_BUILDER.build(registryAccess);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput output = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = CompletableFuture.supplyAsync(DataGen::createLookup, Util.backgroundExecutor());
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        dataGenerator.addProvider(event.includeServer(), new RegistryDataGenerator(output, lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new ModEnchantments.PotatoEnchantTags(output, lookupProvider, existingFileHelper));
    }
}
