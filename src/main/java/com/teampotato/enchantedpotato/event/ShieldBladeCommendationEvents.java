package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.effect.CounterattackMoment;
import com.teampotato.enchantedpotato.enchantment.ShieldBladeCommendation;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class ShieldBladeCommendationEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onShieldBlock(@NotNull ShieldBlockEvent event) {
        LivingEntity entity = event.getEntity();
        if (!event.isCanceled() && !entity.level().isClientSide && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.shieldBladeCommendation, ShieldBladeCommendation.PATH)) {
            entity.addEffect(new MobEffectInstance(CounterattackMoment.INSTANCE, 40, 0));
            entity.addTag(EarlySetupInitializer.MOD_ID + ".counter_attacker");
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof Player sourcePlayer && sourcePlayer.removeTag(EarlySetupInitializer.MOD_ID + ".counter_attacker")) {
            event.setAmount(event.getAmount() * 1.5F);
            sourcePlayer.resetAttackStrengthTicker();//TODO: Not sure.
        }
    }
}
