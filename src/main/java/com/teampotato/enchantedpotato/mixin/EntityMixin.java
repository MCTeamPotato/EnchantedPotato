package com.teampotato.enchantedpotato.mixin;

import com.teampotato.enchantedpotato.api.IEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntity {
    @Shadow private int remainingFireTicks;

    @Shadow public abstract void setRemainingFireTicks(int remainingFireTicks);

    @SuppressWarnings("ConstantValue")
    @Override
    public void ep$setTicksOnFire(int ticks) {
        if (((Object)this) instanceof LivingEntity) ticks = ProtectionEnchantment.getFireAfterDampener((LivingEntity)(Object)this, ticks);
        if (this.remainingFireTicks < ticks) this.setRemainingFireTicks(ticks);
    }
}
