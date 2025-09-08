package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.CaressingMoonlightConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class CaressingMoonlight extends BaseEnchantment {
    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel serverLevel) {
            int level = getLevelInHands(ModEnchantments.CARESSING_MOONLIGHT, player, serverLevel);
            if (level == 0) return;
            float count = 0.0F;
            for (MobEffectInstance activeEffect : event.getEntity().getActiveEffects()) {
                if (activeEffect.getEffect().value().getCategory().equals(MobEffectCategory.HARMFUL)) count = count + 1.0F;
            }
            float baseDamage = CaressingMoonlightConfig.BASE_DAMAGE_FOR_EACH_EFFECT.get().floatValue();
            float gainedDamagePerLevel = CaressingMoonlightConfig.GAINED_DAMAGE_FOR_EACH_EFFECT_PER_LEVEL.get().floatValue();
            float damageBonus = (baseDamage + gainedDamagePerLevel * (float) (level - 1)) * count;
            event.setAmount(event.getAmount() + damageBonus);
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.CARESSING_MOONLIGHT.get();
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
        return EquipmentSlotGroup.HAND;
    }
}
