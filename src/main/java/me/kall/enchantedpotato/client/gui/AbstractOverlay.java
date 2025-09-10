package me.kall.enchantedpotato.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public abstract class AbstractOverlay {

    public abstract String getTranslateKey();

    public abstract int getX();

    public abstract int getYOffSet();

    public abstract double getValue(ExtendedPlayer player);

    public abstract boolean isTicks();

    public void render(PoseStack poseStack) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        double value = getValue((ExtendedPlayer) mc.player);

        if (value > 0) {
            double displayNumber = isTicks() ? Math.ceil(value / 20.0) : value;
            Component text = new TranslatableComponent(getTranslateKey(), displayNumber);
            int yPos = mc.getWindow().getGuiScaledHeight() - getYOffSet();
            mc.font.drawShadow(poseStack, text, getX(), yPos, 0xFFA500);
        }
    }
}
