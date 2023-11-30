package com.teampotato.enchantedpotato.enchantment.trident;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Missile extends Enchantment {
    public static final String PATH = "missile";
    public Missile() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.missile),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.missile),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.missile)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.missile;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.missile;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.missile;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.missile;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.missile && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.missile;
    }
}
