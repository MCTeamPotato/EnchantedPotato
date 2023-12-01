package com.teampotato.enchantedpotato.enchantment.trident;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Triback extends Enchantment {
    public static final String PATH = "triback";
    public Triback() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.triback),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.triback),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.triback)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.triback;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.triback;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.triback;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.triback;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.triback && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.triback;
    }
}
