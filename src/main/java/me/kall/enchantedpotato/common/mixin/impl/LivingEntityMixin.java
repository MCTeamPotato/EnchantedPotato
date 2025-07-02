package me.kall.enchantedpotato.common.mixin.impl;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.registry.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ExtendedLivingEntity {
    @Shadow @Nullable protected Player lastHurtByPlayer;

    @Shadow @Nullable public abstract AttributeInstance getAttribute(Attribute attribute);

    @Unique private int armorBreaking$interval = 0, natureBlessing$interval = 0;

    @Override
    public @Nullable Player enchantedPotato$getLastHurtByPlayer() {
        return this.lastHurtByPlayer;
    }

    @Override
    public AttributeInstance armorBreaking$getAttribute() {
        return this.getAttribute(ModAttributes.ARMOR_BREAKING_DURATION.get());
    }

    @Override
    public int armorBreaking$getInterval() {
        return this.armorBreaking$interval;
    }

    @Override
    public void armorBreaking$bumpInterval() {
        this.armorBreaking$interval++;
    }

    @Override
    public void armorBreaking$clearInterval() {
        this.armorBreaking$interval = 0;
    }

    @Override
    public int natureBlessing$getInterval() {
        return this.natureBlessing$interval;
    }

    @Override
    public void natureBlessing$setInterval(int interval) {
        this.natureBlessing$interval = interval;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (this.natureBlessing$getInterval() == 0) return;
        this.natureBlessing$setInterval(this.natureBlessing$getInterval() - 1);
    }
}
