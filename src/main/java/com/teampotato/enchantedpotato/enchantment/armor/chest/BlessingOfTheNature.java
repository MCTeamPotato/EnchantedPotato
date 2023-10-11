package com.teampotato.enchantedpotato.enchantment.armor.chest;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.blessingOfTheNature)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.blessingOfTheNature;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.blessingOfTheNature;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.blessingOfTheNature;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.blessingOfTheNature;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.blessingOfTheNature && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.blessingOfTheNature;
    }
}
