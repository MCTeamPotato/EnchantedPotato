package com.teampotato.enchantedpotato.enchantment.digger;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.uniteStonesOfAll;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.uniteStonesOfAll;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.uniteStonesOfAll && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.uniteStonesOfAll;
    }
}
