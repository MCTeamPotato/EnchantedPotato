package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.common.network.GurenNoYumiyaPacket;
import me.kall.enchantedpotato.common.network.PressurizedCollapsePacket;
import me.kall.enchantedpotato.common.network.SpaceLeapfrogPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModPackets {
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(SpaceLeapfrogPacket.TYPE, SpaceLeapfrogPacket.CODEC, SpaceLeapfrogPacket::handle);
        registrar.playToClient(GurenNoYumiyaPacket.TYPE, GurenNoYumiyaPacket.CODEC, GurenNoYumiyaPacket::handle);
        registrar.playToClient(PressurizedCollapsePacket.TYPE, PressurizedCollapsePacket.CODEC, PressurizedCollapsePacket::handle);
    }
}
