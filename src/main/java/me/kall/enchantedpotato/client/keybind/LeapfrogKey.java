package me.kall.enchantedpotato.client.keybind;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.network.SpaceLeapfrogPacket;
import me.kall.enchantedpotato.common.registry.ModPackets;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class LeapfrogKey {
    public static final KeyMapping LEAPFROG_KEY = new KeyMapping("key." + EnchantedPotato.MOD_ID + ".leapfrog", GLFW.GLFW_KEY_V, "key.categories.movement");

    public static void onClientTick(TickEvent.@NotNull ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START)) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player == null || minecraft.level == null) return;
            LocalPlayer player = minecraft.player;
            AttributeInstance spaceLeapfrogCooldown = ((ExtendedLivingEntity)player).spaceLeapfrog$getAttribute();
            if (spaceLeapfrogCooldown.getBaseValue() != 0) return;
            if (LEAPFROG_KEY.consumeClick()) {
                ModPackets.CHANNEL.sendToServer(new SpaceLeapfrogPacket());
            }
        }
    }
}
