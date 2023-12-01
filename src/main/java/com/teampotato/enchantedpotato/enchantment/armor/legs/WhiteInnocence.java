package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class WhiteInnocence extends Enchantment {
    public static final String PATH = "white_innocence";
    public WhiteInnocence() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.whiteInnocence),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.whiteInnocence),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.whiteInnocence)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.whiteInnocence;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.whiteInnocence;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.whiteInnocence;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.whiteInnocence;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.whiteInnocence && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.whiteInnocence;
    }
}

