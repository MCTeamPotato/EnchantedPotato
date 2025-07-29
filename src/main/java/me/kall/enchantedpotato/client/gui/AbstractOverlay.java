package me.kall.enchantedpotato.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
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
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        double value = getValue((ExtendedPlayer) mc.player);

        if (value > 0) {
            double displayNumber = isTicks() ? Math.ceil(value / 20.0) : value;
            Component text = Component.translatable(getTranslateKey(), displayNumber);
            int yPos = screenHeight - getYOffSet();
            Minecraft.getInstance().font.draw(poseStack, text, getX(), yPos, 0xFFA500);
        }
    }
}
