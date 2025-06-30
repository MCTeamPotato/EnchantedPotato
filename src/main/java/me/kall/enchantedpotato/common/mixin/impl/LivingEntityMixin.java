package me.kall.enchantedpotato.common.mixin.impl;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ExtendedLivingEntity {
    @Shadow @Nullable protected Player lastHurtByPlayer;

    @Override
    public @Nullable Player enchantedPotato$getLastHurtByPlayer() {
        return this.lastHurtByPlayer;
    }
}
