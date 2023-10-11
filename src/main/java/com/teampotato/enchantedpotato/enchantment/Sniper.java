package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Sniper extends Enchantment {
    public static final String PATH = "sniper";
    public Sniper() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.sniper),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.sniper),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.sniper)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.sniper;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.sniper;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.sniper;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.sniper;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.sniper && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.sniper;
    }
}

