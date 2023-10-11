package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.pressurizedCollapse)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.pressurizedCollapse;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.pressurizedCollapse;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.pressurizedCollapse;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.pressurizedCollapse;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.pressurizedCollapse && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.pressurizedCollapse;
    }
}
