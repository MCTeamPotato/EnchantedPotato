package me.kall.enchantedpotato.common.mixin.finalpower;

import me.kall.enchantedpotato.common.config.FinalPowerConfig;
import me.kall.enchantedpotato.common.enchantment.weapon.FinalPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At(value = "HEAD"), cancellable = true)
    private void onAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if (source.getEntity() instanceof LivingEntity livingEntity && FinalPower.has(livingEntity)) {
            cir.setReturnValue(amount);
        }
    }

    @Inject(method = "getDamageAfterMagicAbsorb", at = @At("HEAD"), cancellable = true)
    private void onAbsorbMagic(DamageSource damageSource, float damageAmount, CallbackInfoReturnable<Float> cir) {
        if (damageSource.getEntity() instanceof LivingEntity entity && FinalPower.has(entity)) {
            cir.setReturnValue(damageAmount);
        }
    }

    @Inject(method = "isDamageSourceBlocked", at = @At("HEAD"), cancellable = true)
    private void onCheckShield(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (FinalPowerConfig.DISABLE_SHIELD.get() && source.getEntity() instanceof LivingEntity livingEntity && FinalPower.has(livingEntity)) {
            cir.setReturnValue(false);
        }
    }
}
