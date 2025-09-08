package me.kall.enchantedpotato.common.mixin.lawofinertia;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.enchantment.weapon.LawOfInertia;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V"))
    private void onKnockback(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getEntity() instanceof Player player) {
            int enchantmentLevel = BaseEnchantment.getLevelInHands(ModEnchantments.LAW_OF_INERTIA, player, player.level());
            if (enchantmentLevel == 0) return;
            this.getPersistentData().putFloat(LawOfInertia.MARK, amount);
        }
    }

    @Inject(method = "push", at = @At("HEAD"))
    private void onPush(Entity entity, CallbackInfo ci) {
        float amount = this.getPersistentData().getFloat(LawOfInertia.MARK);
        if (amount != 0.0F && this.level() instanceof ServerLevel && entity instanceof LivingEntity living) {
            float damage = amount / 4.0F;
            if (damage < 1.0F) damage = 1.0F;
            living.hurt(this.damageSources().generic(), damage);
        }
    }
}
