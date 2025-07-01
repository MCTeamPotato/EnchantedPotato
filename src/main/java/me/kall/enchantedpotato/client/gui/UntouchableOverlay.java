package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.client.gui.api.Overlay;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class UntouchableOverlay extends Overlay implements IGuiOverlay {
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
    public int getCoolDownTicks(ExtendedPlayer player) {
        return player.untouchable$getCoolDown();
    }
}
