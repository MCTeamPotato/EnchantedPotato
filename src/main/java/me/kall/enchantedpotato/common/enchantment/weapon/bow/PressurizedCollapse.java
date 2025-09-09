package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import me.kall.enchantedpotato.common.registry.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PressurizedCollapse extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new PressurizedCollapse();

    @Override
    public boolean isDisabled() {
        return DisableConfig.PRESSURIZED_COLLAPSE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.BOW_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(22, 12);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(18, 10);
    }

    @Override
    public int anvilCost() {
        return 3;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    @Override
    public ResourceKey<Enchantment> loc() {
        return ModEnchantments.PRESSURIZED_COLLAPSE;
    }

    @Override
    public TagKey<Enchantment> incompatible() {
        return ModTags.BOW_COM;
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
            if (!entity.onGround()) {
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7, 0.98, 0.7));
            }
        }
    }
}
