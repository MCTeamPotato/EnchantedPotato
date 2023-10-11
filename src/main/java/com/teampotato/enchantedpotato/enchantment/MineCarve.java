package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class MineCarve extends Enchantment {
    public static final String PATH = "mine_carve";
    public MineCarve() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.mineCarve),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.mineCarve),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.mineCarve)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.mineCarve;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.mineCarve;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.mineCarve;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.mineCarve;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.mineCarve && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.mineCarve;
    }
}
