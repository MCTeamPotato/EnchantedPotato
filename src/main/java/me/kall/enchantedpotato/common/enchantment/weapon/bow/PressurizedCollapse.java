package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PressurizedCollapse extends Enchantment {
    public PressurizedCollapse() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public int getMaxLevel() {
        return 3;
    }

    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return other != ModEnchantments.GRACE_OF_GUNGNIR.get() && super.checkCompatibility(other);
    }

    public static void apply(@NotNull Vec3 center, double range, @NotNull Level world, int level) {
        AABB area = new AABB(center.x - range, center.y - range, center.z - range, center.x + range, center.y + range, center.z + range);

        List<Entity> entities = world.getEntitiesOfClass(Entity.class, area, e -> e.isAlive() && !(e instanceof Player));

        for (Entity entity : entities) {
            Vec3 direction = center.subtract(entity.position()).normalize();
            double distance = entity.distanceToSqr(center);
            double strength = (1.0 - (distance / (range * range))) * 0.8 * (1.0D + 0.5D * ((double) level - 1.0D));
            entity.setDeltaMovement(entity.getDeltaMovement().add(direction.scale(strength)));
            entity.hurtMarked = true;
            if (!entity.isOnGround()) {
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7, 0.98, 0.7));
            }
        }
    }
}
