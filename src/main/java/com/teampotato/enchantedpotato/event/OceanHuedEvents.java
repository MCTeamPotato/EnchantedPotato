package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.util.Constants;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class OceanHuedEvents {
    @SubscribeEvent
    public static void onLivingHeal(@NotNull LivingHealEvent event) {
        LivingEntity healed = event.getEntity();
        ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) healed;
        if (iLivingEntity.ep$getIsOceanHuedAttackReady() || iLivingEntity.ep$getOceanHuedCoolDownTickCount() != 0) return;
        if (!iLivingEntity.ep$getOceanHuedTimerStatus()) {
            iLivingEntity.ep$setOceanHuedTimerStatus(true);
            iLivingEntity.ep$setOceanHuedHealingAmount(event.getAmount());
        } else {
            iLivingEntity.ep$setOceanHuedHealingAmount(iLivingEntity.ep$getOceanHuedHealingAmount() + event.getAmount());
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.@NotNull LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) entity;
        if (iLivingEntity.ep$getOceanHuedTimerStatus()) {
            iLivingEntity.ep$setOceanHuedTimerTickCount(iLivingEntity.ep$getOceanHuedTimerTickCount() + 1);
            if (iLivingEntity.ep$getOceanHuedTimerTickCount() >= DetailsConfig.OCEAN_HUED_TIMER_TICKS.get()) {
                iLivingEntity.ep$resetOceanHuedTimer();
                iLivingEntity.ep$setIsOceanHuedAttackReady(true);
            }
        }
        if (iLivingEntity.ep$getOceanHuedCoolDownTickCount() > 0) {
            iLivingEntity.ep$setOceanHuedCoolDownTickCount(iLivingEntity.ep$getOceanHuedCoolDownTickCount() - 1);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity sourceEntity) {
            ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) sourceEntity;
            if (iLivingEntity.ep$getIsOceanHuedAttackReady()) {
                float maxDamage = DetailsConfig.OCEAN_HUED_MAX_DAMAGE_ADDITION.get().floatValue();
                float amount = iLivingEntity.ep$getOceanHuedHealingAmount() * DetailsConfig.OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT.get().floatValue();
                if (amount >= maxDamage) amount = maxDamage;
                double x = attacked.getX();
                double y = attacked.getY();
                double z = attacked.getZ();
                for (LivingEntity target : attacked.level().getEntitiesOfClass(LivingEntity.class, new AABB(x - Constants.oceanHuedX, y - Constants.oceanHuedY, z - Constants.oceanHuedZ, x + Constants.oceanHuedX, y + Constants.oceanHuedY, z + Constants.oceanHuedZ))) {
                    target.hurt(target.level().damageSources().inWall(), amount);
                }
                iLivingEntity.ep$setIsOceanHuedAttackReady(false);
                iLivingEntity.ep$setOceanHuedCoolDownTickCount(DetailsConfig.OCEAN_HUED_COOL_DOWN_TICKS.get());
            }
        }
    }
}
