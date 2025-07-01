package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class RunLikeHellOverlay implements IGuiOverlay {
    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        int coolDownTicks = ((ExtendedPlayer)mc.player).runLikeHell$getCoolDown();

        if (coolDownTicks > 0) {
            int seconds = (int) Math.ceil(coolDownTicks / 20.0);
            Component text = Component.translatable("info.enchantedpotato.runlikehell", seconds);
            int yPos = screenHeight - ClientConfig.RUN_LIKE_HELL_POS_Y_OFFSET.get();
            guiGraphics.drawString(mc.font, text, ClientConfig.RUN_LIKE_HELL_POS_X.get(), yPos, 0xFFA500, true);
        }
    }
}