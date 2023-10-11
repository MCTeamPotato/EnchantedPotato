package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.IEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntity {
    @Shadow private int remainingFireTicks;

    @Shadow public abstract void setRemainingFireTicks(int remainingFireTicks);

    @Override
    public void ep$setTicksOnFire(int ticks) {
        if (ep$getThis() instanceof LivingEntity) ticks = ProtectionEnchantment.getFireAfterDampener((LivingEntity)ep$getThis(), ticks);
        if (this.remainingFireTicks < ticks) this.setRemainingFireTicks(ticks);
    }

    @Unique
    private Entity ep$getThis() {
        return (Entity)(Object)this;
    }
}