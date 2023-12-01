package com.teampotato.enchantedpotato.mixin.sniper;

import com.teampotato.enchantedpotato.EnchantedPotato;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    @Inject(method = "getShootingPower", at = @At("RETURN"), cancellable = true)
    private static void onGetPower(@NotNull ItemStack crossbowStack, CallbackInfoReturnable<Float> cir) {
        int level = crossbowStack.getEnchantmentLevel(EnchantedPotato.EnchantedRegistries.SNIPER.get());
        if (level > 0) cir.setReturnValue(cir.getReturnValue() * (1.0F + EnchantedPotato.EnchantedConfig.SNIPER_ARROW_SPEED_INCREASE_PER_LEVEL.get().floatValue() * level));
    }
}
