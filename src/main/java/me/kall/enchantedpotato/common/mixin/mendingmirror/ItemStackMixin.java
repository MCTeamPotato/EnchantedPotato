package me.kall.enchantedpotato.common.mixin.mendingmirror;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.enchantment.weapon.MendingMirror;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void onItemBroken(int j, ServerLevel serverLevel, LivingEntity entity, Consumer<Item> consumer, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        if (BaseEnchantment.getLevel(stack, serverLevel, ModEnchantments.MENDING_MIRROR) > 0) {
            MendingMirror.recordBrokenItem(entity, stack);
        }
    }
}
