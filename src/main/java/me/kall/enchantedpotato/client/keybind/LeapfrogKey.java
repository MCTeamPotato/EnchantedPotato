package me.kall.enchantedpotato.client.keybind;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.network.SpaceLeapfrogPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class LeapfrogKey {
    public static final KeyMapping LEAPFROG_KEY = new KeyMapping("key." + EnchantedPotato.MOD_ID + ".leapfrog", GLFW.GLFW_KEY_V, "key."+ EnchantedPotato.MOD_ID + ".category");

    public static void onClientTick(ClientTickEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;
        LocalPlayer player = minecraft.player;
        if (((ExtendedPlayer)player).spaceLeapfrog$isInCoolDown()) return;
        if (LEAPFROG_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new SpaceLeapfrogPacket(true));
        }
    }
}
