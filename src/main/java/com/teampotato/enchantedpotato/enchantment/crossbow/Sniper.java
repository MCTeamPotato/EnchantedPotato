package com.teampotato.enchantedpotato.enchantment.crossbow;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Sniper extends Enchantment {
    public static final String PATH = "sniper";

    public Sniper() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.sniper),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.sniper),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.sniper)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.sniper;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.sniper;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.sniper;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.sniper;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.sniper && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.sniper;
    }

    @Override
    public int getMaxLevel() {
        return EarlySetupInitializer.maxLevelConfig.sniper;
    }
}

