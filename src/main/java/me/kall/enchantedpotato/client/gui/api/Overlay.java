package me.kall.enchantedpotato.client.gui.api;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public abstract class Overlay implements IGuiOverlay {

    public abstract String getTranslateKey();

    public abstract int getX();

    public abstract int getYOffSet();

    public abstract int getCoolDownTicks(ExtendedPlayer player);

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int coolDownTick = getCoolDownTicks((ExtendedPlayer) mc.player);

        if (coolDownTick > 0) {
            int seconds = (int) Math.ceil(coolDownTick / 20.0);
            Component text = Component.translatable(getTranslateKey(), seconds);
            int yPos = screenHeight - getYOffSet();
            guiGraphics.drawString(mc.font, text, getX(), yPos, 0xFFA500, true);
        }
    }
}
