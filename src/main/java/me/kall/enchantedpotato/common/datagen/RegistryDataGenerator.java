package me.kall.enchantedpotato.common.datagen;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", EnchantedPotato.MOD_ID));
    }
}
