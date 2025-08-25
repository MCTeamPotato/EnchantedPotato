package me.kall.enchantedpotato.common.api;

public interface ExtendedPlayer {
    boolean runLikeHell$isInCoolDown();
    int runLikeHell$getCoolDown();
    void runLikeHell$setCoolDown(int coolDown);

    boolean untouchable$isInCoolDown();
    int untouchable$getCoolDown();
    void untouchable$setCoolDown(int coolDown);

    boolean oceanHued$isInCounting();
    int oceanHued$getCountingTicks();
    void oceanHued$setCountingTicks(double countingTicks);
    boolean oceanHued$isReady();

    double oceanHued$getHealingAmount();
    void oceanHued$setHealingAmount(double healingAmount);

    boolean oceanHued$isInCoolDown();
    int oceanHued$getCoolDown();
    void oceanHued$setCoolDown(int coolDown);

    boolean spaceLeapfrog$isInCoolDown();
    int spaceLeapfrog$getCoolDown();
    void spaceLeapfrog$setCoolDown(int coolDown);
}
