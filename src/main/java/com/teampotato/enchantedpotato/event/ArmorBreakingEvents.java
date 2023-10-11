package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.weapon.ArmorBreaking;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class ArmorBreakingEvents {
    @SubscribeEvent
    public static void onLivingAttack(@NotNull LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        AttributeInstance armorValueAttribute = entity.getAttribute(Attributes.ARMOR);
        AttributeInstance armorToughnessAttribute = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (armorToughnessAttribute == null || armorValueAttribute == null || entity.level().isClientSide) return;
        if (event.getSource().getDirectEntity() instanceof LivingEntity sourceDirectEntity && Utils.hasPotatoEnchantmentEquipped(sourceDirectEntity, EarlySetupInitializer.equipmentSlotConfig.armorBreaking, ArmorBreaking.PATH)) {
            if (!armorValueAttribute.hasModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER) && armorValueAttribute.getValue() * (1.00 - DetailsConfig.ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER.get()) <= DetailsConfig.ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION.get().doubleValue()) armorValueAttribute.addTransientModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER);
            if (!armorToughnessAttribute.hasModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER)) armorToughnessAttribute.addTransientModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER);
        }
    }
}
