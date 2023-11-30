package com.teampotato.enchantedpotato.enchantment.crossbow;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class GoldfishFireworks extends Enchantment {
    public static final String PATH = "goldfish_fireworks";
    public GoldfishFireworks() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.goldfishFireworks),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.goldfishFireworks),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.goldfishFireworks)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.goldfishFireworks;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.goldfishFireworks;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.goldfishFireworks;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.goldfishFireworks;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.goldfishFireworks && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.goldfishFireworks;
    }

    @Override
    public int getMaxLevel() {
        return EarlySetupInitializer.maxLevelConfig.goldfishFireworks;
    }
}

