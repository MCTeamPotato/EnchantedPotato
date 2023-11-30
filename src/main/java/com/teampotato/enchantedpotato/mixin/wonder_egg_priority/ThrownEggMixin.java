package com.teampotato.enchantedpotato.mixin.wonder_egg_priority;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.armor.head.WonderEggPriority;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThrownEgg.class)
public abstract class ThrownEggMixin extends ThrowableItemProjectile {
    public ThrownEggMixin(EntityType<? extends ThrowableItemProjectile> arg, Level arg2) {
        super(arg, arg2);
    }

    @ModifyConstant(method = "onHitEntity", constant = @Constant(floatValue = 0.0f))
    private float onHitEntity(float constant) {
        Entity owner = this.getOwner();
        return owner instanceof LivingEntity &&
                Utils.hasPotatoEnchantmentEquipped((LivingEntity) owner, EarlySetupInitializer.equipmentSlotConfig.wonderEggPriority, WonderEggPriority.PATH) ?
                EnchantedPotato.WONDER_EGG_PRIORITY_EGG_DAMAGE_VALUE.get().floatValue() :
                constant;
    }
}
