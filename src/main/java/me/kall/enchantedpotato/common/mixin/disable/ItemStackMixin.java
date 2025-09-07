package me.kall.enchantedpotato.common.mixin.disable;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IForgeItemStack {
    @Shadow public abstract boolean hasTag();

    @Unique private boolean disable$shouldCheck = true;

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void onTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, CallbackInfo ci) {
        if (this.disable$shouldCheck) {
            if (this.hasTag()) {
                for (Enchantment enchantment : this.getAllEnchantments().keySet()) {
                    if (enchantment instanceof BaseEnchantment baseEnchantment && baseEnchantment.isDisabled()) {
                        BaseEnchantment.removeEnchantment((ItemStack) (Object) this, enchantment);
                    }
                }
            }
            this.disable$shouldCheck = false;
        }
    }

    @Inject(method = "enchant", at = @At("HEAD"), cancellable = true)
    private void onEnchantStart(Enchantment enchantment, int level, CallbackInfo ci) {
        if (enchantment instanceof BaseEnchantment baseEnchantment && baseEnchantment.isDisabled()) ci.cancel();
    }
}
