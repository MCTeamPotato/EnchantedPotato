package me.kall.enchantedpotato.common.mixin.disable;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookItemMixin {
    @Inject(method = "addEnchantment", at = @At("HEAD"), cancellable = true)
    private static void onEnchant(ItemStack stack, EnchantmentInstance instance, CallbackInfo ci) {
        if (instance.enchantment instanceof BaseEnchantment baseEnchantment && baseEnchantment.isDisabled()) ci.cancel();
    }
}
