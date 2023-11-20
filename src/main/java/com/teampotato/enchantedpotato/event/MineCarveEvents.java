package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.digger.MineCarve;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import org.jetbrains.annotations.NotNull;

public class MineCarveEvents {
    @SubscribeEvent
    public static void onLivingAttack(@NotNull LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity source && entity.getArmorValue() >= DetailsConfig.MINE_CARVE_VALID_ARMOR_VALUE.get() && Utils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.mineCarve, MineCarve.PATH)) {
            AttributeInstance armorValue = entity.getAttribute(Attributes.ARMOR);
            if (armorValue == null || armorValue.hasModifier(MineCarve.ARMOR_VALUE_MODIFIER)) return;
            armorValue.addTransientModifier(MineCarve.ARMOR_VALUE_MODIFIER);
        }
    }
}
