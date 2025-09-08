package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractOverlay implements LayeredDraw.Layer {

    public abstract String getTranslateKey();

    public abstract int getX();

    public abstract int getYOffSet();

    public abstract double getValue(ExtendedPlayer player);

    public abstract boolean isTicks();

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        double value = getValue((ExtendedPlayer) mc.player);

        if (value > 0) {
            double displayNumber = isTicks() ? Math.ceil(value / 20.0) : value;
            Component text = Component.translatable(getTranslateKey(), displayNumber);
            int yPos = mc.getWindow().getGuiScaledHeight() - getYOffSet();
            guiGraphics.drawString(mc.font, text, getX(), yPos, 0xFFA500, true);
        }
    }
}
