package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.util.Constants;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class UntouchableEvents {
    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        DamageSource damageSource = event.getSource();
        if (!level.isClientSide && ((ILivingEntity)entity).ep$ShouldTickUntouchable() && damageSource.getDirectEntity() instanceof LivingEntity sourceDirectEntity && entity.getTags().contains(EarlySetupInitializer.MOD_ID + ".untouchable")) {
            for (Monster monster : level.getEntitiesOfClass(Monster.class, entity.getBoundingBox().expandTowards(Constants.untouchableX, Constants.untouchableY, Constants.untouchableZ))) {
                monster.knockback(DetailsConfig.UNTOUCHABLE_KNOCK_BACK_STRENGTH.get(), sourceDirectEntity.getX() - entity.getX(), sourceDirectEntity.getZ() - entity.getZ());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.@NotNull LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        if (entity.getTags().contains(EarlySetupInitializer.MOD_ID + ".untouchable")) {
            ILivingEntity iLivingEntity = (ILivingEntity) entity;
            iLivingEntity.ep$setUntouchableTickCount(iLivingEntity.ep$getUntouchableTickCount() + 1);
        }
    }
}
