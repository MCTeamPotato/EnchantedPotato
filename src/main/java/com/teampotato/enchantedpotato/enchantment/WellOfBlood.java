package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class WellOfBlood extends Enchantment {
    public static final String PATH = "well_of_blood";
    public WellOfBlood() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.wellOfBlood),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.wellOfBlood),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.wellOfBlood)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.wellOfBlood;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.wellOfBlood;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.wellOfBlood;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.wellOfBlood;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.wellOfBlood && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.wellOfBlood;
    }
}
