package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class FlameCross extends Enchantment {
    public static final String PATH = "flame_cross";
    public FlameCross() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.flameCross),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.flameCross),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.flameCross)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.flameCross;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.flameCross;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.flameCross;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.flameCross;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.flameCross && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.flameCross;
    }
}
