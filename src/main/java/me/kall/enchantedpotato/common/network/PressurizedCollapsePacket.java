package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record PressurizedCollapsePacket(Vec3 position, double radius) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, PressurizedCollapsePacket> CODEC = CustomPacketPayload.codec(PressurizedCollapsePacket::toBytes, PressurizedCollapsePacket::new);
    public static final ResourceLocation ID = EnchantedPotato.loc("pressurized_collapse_packet");
    public static final Type<PressurizedCollapsePacket> TYPE = new Type<>(ID);

    public PressurizedCollapsePacket(@NotNull FriendlyByteBuf buf) {
        this(buf.readVec3(), buf.readDouble());
    }

    public void toBytes(@NotNull FriendlyByteBuf buf) {
        buf.writeVec3(position);
        buf.writeDouble(radius);
    }


    public static void handle(PressurizedCollapsePacket msg, @NotNull IPayloadContext context) {
        context.enqueueWork(() -> PressurizedCollapseRenderer.INSTANCE.addEffect(msg.position, msg.radius));
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}