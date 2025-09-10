package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PressurizedCollapsePacket {
    private final Vec3 position;
    private final double radius;

    public PressurizedCollapsePacket(Vec3 position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    public static void encode(@NotNull PressurizedCollapsePacket msg, @NotNull FriendlyByteBuf buf) {
        buf.writeDouble(msg.position.x);
        buf.writeDouble(msg.position.y);
        buf.writeDouble(msg.position.z);
        buf.writeDouble(msg.radius);
    }

    @Contract("_ -> new")
    public static @NotNull PressurizedCollapsePacket decode(@NotNull FriendlyByteBuf buf) {
        return new PressurizedCollapsePacket(new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()), buf.readDouble());
    }

    public static void handle(PressurizedCollapsePacket msg, @NotNull Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> PressurizedCollapseRenderer.INSTANCE.addEffect(msg.position, msg.radius));
        ctx.get().setPacketHandled(true);
    }
}