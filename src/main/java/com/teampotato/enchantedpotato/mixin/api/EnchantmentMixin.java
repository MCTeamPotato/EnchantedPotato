package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.IEnchantment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements IEnchantment {
    @Unique
    private ResourceLocation ep$registryName = null;

    @Override
    @Nullable
    public ResourceLocation ep$getRegistryName() {
        if (this.ep$registryName != null) return this.ep$registryName;
        this.ep$registryName = ForgeRegistries.ENCHANTMENTS.getKey((Enchantment)(Object)this);
        return this.ep$registryName;
    }
}
