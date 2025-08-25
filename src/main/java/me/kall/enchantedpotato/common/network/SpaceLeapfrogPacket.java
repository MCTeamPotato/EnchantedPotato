package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.common.enchantment.boots.SpaceLeapfrog;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SpaceLeapfrogPacket {
    public SpaceLeapfrogPacket() {}
    public static void encode(SpaceLeapfrogPacket ignored, FriendlyByteBuf ignoredBuf) {}

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull SpaceLeapfrogPacket decode(FriendlyByteBuf ignored) {
        return new SpaceLeapfrogPacket();
    }

    public static void handle(SpaceLeapfrogPacket ignored, @NotNull Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) return;
            SpaceLeapfrog.spaceLeapfrog(player);
        });
        context.get().setPacketHandled(true);
    }


    @SuppressWarnings("unused")
    private void forInstance() {}
}
