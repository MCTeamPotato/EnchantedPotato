package com.teampotato.enchantedpotato.enchantment.armor.chest;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BlessingOfTheNature extends Enchantment {
    public static final String PATH = "blessing_of_the_nature";
    public BlessingOfTheNature() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.blessingOfTheNature),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.blessingOfTheNature),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.blessingOfTheNature)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.blessingOfTheNature;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.blessingOfTheNature;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.blessingOfTheNature;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.blessingOfTheNature;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.blessingOfTheNature && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.blessingOfTheNature;
    }
}
