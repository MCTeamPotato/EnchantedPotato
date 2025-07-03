package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public abstract class AbstractOverlay implements IGuiOverlay {

    public abstract String getTranslateKey();

    public abstract int getX();

    public abstract int getYOffSet();

    public abstract double getValue(ExtendedPlayer player);

    public abstract boolean isTicks();

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        double value = getValue((ExtendedPlayer) mc.player);

        if (value > 0) {
            double displayNumber;
            if (!isTicks()) {
                displayNumber = value;
            } else {
                displayNumber = Math.ceil(value / 20.0);
            }
            Component text = Component.translatable(getTranslateKey(), displayNumber);
            int yPos = screenHeight - getYOffSet();
            guiGraphics.drawString(mc.font, text, getX(), yPos, 0xFFA500, true);
        }
    }
}
