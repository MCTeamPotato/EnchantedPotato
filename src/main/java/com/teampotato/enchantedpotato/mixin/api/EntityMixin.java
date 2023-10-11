package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.IEntity;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntity {
    @Shadow private int remainingFireTicks;

    @Shadow public abstract void setRemainingFireTicks(int remainingFireTicks);

    @Mutable @Shadow @Final private Set<String> tags;

    @Override
    public void ep$setTicksOnFire(int ticks) {
        if (ep$getThis() instanceof LivingEntity) ticks = ProtectionEnchantment.getFireAfterDampener((LivingEntity)ep$getThis(), ticks);
        if (this.remainingFireTicks < ticks) this.setRemainingFireTicks(ticks);
    }

    @Unique
    private Entity ep$getThis() {
        return (Entity)(Object)this;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.tags = new ObjectOpenHashSet<>(this.tags);
    }
}
