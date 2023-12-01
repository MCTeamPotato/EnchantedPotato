package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class TrueMan extends Enchantment {
    public static final String PATH = "true_man";
    public TrueMan() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.trueMan),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.trueMan),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.trueMan)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.trueMan;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.trueMan;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.trueMan;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.trueMan;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.trueMan && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.trueMan;
    }
}
