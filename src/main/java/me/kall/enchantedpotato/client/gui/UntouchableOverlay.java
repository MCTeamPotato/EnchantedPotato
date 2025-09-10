package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;

public class UntouchableOverlay extends AbstractOverlay {
    @Override
    public String getTranslateKey() {
        return "info.enchantedpotato.untouchable";
    }

    @Override
    public int getX() {
        return ClientConfig.UNTOUCHABLE_POS_X.get();
    }

    @Override
    public int getYOffSet() {
        return ClientConfig.UNTOUCHABLE_POS_Y_OFFSET.get();
    }

    @Override
    public double getValue(ExtendedPlayer player) {
        return player.untouchable$getCoolDown();
    }

    @Override
    public boolean isTicks() {
        return true;
    }
}
