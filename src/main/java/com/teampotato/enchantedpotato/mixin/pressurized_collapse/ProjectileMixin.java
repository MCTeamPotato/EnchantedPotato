package com.teampotato.enchantedpotato.mixin.pressurized_collapse;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.util.Constants;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends Entity {
    @SuppressWarnings("resource")
    @Inject(method = "onHit", at = @At("RETURN"))
    private void onHit(HitResult result, CallbackInfo ci) {
        if (result == null) return;
        Vec3 location = result.getLocation();
        double x = location.x;
        double y = location.y;
        double z = location.z;
        String gravityTag = "";
        for (String tag : this.getTags()) {
            if (tag.startsWith(EarlySetupInitializer.MOD_ID + ".gravityArrow.")) {
                gravityTag = tag.split("\\.")[2];
                break;
            }
        }
        if (!gravityTag.isEmpty()) {
            double additionalChargeSeconds = (double) Long.parseLong(gravityTag) / 20.00;
            if (additionalChargeSeconds > DetailsConfig.PRESSURIZED_COLLAPSE_MAX_RANGE_ADDITION.get()) additionalChargeSeconds = DetailsConfig.PRESSURIZED_COLLAPSE_MAX_RANGE_ADDITION.get();
            for (Monster entity : this.level().getEntitiesOfClass(Monster.class, new AABB(x - Constants.pressurizedCollapseX - additionalChargeSeconds, y - Constants.pressurizedCollapseY - additionalChargeSeconds, z - Constants.pressurizedCollapseZ - additionalChargeSeconds, x + Constants.pressurizedCollapseX + additionalChargeSeconds, y + Constants.pressurizedCollapseY + additionalChargeSeconds, z + Constants.pressurizedCollapseZ + additionalChargeSeconds))) {
                Vec3 entityPos = entity.position();
                entity.setDeltaMovement(result.getLocation());
                if (this.level().isClientSide) this.level().addParticle(ParticleTypes.PORTAL, x, y, z, entityPos.x, entityPos.y, entityPos.z);
            }
        }
    }

    public ProjectileMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }
}
