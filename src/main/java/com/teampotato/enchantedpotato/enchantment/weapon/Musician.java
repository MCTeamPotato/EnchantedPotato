package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.musician)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.musician;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.musician;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.musician;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.musician;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.musician && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.musician;
    }
}

