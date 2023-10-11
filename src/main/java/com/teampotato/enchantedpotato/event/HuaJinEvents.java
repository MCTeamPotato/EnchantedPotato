package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.armor.chest.HuaJin;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class HuaJinEvents {
    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide() || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.huaJin, HuaJin.PATH)) return;
        float amount = event.getAmount();
        float health = entity.getHealth();
        float huaJin = DetailsConfig.HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE.get().floatValue();
        if (amount > health * huaJin) {
            event.setAmount(amount - (amount - health * huaJin) * DetailsConfig.HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE.get().floatValue());
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, DetailsConfig.HUA_JIN_STRENGTH_EFFECT_DURATION.get(), DetailsConfig.HUA_JIN_STRENGTH_EFFECT_AMPLIFIER.get()));
        }
    }
}
