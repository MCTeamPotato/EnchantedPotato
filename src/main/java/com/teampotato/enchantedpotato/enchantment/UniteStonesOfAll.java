package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class UniteStonesOfAll extends Enchantment {
    public static final String PATH = "unite_stones_of_all";
    public UniteStonesOfAll() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.uniteStonesOfAll),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.uniteStonesOfAll),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.uniteStonesOfAll)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.uniteStonesOfAll;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.uniteStonesOfAll && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.uniteStonesOfAll;
    }
}
