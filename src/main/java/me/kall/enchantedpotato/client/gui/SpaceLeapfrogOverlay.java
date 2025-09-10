package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;

public class SpaceLeapfrogOverlay extends AbstractOverlay {
    @Override
    public String getTranslateKey() {
        return "info.enchantedpotato.space_leapfrog";
    }

    @Override
    public int getX() {
        return ClientConfig.SPACE_LEAPFROG_POS_X.get();
    }

    @Override
    public int getYOffSet() {
        return ClientConfig.SPACE_LEAPFROG_POS_Y_OFFSET.get();
    }

    @Override
    public double getValue(ExtendedPlayer player) {
        return player.spaceLeapfrog$getCoolDown();
    }

    @Override
    public boolean isTicks() {
        return true;
    }
}
