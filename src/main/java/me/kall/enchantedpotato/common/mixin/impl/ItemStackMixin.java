package me.kall.enchantedpotato.common.mixin.impl;

import me.kall.enchantedpotato.common.api.Weapon;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IItemStackExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IItemStackExtension {
    @Shadow public abstract Item getItem();

    @Inject(method = {"<init>(Ljava/lang/Void;)V", "<init>(Lnet/minecraft/world/level/ItemLike;ILnet/minecraft/core/component/PatchedDataComponentMap;)V"}, at = @At("RETURN"))
    private void initWeapon(CallbackInfo ci) {
        if (((Weapon)this.getItem()).item$tested()) return;
        ((Weapon)this.getItem()).item$setTested();

        if (this.getAttributeModifiers().modifiers().stream().anyMatch(entry -> entry.attribute().value().equals(Attributes.ATTACK_DAMAGE.value()))) {
            ((Weapon)this.getItem()).item$setAsWeapon();
        }
    }
}
