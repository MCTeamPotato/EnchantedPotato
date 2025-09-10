package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.SpaceLeapfrogConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SpaceLeapfrog extends BaseEnchantment {
    public SpaceLeapfrog() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static double getDist(int enchantmentLevel) {
        return SpaceLeapfrogConfig.BASIC_LEAPFROG_DIST.get() + SpaceLeapfrogConfig.GAINED_LEAPFROG_DIST_PER_LEVEL.get() * (double) (enchantmentLevel - 1);
    }

    public static float getExplodeRadius(int enchantmentLevel) {
        return SpaceLeapfrogConfig.BASIC_EXPLOSION_RADIUS.get().floatValue() + SpaceLeapfrogConfig.GAINED_EXPLOSION_RADIUS_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1);
    }

    public static int getCoolDown(int enchantmentLevel) {
        return SpaceLeapfrogConfig.BASIC_COOLDOWN.get() - SpaceLeapfrogConfig.SAVED_COOLDOWN_PER_LEVEL.get() * (enchantmentLevel - 1);
    }

    public static void spaceLeapfrog(@NotNull ServerPlayer player) {
        ServerLevel level = player.getLevel();

        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SPACE_LEAPFROG.get(), player.getItemBySlot(EquipmentSlot.FEET));
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
        level.explode(player, originPos.getX(), originPos.getY(), originPos.getZ(), radius, Explosion.BlockInteraction.NONE);

        ((ExtendedPlayer) player).spaceLeapfrog$setCoolDown(getCoolDown(enchantmentLevel));
    }

    @SuppressWarnings("deprecation")
    private static void validatePos(@NotNull BlockState targetBlock, ServerLevel level, BlockPos.MutableBlockPos targetPos) {
        if (targetBlock.isAir()) {
            if (targetPos.getY() <= 0) targetPos.setY(60);
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

    @Override
    public boolean isDisabled() {
        return DisableConfig.SPACE_LEAPFROG.get();
    }
}
