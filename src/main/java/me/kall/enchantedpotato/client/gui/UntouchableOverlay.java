package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class UntouchableOverlay implements IGuiOverlay {
    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int coolDownTick = ((ExtendedPlayer)mc.player).untouchable$getCoolDown();

        if (coolDownTick > 0) {
            int seconds = (int) Math.ceil(coolDownTick / 20.0);
            Component text = Component.translatable("info.enchantedpotato.untouchable", seconds);
            int yPos = screenHeight - ClientConfig.UNTOUCHABLE_POS_Y_OFFSET.get();
            guiGraphics.drawString(mc.font, text, ClientConfig.UNTOUCHABLE_POS_X.get(), yPos, 0xFFA500, true);
        }
    }
}
