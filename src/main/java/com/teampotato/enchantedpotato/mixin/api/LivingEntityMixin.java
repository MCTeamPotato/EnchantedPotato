package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements ILivingEntity, ILivingEntity.OceanHued {
    @Unique private int ep$dyingOfLightTickCount;

    @Unique private boolean ep$shouldTrackRunLikeHell;

    @Unique private int ep$runLikeHellTickCount;

    @Unique private int ep$natureBlessedTickCount;

    @Unique private int ep$untouchableTickCount;

    @Unique private boolean ep$oceanHuedTimerStatus;
    @Unique private int ep$oceanHuedTimerTickCount;
    @Unique private boolean ep$oceanHuedAttackReady;
    @Unique private float ep$oceanHuedHealingAmount;
    @Unique private int ep$oceanHuedCoolDownTickCount;

    @Override
    public int ep$getDyingOfLightTickCount() {
        return this.ep$dyingOfLightTickCount;
    }

    @Override
    public boolean ep$shouldTickDyingOfLight() {
        return this.ep$getDyingOfLightTickCount() % DetailsConfig.DYING_OF_LIGHT_DETECTION_TICK_INTERVAL.get() == 0;
    }

    @Override
    public int ep$getNatureBlessedTickCount() {
        return this.ep$natureBlessedTickCount;
    }

    @Override
    public void ep$setNatureBlessedTickCount(int tickCount) {
        this.ep$natureBlessedTickCount = tickCount;
    }

    @Override
    public boolean ep$shouldTickNatureBlessed() {
        return this.ep$getNatureBlessedTickCount() % DetailsConfig.BLESSING_OF_THE_NATURE_HEALING_INTERVAL_TICK.get() == 0;
    }

    @Override
    public boolean ep$ShouldTickUntouchable() {
        return this.ep$untouchableTickCount % DetailsConfig.UNTOUCHABLE_COOL_DOWN.get() == 0;
    }

    @Override
    public int ep$getUntouchableTickCount() {
        return this.ep$untouchableTickCount;
    }

    @Override
    public void ep$setUntouchableTickCount(int tickCount) {
        this.ep$untouchableTickCount = tickCount;
    }

    @Override
    public boolean ep$getOceanHuedTimerStatus() {
        return this.ep$oceanHuedTimerStatus;
    }

    @Override
    public void ep$setOceanHuedTimerStatus(boolean status) {
        this.ep$oceanHuedTimerStatus = status;
    }

    @Override
    public void ep$resetOceanHuedTimer() {
        this.ep$oceanHuedTimerStatus = false;
        this.ep$oceanHuedTimerTickCount = 0;
    }

    @Override
    public int ep$getOceanHuedTimerTickCount() {
        return this.ep$oceanHuedTimerTickCount;
    }

    @Override
    public void ep$setOceanHuedTimerTickCount(int tickCount) {
        this.ep$oceanHuedTimerTickCount = tickCount;
    }

    @Override
    public boolean ep$getIsOceanHuedAttackReady() {
        return this.ep$oceanHuedAttackReady;
    }

    @Override
    public void ep$setIsOceanHuedAttackReady(boolean ready) {
        this.ep$oceanHuedAttackReady = ready;
    }

    @Override
    public float ep$getOceanHuedHealingAmount() {
        return this.ep$oceanHuedHealingAmount;
    }

    @Override
    public void ep$setOceanHuedHealingAmount(float amount) {
        this.ep$oceanHuedHealingAmount = amount;
    }

    @Override
    public int ep$getOceanHuedCoolDownTickCount() {
        return this.ep$oceanHuedCoolDownTickCount;
    }

    @Override
    public void ep$setOceanHuedCoolDownTickCount(int tickCount) {
        this.ep$oceanHuedCoolDownTickCount = tickCount;
    }

    @Override
    public void ep$setDyingOfLightTickCount(int tickCount) {
        this.ep$dyingOfLightTickCount = tickCount;
    }

    @Override
    public void ep$setShouldTrackRunLikeHell(boolean shouldTrackRunLikeHell) {
        this.ep$shouldTrackRunLikeHell = shouldTrackRunLikeHell;
    }

    @Override
    public void ep$setRunLikeHellTickCount(int runLikeHellTickCount) {
        this.ep$runLikeHellTickCount = runLikeHellTickCount;
    }

    @Override
    public boolean ep$shouldTrackRunLikeHell() {
        return this.ep$shouldTrackRunLikeHell;
    }

    @Override
    public int ep$getRunLikeHellTickCount() {
        return this.ep$runLikeHellTickCount;
    }
}
