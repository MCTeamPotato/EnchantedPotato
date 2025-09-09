package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.CaressingMoonlightConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class CaressingMoonlight extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new CaressingMoonlight();

    @Override
    public boolean isDisabled() {
        return DisableConfig.CARESSING_MOONLIGHT.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
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
        return cost(15, 10);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(12, 8);
    }

    @Override
    public int anvilCost() {
        return 2;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.CARESSING_MOONLIGHT;
    }

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

}
