package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.digger.MarkFromTheBeneath;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

public class MarkFromTheBeneathEvents {
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.@NotNull LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        AttributeInstance digSpeedAttribute = entity.getAttribute(Attributes.ATTACK_SPEED);
        if (entity.getY() > DetailsConfig.MARK_FROM_THE_BENEATH_VALID_Y.get() || event.isCanceled() || digSpeedAttribute == null || digSpeedAttribute.hasModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER) || !(entity.getUseItem().getItem() instanceof PickaxeItem) || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.markFromTheBeneath, MarkFromTheBeneath.PATH)) {
            if (digSpeedAttribute != null) digSpeedAttribute.removeModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER_UUID);
            return;
        }
        digSpeedAttribute.addTransientModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER);
    }
}
