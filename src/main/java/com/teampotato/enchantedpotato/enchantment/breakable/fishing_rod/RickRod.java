package com.teampotato.enchantedpotato.enchantment.breakable.fishing_rod;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.FishingRodItem;
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
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.rickRod)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.rickRod;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.rickRod;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.rickRod;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.rickRod;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.rickRod && stack.getItem() instanceof FishingRodItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.rickRod;
    }
}

