package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.network.GurenNoYumiyaPacket;
import me.kall.enchantedpotato.common.network.PressurizedCollapsePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(ResourceLocation.parse(EnchantedPotato.MOD_ID + ":main"), () -> "1.0", s -> true, s -> true);
    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(packetId++, PressurizedCollapsePacket.class, PressurizedCollapsePacket::encode, PressurizedCollapsePacket::decode, PressurizedCollapsePacket::handle);
        CHANNEL.registerMessage(packetId++, GurenNoYumiyaPacket.class, GurenNoYumiyaPacket::encode, GurenNoYumiyaPacket::decode, GurenNoYumiyaPacket::handle);
    }
}
