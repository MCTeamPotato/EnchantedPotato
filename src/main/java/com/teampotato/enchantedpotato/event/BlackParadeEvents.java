package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.BlackParade;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class BlackParadeEvents {
    @SubscribeEvent
    public static void onLivingDeath(@NotNull LivingDeathEvent event) {
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity directSourceEntity) {
            MobEffectInstance effect = directSourceEntity.getEffect(MobEffects.MOVEMENT_SPEED);
            if (Utils.hasPotatoEnchantmentEquipped(directSourceEntity, EarlySetupInitializer.equipmentSlotConfig.blackParade, BlackParade.PATH)) {
                if (effect != null) {
                    directSourceEntity.removeEffect(effect.getEffect());
                    directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DetailsConfig.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get() + effect.getDuration(), DetailsConfig.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                } else {
                    directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DetailsConfig.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get(), DetailsConfig.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                }
            }
        }
    }
}
