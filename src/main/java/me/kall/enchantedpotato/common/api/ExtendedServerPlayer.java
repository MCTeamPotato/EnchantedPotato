package me.kall.enchantedpotato.common.api;

public interface ExtendedServerPlayer {
    boolean runLikeHell$isInCoolDown();
    void runLikeHell$setCoolDown(int coolDown);

    boolean untouchable$isInCoolDown();
    void untouchable$setCoolDown(int coolDown);
}
