package me.kall.enchantedpotato.common.api;

public interface ExtendedPlayer {
    boolean runLikeHell$isInCoolDown();
    int runLikeHell$getCoolDown();
    void runLikeHell$setCoolDown(int coolDown);

    boolean untouchable$isInCoolDown();
    int untouchable$getCoolDown();
    void untouchable$setCoolDown(int coolDown);
}
