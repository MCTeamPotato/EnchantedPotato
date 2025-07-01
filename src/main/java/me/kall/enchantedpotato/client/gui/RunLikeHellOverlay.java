package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class RunLikeHellOverlay extends AbstractOverlay implements IGuiOverlay {
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
    public int getCoolDownTicks(ExtendedPlayer player) {
        return player.runLikeHell$getCoolDown();
    }
}