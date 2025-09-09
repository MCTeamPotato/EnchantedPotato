package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import me.kall.enchantedpotato.common.config.GurenNoYumiyaConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.network.GurenNoYumiyaPacket;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class GurenNoYumiya extends BaseEnchantment {
    public static final String GUREN_NO_YUMIYA_KEY = "GurenNoYumiyaLevel";

    @Override
    public boolean isDisabled() {
        return DisableConfig.GUREN_NO_YUMIYA.get();
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
        return 4;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(20, 12);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(16, 10);
    }

    @Override
    public int anvilCost() {
        return 3;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    public static void apply(@NotNull AbstractArrow arrow) {
        if (arrow.getOwner() instanceof ServerPlayer player) {
            int level = arrow.getPersistentData().getInt(GUREN_NO_YUMIYA_KEY);

            if (level == 0) return;
            double radius = GurenNoYumiyaConfig.BASE_RADIUS.get() + GurenNoYumiyaConfig.GAINED_RADIUS_PER_LEVEL.get() * (double)(level - 1);
            AABB box = new AABB(arrow.blockPosition()).inflate(radius);
            Predicate<LivingEntity> filter = entity -> entity.isAlive() && !entity.fireImmune() && !entity.getUUID().equals(arrow.getOwner().getUUID());

            int fireTicks = (GurenNoYumiyaConfig.BASE_FIRE_SECONDS.get() + GurenNoYumiyaConfig.GAINED_FIRE_SECONDS_PER_LEVEL.get() * (level - 1)) * 20;

            Vec3 pos = arrow.position();
            GurenNoYumiyaPacket packet = new GurenNoYumiyaPacket(pos, radius);
            PacketDistributor.sendToPlayer(player, packet);

            arrow.level()
                    .getEntitiesOfClass(LivingEntity.class, box, filter)
                    .forEach(entity -> {
                        if (entity.isOnFire()) {
                            int origin = entity.getRemainingFireTicks();
                            entity.setRemainingFireTicks(origin + fireTicks);
                        } else {
                            entity.setRemainingFireTicks(fireTicks);
                        }
                    });
            arrow.getPersistentData().remove(GUREN_NO_YUMIYA_KEY);
        }
    }

}
