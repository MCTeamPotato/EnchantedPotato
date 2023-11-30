package com.teampotato.enchantedpotato.mixin.goldfish_fireworks;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    @Inject(method = "shootProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private static void onShoot(Level level, LivingEntity shooter, InteractionHand hand, ItemStack crossbowStack, ItemStack ammoStack, float soundPitch, boolean isCreativeMode, float velocity, float inaccuracy, float projectileAngle, CallbackInfo ci) {
        int lvl = Utils.getPotatoEnchantmentLevel(shooter, EnchantedPotato.EnchantedRegistries.GOLDFISH_FIREWORKS.get(), EarlySetupInitializer.equipmentSlotConfig.goldfishFireworks);
        if (lvl > 0 && Utils.getRandom().nextDouble(0.000, 1.001) < EnchantedPotato.GOLDFISH_FIREWORKS_SHOOT_PERCENT_PER_LEVEL.get() * lvl) {
            Vec3 upVector = shooter.getUpVector(1.0F);
            Vector3f vector3f = shooter.getViewVector(1.0F).toVector3f().rotate((new Quaternionf()).setAngleAxis(projectileAngle * 0.017453292F, upVector.x, upVector.y, upVector.z));
            FireworkRocketEntity rocketEntity = new FireworkRocketEntity(level, ammoStack, shooter, shooter.getX(), shooter.getEyeY() - 0.25000000596046448, shooter.getZ(), true);
            rocketEntity.shoot(vector3f.x(), vector3f.y(), vector3f.z(), velocity, inaccuracy);
            level.addFreshEntity(rocketEntity);
        }
    }
}
