package com.teampotato.enchantedpotato.enchantment.crossbow;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.multiLoad)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.multiLoad;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.multiLoad;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.multiLoad;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.multiLoad;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.multiLoad && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.multiLoad;
    }
}

