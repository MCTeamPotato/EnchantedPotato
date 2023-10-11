package com.teampotato.enchantedpotato.enchantment.trident;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.triback)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.triback;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.triback;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.triback;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.triback;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.triback && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.triback;
    }
}
