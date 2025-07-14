package me.kall.enchantedpotato.common.mixin.sniper;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.shotFromCrossbow() && arrow.getPierceLevel() > 0 && arrow.getOwner() instanceof LivingEntity entity) {
            int level = Math.max(entity.getMainHandItem().getEnchantmentLevel(ModEnchantments.SNIPER.get()), entity.getOffhandItem().getEnchantmentLevel(ModEnchantments.SNIPER.get()));
            if (level == 0) return;

            double multiplier = 1.0D + 0.2D * (double) level;
            Vec3 newMotion = new Vec3(x * multiplier, y * multiplier, z * multiplier);
            arrow.setDeltaMovement(newMotion);

            arrow.setYRot((float) (Math.atan2(newMotion.x, newMotion.z) * (180F / (float) Math.PI)));
            arrow.setXRot((float) (Math.atan2(newMotion.y, newMotion.horizontalDistance()) * (180F / (float) Math.PI)));

            arrow.yRotO = arrow.getYRot();
            arrow.xRotO = arrow.getXRot();
        }
    }
}
