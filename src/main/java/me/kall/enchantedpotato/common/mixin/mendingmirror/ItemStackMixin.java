package me.kall.enchantedpotato.common.mixin.mendingmirror;

import me.kall.enchantedpotato.common.enchantment.weapon.MendingMirror;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "hurtAndBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private <T extends LivingEntity> void onItemBroken(int amount, T entity, Consumer<T> onBroken, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MENDING_MIRROR.get(), stack) > 0) {
            MendingMirror.recordBrokenItem(entity, stack);
        }
    }
}
