package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PressurizedCollapsePacket {
    private final Vec3 position;
    private final double radius;

    public PressurizedCollapsePacket(Vec3 position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    public static void encode(PressurizedCollapsePacket msg, FriendlyByteBuf buf) {
        buf.writeDouble(msg.position.x);
        buf.writeDouble(msg.position.y);
        buf.writeDouble(msg.position.z);
        buf.writeDouble(msg.radius);
    }

    public static PressurizedCollapsePacket decode(FriendlyByteBuf buf) {
        return new PressurizedCollapsePacket(new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()), buf.readDouble());
    }

    public static void handle(PressurizedCollapsePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> PressurizedCollapseRenderer.INSTANCE.addEffect(msg.position, msg.radius));
        ctx.get().setPacketHandled(true);
    }
}