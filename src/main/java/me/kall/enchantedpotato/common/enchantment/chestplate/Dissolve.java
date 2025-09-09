package me.kall.enchantedpotato.common.enchantment.chestplate;

import me.kall.enchantedpotato.common.config.DissolveConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Dissolve extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new Dissolve();

    @Override
    public boolean isDisabled() {
        return DisableConfig.DISSOLVE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
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
        return cost(22, 15);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(18, 12);
    }

    @Override
    public int anvilCost() {
        return 3;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.CHEST;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.DISSOLVE;
    }

    public static void onPlayerHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel) {
            int level = getLevel(player.getItemBySlot(EquipmentSlot.CHEST), serverLevel, ModEnchantments.DISSOLVE);
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
