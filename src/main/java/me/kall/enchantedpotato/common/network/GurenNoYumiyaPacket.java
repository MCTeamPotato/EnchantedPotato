package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.renderer.GurenNoYumiyaRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record GurenNoYumiyaPacket(Vec3 position, double radius) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, GurenNoYumiyaPacket> CODEC = CustomPacketPayload.codec(GurenNoYumiyaPacket::toBytes, GurenNoYumiyaPacket::new);
    public static final ResourceLocation ID = EnchantedPotato.loc("guren_no_yumiya_packet");
    public static final Type<GurenNoYumiyaPacket> TYPE = new Type<>(ID);

    public GurenNoYumiyaPacket(@NotNull FriendlyByteBuf buf) {
        this(buf.readVec3(), buf.readDouble());
    }

    public void toBytes(@NotNull FriendlyByteBuf buf) {
        buf.writeVec3(position);
        buf.writeDouble(radius);
    }

    public static void handle(GurenNoYumiyaPacket msg, @NotNull IPayloadContext context) {
        context.enqueueWork(() -> GurenNoYumiyaRenderer.INSTANCE.addEffect(msg.position, msg.radius));
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
