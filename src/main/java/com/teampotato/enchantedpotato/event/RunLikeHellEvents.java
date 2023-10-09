package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.enchantment.RunLikeHell;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import static com.teampotato.enchantedpotato.config.DetailsConfig.*;

public class RunLikeHellEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        if (attacked.level().isClientSide) return;
        if (!event.isCanceled() && Utils.hasPotatoEnchantmentEquipped(attacked, EarlySetupInitializer.equipmentSlotConfig.runLikeHell, RunLikeHell.PATH) && ((Float) attacked.getHealth()).doubleValue() - ((Float) event.getAmount()).doubleValue() < ((Float) attacked.getMaxHealth()).doubleValue() * RUN_LIKE_HELL_VALID_MIN_HEALTH.get()) {
            attacked.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, RUN_LIKE_HELL_INVISIBILITY_DURATION.get()));
            attacked.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION.get(), RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER.get()));
            attacked.addTag(EarlySetupInitializer.MOD_ID + ".blinder");
            attacked.addTag(EarlySetupInitializer.MOD_ID + ".tickCountTrigger");
            return;
        }

        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
            sourceDirectEntity.removeTag(EarlySetupInitializer.MOD_ID + ".blinder");
            ((ILivingEntity)sourceDirectEntity).ep$setRunLikeHellTickCount(0);
            ((ILivingEntity)sourceDirectEntity).ep$setShouldTrackRunLikeHell(false);
        }
    }
}
