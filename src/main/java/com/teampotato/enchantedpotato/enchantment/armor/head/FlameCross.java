package com.teampotato.enchantedpotato.enchantment.armor.head;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class FlameCross extends Enchantment {
    public static final String PATH = "flame_cross";
    public FlameCross() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.flameCross),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.flameCross),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.flameCross)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.flameCross;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.flameCross;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.flameCross;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.flameCross;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.flameCross && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.flameCross;
    }
}
