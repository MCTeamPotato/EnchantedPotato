package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GraceOfGungnir extends BaseEnchantment {
    @Override
    public boolean isDisabled() {
        return DisableConfig.GRACE_OF_GUNGNIR.get();
    }

    public static @Nullable LivingEntity findNearestLivingEntityOnPath(@NotNull Player player) {
        double maxDistance = 100;
        Vec3 startPos = player.getEyePosition();
        Vec3 viewVector = player.getViewVector(1.0F);
        Vec3 endPos = startPos.add(viewVector.scale(maxDistance));
        Level level = player.level();

        ClipContext context = new ClipContext(startPos, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);
        Vec3 blockHit = level.clip(context).getLocation();
        double actualDistance = Math.min(maxDistance, startPos.distanceTo(blockHit));
        Vec3 actualEnd = startPos.add(viewVector.scale(actualDistance));

        Predicate<Entity> filter = entity -> entity != player && entity.isAlive() && entity instanceof LivingEntity && !entity.isSpectator();

        AABB searchArea = new AABB(startPos, actualEnd).inflate(2.0);
        List<Entity> entities = level.getEntities(player, searchArea, filter);

        LivingEntity nearestTarget = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity entity : entities) {
            Optional<Vec3> hitPoint = entity.getBoundingBox().clip(startPos, actualEnd);
            if (hitPoint.isPresent()) {
                double distanceSq = startPos.distanceToSqr(hitPoint.get());
                if (distanceSq < closestDistance) {
                    closestDistance = distanceSq;
                    nearestTarget = (LivingEntity) entity;
                }
            }
        }
        return nearestTarget;
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int maxLevel() {
        return 0;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
