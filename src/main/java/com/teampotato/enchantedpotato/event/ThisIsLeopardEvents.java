package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.ThisIsLeopard;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class ThisIsLeopardEvents {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

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
        if (target.getY() > DetailsConfig.THIS_IS_LEOPARD_VALID_HEIGHT.get() && Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH) && RANDOM.nextDouble(0.00,1.00) < DetailsConfig.THIS_IS_LEOPARD_TARGET_MISS_CHANCE.get()) {
            event.setCanceled(true);
        }
    }
}
