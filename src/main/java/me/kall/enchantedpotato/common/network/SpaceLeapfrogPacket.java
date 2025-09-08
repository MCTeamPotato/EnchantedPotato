package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.enchantment.boots.SpaceLeapfrog;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record SpaceLeapfrogPacket(boolean trigger) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, SpaceLeapfrogPacket> CODEC = CustomPacketPayload.codec(SpaceLeapfrogPacket::toBytes, SpaceLeapfrogPacket::new);
    public static final ResourceLocation ID = EnchantedPotato.loc("space_leapfrog_packet");
    public static final Type<SpaceLeapfrogPacket> TYPE = new Type<>(ID);

    public SpaceLeapfrogPacket(@NotNull FriendlyByteBuf buf) {
        this(buf.readBoolean());
    }

    public void toBytes(@NotNull FriendlyByteBuf buf) {
        buf.writeBoolean(trigger);
    }


    public static void handle(SpaceLeapfrogPacket packet, @NotNull IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player && packet.trigger()) {
                SpaceLeapfrog.spaceLeapfrog(player);
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
