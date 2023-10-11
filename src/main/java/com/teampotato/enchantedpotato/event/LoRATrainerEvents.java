package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.weapon.LoRATrainer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class LoRATrainerEvents {
    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (entity.level().isClientSide()) return;
        if (
                damageSource.getDirectEntity() instanceof ServerPlayer player &&
                player.getStats().getValue(Stats.ENTITY_KILLED.get(entity.getType())) >= DetailsConfig.LORA_TRAINER_KILL_COUNTS_REQUIREMENT.get() &&
                Utils.hasPotatoEnchantmentEquipped(player, EarlySetupInitializer.equipmentSlotConfig.loraTrainer, LoRATrainer.PATH)
        ) {
            event.setAmount((DetailsConfig.LORA_TRAINER_DAMAGE_BONUS.get().floatValue() + 1.0F) * event.getAmount());
        }
    }
}
