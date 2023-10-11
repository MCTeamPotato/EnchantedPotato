package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.IEntity;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.FlameCross;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class FlameCrossEvents {
    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource damageSource = event.getSource();
        Entity sourceEntity = damageSource.getEntity();
        Entity sourceDirectEntity = damageSource.getDirectEntity();
        if (sourceDirectEntity instanceof LivingEntity source && sourceEntity != null && Utils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH)) {
            if (DetailsConfig.FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING.get()) ((IEntity)source).ep$setTicksOnFire(DetailsConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER.get());
            if (sourceDirectEntity.getStringUUID().equals(sourceEntity.getStringUUID())) {
                if (Utils.getRandom().nextDouble(0.00, 1.00) < DetailsConfig.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING.get()) {
                    ((IEntity)entity).ep$setTicksOnFire(DetailsConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                }
            } else {
                if (Utils.getRandom().nextDouble(0.00, 1.00) < DetailsConfig.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING.get()) {
                    ((IEntity)entity).ep$setTicksOnFire(DetailsConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                }
            }
            if (entity.isOnFire()) event.setAmount(event.getAmount() * DetailsConfig.FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET.get().floatValue());
        }
        if (Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH) && damageSource.is(DamageTypeTags.IS_FIRE)) {
            event.setAmount(event.getAmount() * DetailsConfig.FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER.get().floatValue());
        }
    }
}
