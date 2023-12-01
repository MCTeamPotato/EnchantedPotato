package com.teampotato.enchantedpotato.enchantment.crossbow;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class MultiLoad extends Enchantment {
    public static final String PATH = "multi_load";
    public MultiLoad() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.multiLoad),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.multiLoad),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.multiLoad)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.multiLoad;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.multiLoad;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.multiLoad;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.multiLoad;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.multiLoad && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.multiLoad;
    }
}

