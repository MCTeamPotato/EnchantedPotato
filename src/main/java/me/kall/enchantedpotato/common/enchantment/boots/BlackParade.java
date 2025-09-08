package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.BlackParadeConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

public class BlackParade extends BaseEnchantment {

    public static void onLivingDie(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel level && getLevel(player.getItemBySlot(EquipmentSlot.FEET), level, ModEnchantments.BLACK_PARADE) != 0) {
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

    @Override
    public boolean isDisabled() {
        return DisableConfig.BLACK_PARADE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int maxLevel() {
        return 0;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.FEET;
    }
}
