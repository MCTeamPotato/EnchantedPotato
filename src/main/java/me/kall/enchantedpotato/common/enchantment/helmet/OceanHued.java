package me.kall.enchantedpotato.common.enchantment.helmet;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.OceanHuedConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class OceanHued extends BaseEnchantment {
    public OceanHued() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static void onHeal(@NotNull LivingHealEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.OCEAN_HUED.get()) == 0) {
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

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel level) {
            int enchantmentLevel = player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.OCEAN_HUED.get());
            if (enchantmentLevel == 0) return;
            if (((ExtendedPlayer)player).oceanHued$isReady()) {
                LivingEntity attacked = event.getEntity();
                Predicate<LivingEntity> filter = livingEntity -> livingEntity.getMobType().equals(MobType.UNDEAD) || livingEntity.getUUID().equals(attacked.getUUID());

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
        if (!event.isCanceled() && event.getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            int level = player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.OCEAN_HUED.get());
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
}
