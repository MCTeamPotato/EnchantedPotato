package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class RickRod extends Enchantment {
    public static final String PATH = "rick_rod";
    public RickRod() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.rickRod),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.rickRod),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.rickRod)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.rickRod;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.rickRod;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.rickRod;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.rickRod;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.rickRod && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.rickRod;
    }
}

