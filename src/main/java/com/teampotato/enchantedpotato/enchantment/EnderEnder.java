package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class EnderEnder extends Enchantment {
    public static final String PATH = "ender_ender";
    public EnderEnder() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.enderEnder),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.enderEnder),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.enderEnder)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.enderEnder;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.enderEnder;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.enderEnder;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.enderEnder;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.enderEnder && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.enderEnder;
    }
}

