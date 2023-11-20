package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.armor.legs.ThisIsLeopard;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class ThisIsLeopardEvents {
    @SubscribeEvent
    public static void onLivingAttack(@NotNull LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if (source.getDirectEntity() instanceof LivingEntity entity && entity.getY() > 95.00 && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH)) {
            event.setAmount(event.getAmount() * DetailsConfig.THIS_IS_LEOPARD_DAMAGE_MULTIPLIER.get());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingChangeTarget(@NotNull LivingChangeTargetEvent event) {
        LivingEntity target = event.getOriginalTarget();
        if (target.getY() > DetailsConfig.THIS_IS_LEOPARD_VALID_HEIGHT.get() && Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH) && Utils.getRandom().nextDouble(0.00,1.00) < DetailsConfig.THIS_IS_LEOPARD_TARGET_MISS_CHANCE.get()) {
            event.setCanceled(true);
        }
    }
}
