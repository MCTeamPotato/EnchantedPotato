package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class PressurizedCollapse extends Enchantment {

    public static final String PATH = "pressurized_collapse";
    public PressurizedCollapse() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.pressurizedCollapse),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.pressurizedCollapse),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.pressurizedCollapse)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.pressurizedCollapse;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.pressurizedCollapse;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.pressurizedCollapse;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.pressurizedCollapse;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.pressurizedCollapse && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.pressurizedCollapse;
    }
}
