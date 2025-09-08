package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;

public class RunLikeHellOverlay extends AbstractOverlay {
    @Override
    public String getTranslateKey() {
        return "info.enchantedpotato.runlikehell";
    }

    @Override
    public int getX() {
        return ClientConfig.RUN_LIKE_HELL_POS_X.get();
    }

    @Override
    public int getYOffSet() {
        return ClientConfig.RUN_LIKE_HELL_POS_Y_OFFSET.get();
    }

    @Override
    public double getValue(ExtendedPlayer player) {
        return player.runLikeHell$getCoolDown();
    }

    @Override
    public boolean isTicks() {
        return true;
    }
}