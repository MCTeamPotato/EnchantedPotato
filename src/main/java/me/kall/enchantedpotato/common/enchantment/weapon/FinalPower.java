package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class FinalPower extends BaseEnchantment {
    public static boolean entityHasFinalPower(@NotNull LivingEntity livingSource) {
        return getLevelInHands(ModEnchantments.FINAL_POWER, livingSource, livingSource.level()) != 0;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.FINAL_POWER.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        if (isDisabled()) return HolderSet.empty();
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
        return EquipmentSlotGroup.HAND;
    }
}