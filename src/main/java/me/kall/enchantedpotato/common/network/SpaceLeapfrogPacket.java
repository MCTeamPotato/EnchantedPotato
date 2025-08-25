package me.kall.enchantedpotato.common.network;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.enchantment.boots.SpaceLeapfrog;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
            ServerLevel level = player.serverLevel();

            int enchantmentLevel = player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.SPACE_LEAPFROG.get());
            if (enchantmentLevel <= 0) return;

            double dist = SpaceLeapfrog.getDist(enchantmentLevel);
            float radius = SpaceLeapfrog.getExplodeRadius(enchantmentLevel);

            Vec3 look = player.getLookAngle().normalize();
            Vec3 targetVec = player.position().add(look.x * dist, look.y, look.z * dist);

            BlockPos originPos = player.blockPosition();
            BlockPos.MutableBlockPos targetPos = new BlockPos.MutableBlockPos(targetVec.x, targetVec.y, targetVec.z);

            BlockState targetBlock = level.getBlockState(targetPos);

            validatePos(targetBlock, level, targetPos);

            player.teleportTo(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            level.explode(player, originPos.getX(), originPos.getY(), originPos.getZ(), radius, Level.ExplosionInteraction.NONE);

            ((ExtendedPlayer) player).spaceLeapfrog$setCoolDown(100);
        });
        context.get().setPacketHandled(true);
    }

    private static void validatePos(@NotNull BlockState targetBlock, ServerLevel level, BlockPos.MutableBlockPos targetPos) {
        if (targetBlock.isAir()) {
            if (targetPos.getY() <= level.getMinBuildHeight()) targetPos.setY(60);
            while (level.getBlockState(targetPos.below()).isAir()) {
                targetPos.move(0, -1, 0);
            }
            targetPos.move(0, 1, 0);
        }

        if (level.getBlockState(targetPos).getFluidState().isEmpty()) {
            while (!level.getBlockState(targetPos.below()).isAir()) {
                targetPos.move(0, 1, 0);
            }
        }
    }

    private void ignoreMe() {}
}
