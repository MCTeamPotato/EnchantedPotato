package com.teampotato.enchantedpotato.mixin.triback;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.trident.Triback;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrownTrident.class)
public abstract class ThrownTridentMixin {
    @Unique
    private boolean ep$isTriback;

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)V", at = @At("RETURN"))
    private void onInit(Level level, LivingEntity shooter, ItemStack stack, CallbackInfo ci) {
        this.ep$isTriback = EnchantedPotato.EnchantedUtils.hasPotatoEnchantmentEquipped(shooter, EarlySetupInitializer.equipmentSlotConfig.triback, Triback.PATH);
    }

    @Inject(method = "getWaterInertia", at = @At("RETURN"), cancellable = true)
    private void onGetInertia(CallbackInfoReturnable<Float> cir) {
        if (this.ep$isTriback) cir.setReturnValue(1.0F);
    }
}
