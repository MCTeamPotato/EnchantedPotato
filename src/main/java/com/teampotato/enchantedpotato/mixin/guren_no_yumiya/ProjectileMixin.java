package com.teampotato.enchantedpotato.mixin.guren_no_yumiya;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.IEntity;
import com.teampotato.enchantedpotato.Constants;
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
    public ProjectileMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }

    @Inject(method = "onHit", at = @At("RETURN"))
    private void onHit(HitResult result, CallbackInfo ci) {
        if (result == null) return;
        Vec3 location = result.getLocation();
        double x = location.x;
        double y = location.y;
        double z = location.z;
        if (this.removeTag(EarlySetupInitializer.MOD_ID + ".fireArrow")) {
                for (Monster entity : this.level().getEntitiesOfClass(Monster.class, new AABB(x - Constants.gurenNoYumiyaX, y - Constants.gurenNoYumiyaY, z - Constants.gurenNoYumiyaZ, x + Constants.gurenNoYumiyaX, y + Constants.gurenNoYumiyaY, z + Constants.gurenNoYumiyaZ))) {
                    ((IEntity)entity).ep$setTicksOnFire(EnchantedPotato.GUREN_NO_YUMIYA_FIRE_DURATION_TICKS.get());
                }
            }
    }
}
