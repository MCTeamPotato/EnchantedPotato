package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.BlackParadeConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

public class BlackParade extends Enchantment {
    public BlackParade() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public static void onLivingDie(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.BLACK_PARADE.get()) != 0) {
            MobEffectInstance speedInstance = player.getEffect(MobEffects.MOVEMENT_SPEED);
            int configDuration = BlackParadeConfig.SPEED_DURATION.get();
            int configAmplifier = BlackParadeConfig.SPEED_AMPLIFIER.get();
            if (speedInstance == null) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, configDuration, configAmplifier));
            } else {
                int duration = speedInstance.getDuration();
                player.removeEffect(MobEffects.MOVEMENT_SPEED);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, configDuration + duration, configAmplifier));
            }
        }
    }
}
