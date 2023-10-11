package com.teampotato.enchantedpotato.api;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public interface ILivingEntity {
    void ep$setShouldTrackRunLikeHell(boolean shouldTrackRunLikeHell);
    void ep$setRunLikeHellTickCount(int runLikeHellTickCount);
    boolean ep$shouldTrackRunLikeHell();
    int ep$getRunLikeHellTickCount();
    int ep$getDyingOfLightTickCount();
    void ep$setDyingOfLightTickCount(int tickCount);
    boolean ep$shouldTickDyingOfLight();
    int ep$getNatureBlessedTickCount();
    void ep$setNatureBlessedTickCount(int tickCount);
    boolean ep$shouldTickNatureBlessed();
    boolean ep$ShouldTickUntouchable();
    int ep$getUntouchableTickCount();
    void ep$setUntouchableTickCount(int tickCount);


    //Need a new interface, or it will be a mess.
    interface OceanHued {
        boolean ep$getOceanHuedTimerStatus();
        void ep$setOceanHuedTimerStatus(boolean status);
        void ep$resetOceanHuedTimer();
        int ep$getOceanHuedTimerTickCount();
        void ep$setOceanHuedTimerTickCount(int tickCount);
        boolean ep$getIsOceanHuedAttackReady();
        void ep$setIsOceanHuedAttackReady(boolean ready);
        float ep$getOceanHuedHealingAmount();
        void ep$setOceanHuedHealingAmount(float amount);
        int ep$getOceanHuedCoolDownTickCount();
        void ep$setOceanHuedCoolDownTickCount(int tickCount);
    }
}
