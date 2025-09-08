package me.kall.enchantedpotato.common.enchantment.helmet;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.OceanHuedConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class OceanHued extends BaseEnchantment {
    public static void onHeal(@NotNull LivingHealEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof Player player && player.level() instanceof ServerLevel serverLevel) {
            if (getLevel(player.getItemBySlot(EquipmentSlot.HEAD), serverLevel, ModEnchantments.OCEAN_HUED) == 0) {
                ((ExtendedPlayer)player).oceanHued$setHealingAmount(0.00);
                ((ExtendedPlayer)player).oceanHued$setCountingTicks(0.00);
                return;
            }
            double amount = event.getAmount();
            if (((ExtendedPlayer)player).oceanHued$isReady() || ((ExtendedPlayer)player).oceanHued$isInCoolDown()) return;
            if (!((ExtendedPlayer)player).oceanHued$isInCounting()) {
                ((ExtendedPlayer)player).oceanHued$setCountingTicks(0.01);
                ((ExtendedPlayer)player).oceanHued$setHealingAmount(amount);
            } else {
                double origin = ((ExtendedPlayer)player).oceanHued$getHealingAmount();
                ((ExtendedPlayer)player).oceanHued$setHealingAmount(origin + amount);
            }
        }
    }

    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel level) {
            int enchantmentLevel = getLevel(player.getItemBySlot(EquipmentSlot.HEAD), level, ModEnchantments.OCEAN_HUED);
            if (enchantmentLevel == 0) return;
            if (((ExtendedPlayer)player).oceanHued$isReady()) {
                LivingEntity attacked = event.getEntity();
                Predicate<LivingEntity> filter = livingEntity -> livingEntity.getType().is(EntityTypeTags.UNDEAD) || livingEntity.getUUID().equals(attacked.getUUID());

                float maxDamageAmount = OceanHuedConfig.BASE_MAX_DAMAGE_AMOUNT.get().floatValue() + OceanHuedConfig.GAINED_MAX_DAMAGE_AMOUNT_PER_LEVEL.get().floatValue() * (float)(enchantmentLevel - 1);
                double reductionPercent = OceanHuedConfig.BASE_HEALING_AMOUNT_REDUCTION.get() - OceanHuedConfig.SAVED_HEALING_AMOUNT_REDUCTION_PER_LEVEL.get() * (enchantmentLevel - 1);
                if (reductionPercent < 0) reductionPercent = 0D;

                float amount = (float) (((ExtendedPlayer)player).oceanHued$getHealingAmount() * (1F - reductionPercent));
                if (amount > maxDamageAmount) amount = maxDamageAmount;

                for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, attacked.getBoundingBox().inflate(OceanHuedConfig.BASE_RADIUS.get().floatValue() + OceanHuedConfig.GAINED_RADIUS_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1)), filter)) {
                    entity.hurt(level.damageSources().inWall(), amount);
                }
                ((ExtendedPlayer)player).oceanHued$setCountingTicks(0.00);
                ((ExtendedPlayer)player).oceanHued$setCoolDown(OceanHuedConfig.getCoolDown(enchantmentLevel));
                ((ExtendedPlayer)player).oceanHued$setHealingAmount(0.00);
            }
        }
    }

    public static void onPlayerDeath(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof Player player && player.level() instanceof ServerLevel serverLevel) {
            int level = getLevel(player.getItemBySlot(EquipmentSlot.HEAD), serverLevel, ModEnchantments.OCEAN_HUED);
            if (level == 0) return;
            ((ExtendedPlayer)player).oceanHued$setHealingAmount(0.00);
            ((ExtendedPlayer)player).oceanHued$setCountingTicks(0.00);
            ((ExtendedPlayer)player).oceanHued$setCoolDown(OceanHuedConfig.getCoolDown(level));
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.OCEAN_HUED.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        if (isDisabled()) return HolderSet.empty();
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
        return EquipmentSlotGroup.HEAD;
    }
}
