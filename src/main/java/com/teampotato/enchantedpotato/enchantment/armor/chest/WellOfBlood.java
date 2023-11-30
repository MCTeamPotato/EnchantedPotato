package com.teampotato.enchantedpotato.enchantment.armor.chest;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.wellOfBlood;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.wellOfBlood;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.wellOfBlood;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.wellOfBlood;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.wellOfBlood && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.wellOfBlood;
    }
}
