package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.IAttributeModifier;
import com.teampotato.enchantedpotato.enchantment.GaiaBlessing;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class GaiaBlessingEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingTick(LivingEvent.@NotNull LivingTickEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        double y = entity.getY();
        AttributeInstance attributeInstance = entity.getAttribute(Attributes.ARMOR);
        if (attributeInstance == null || y >= 50 || entity.level().isClientSide || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.gaiaBlessing, GaiaBlessing.PATH)) return;
        AttributeModifier attributeModifier = attributeInstance.getModifier(GaiaBlessing.MODIFIER_UUID);
        if (attributeModifier == null) return;
        ((IAttributeModifier)attributeModifier).ep$setAmount(50.00 - y);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        AttributeInstance attributeInstance = entity.getAttribute(Attributes.ARMOR);
        if (attributeInstance == null || entity.level().isClientSide) return;
        attributeInstance.addPermanentModifier(new AttributeModifier(GaiaBlessing.MODIFIER_UUID, () -> GaiaBlessing.PATH + "_modifier", 0, AttributeModifier.Operation.ADDITION));
    }
}
