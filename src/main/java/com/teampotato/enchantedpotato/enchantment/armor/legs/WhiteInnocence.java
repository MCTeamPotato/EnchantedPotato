package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.whiteInnocence)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.whiteInnocence;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.whiteInnocence;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.whiteInnocence;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.whiteInnocence;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.whiteInnocence && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.whiteInnocence;
    }
}

