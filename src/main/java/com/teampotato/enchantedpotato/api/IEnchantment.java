package com.teampotato.enchantedpotato.api;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface IEnchantment {
    @Nullable
    ResourceLocation ep$getRegistryName();
}
