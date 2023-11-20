package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import org.jetbrains.annotations.NotNull;

public class DyingOfLightEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingChangeTarget(@NotNull LivingChangeTargetEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        LivingEntity target = event.getOriginalTarget();
        if (Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH) && entity.level().isNight() && entity instanceof Monster) {
            LivingEntity lastHurtMob = target.getLastHurtMob();
            if (lastHurtMob != null && lastHurtMob.getUUID().equals(entity.getUUID())) return;
            event.setCanceled(true);
        }
    }
}
