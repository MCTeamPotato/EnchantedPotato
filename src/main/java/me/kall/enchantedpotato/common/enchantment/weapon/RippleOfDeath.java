package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.RippleOfDeathConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class RippleOfDeath extends BaseEnchantment {
    @Override
    public boolean isDisabled() {
        return DisableConfig.RIPPLE_OF_DEATH.get();
    }

    public static void onLivingDeath(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel level) {
            int enchantmentLevel = getLevelInHands(ModEnchantments.RIPPLE_OF_DEATH, player, level);
            if (enchantmentLevel == 0) return;
            int baseRadius = RippleOfDeathConfig.BASE_RADIUS.get();
            int gainedRadiusPerLevel = RippleOfDeathConfig.GAINED_RADIUS_PER_LEVEL.get();
            int radius = baseRadius + gainedRadiusPerLevel * (enchantmentLevel - 1);
            AABB box = event.getEntity().getBoundingBox().inflate(radius);
            Predicate<LivingEntity> filter = entity -> entity.isAlive() && entity instanceof Mob && !entity.getUUID().equals(player.getUUID());
            float basicDamagePercent = RippleOfDeathConfig.BASIC_DAMAGE_PERCENT.get().floatValue();
            float gainedDamagePercentPerLevel = RippleOfDeathConfig.GAINED_DAMAGE_PERCENT_PER_LEVEL.get().floatValue();
            float damagePercent = basicDamagePercent + gainedDamagePercentPerLevel * (enchantmentLevel - 1);
            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, box, filter)) {
                entity.hurt(player.damageSources().indirectMagic(player, player), entity.getMaxHealth() * damagePercent);
            }
        }
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
