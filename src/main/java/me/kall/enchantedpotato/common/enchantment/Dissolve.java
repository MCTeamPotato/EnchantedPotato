package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.config.DissolveConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Dissolve extends Enchantment {
    public Dissolve() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    public int getMaxLevel() {
        return 3;
    }

    public static void onPlayerHurt(@NotNull LivingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel) {
            int level = player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.DISSOLVE.get());
            if (level == 0) return;

            float baseDamageReductionForExceededPart = DissolveConfig.BASE_DAMAGE_REDUCTION.get().floatValue();
            float gainedDamageReductionPerLevelForExceededPart = DissolveConfig.GAINED_DAMAGE_REDUCTION_PER_LEVEL.get().floatValue();
            float damageReductionForExceededPart = baseDamageReductionForExceededPart + gainedDamageReductionPerLevelForExceededPart * (float)(level - 1);

            if (damageReductionForExceededPart > 1.0F) damageReductionForExceededPart = 1.0F;

            float baseThreshold = DissolveConfig.BASE_THRESHOLD.get().floatValue();
            float savedThresholdPerLevel = DissolveConfig.SAVED_THRESHOLD_PER_LEVEL.get().floatValue();
            float threshold = baseThreshold - savedThresholdPerLevel * (level - 1);

            float minusThreshold = DissolveConfig.MINUS_THRESHOLD.get().floatValue();
            if (threshold < minusThreshold) threshold = minusThreshold;

            float amount = event.getAmount();
            float health = player.getHealth();
            if (amount > health * threshold) {
                float exceed = amount - health * threshold;
                amount = amount - exceed * damageReductionForExceededPart;
                event.setAmount(amount);

                int baseDuration = DissolveConfig.BASE_STRENGTH_DURATION.get();
                int gainedDurationPerLevel = DissolveConfig.GAINED_STRENGTH_DURATION_PER_LEVEL.get();
                int duration = baseDuration + gainedDurationPerLevel * (level - 1);

                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 1));
            }
        }
    }
}
