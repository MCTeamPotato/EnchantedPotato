package me.kall.enchantedpotato.common.mixin.disable;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin  {
    @Shadow public abstract ItemEnchantments getTagEnchantments();

    @Unique private boolean enchant$checked;

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void check(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, CallbackInfo ci) {
        if (this.getTagEnchantments().isEmpty()) {
            enchant$checked = true;
            return;
        }

        if (enchant$checked) return;
        enchant$checked = true;
        for (Holder<Enchantment> enchantmentHolder : new ObjectOpenHashSet<>(this.getTagEnchantments().keySet())) {
            Optional.ofNullable(BaseEnchantment.getBase(enchantmentHolder)).ifPresent(baseEnchantment -> {
                if (baseEnchantment.isDisabled()) {
                    BaseEnchantment.removeEnchantment((ItemStack) (Object) this, enchantmentHolder.value());
                }
            });
        }
    }

    @Inject(method = "enchant", at = @At("HEAD"), cancellable = true)
    private void onEnchant(Holder<Enchantment> enchantment, int level, CallbackInfo ci) {
        Optional.ofNullable(BaseEnchantment.getBase(enchantment)).ifPresent(baseEnchantment -> {
            if (baseEnchantment.isDisabled()) ci.cancel();
        });
    }
}
