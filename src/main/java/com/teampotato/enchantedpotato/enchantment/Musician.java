package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Musician extends Enchantment {
    public static final String PATH = "musician";
    public Musician() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.musician),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.musician),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.musician)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.musician;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.musician;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.musician;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.musician;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.musician && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.musician;
    }
}

