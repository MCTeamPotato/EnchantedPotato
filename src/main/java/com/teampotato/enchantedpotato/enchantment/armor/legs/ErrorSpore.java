package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ErrorSpore extends Enchantment {
    public static final String PATH = "error_spore";

    public static final Int2FloatOpenHashMap ERROR_SPORE_LEVEL_MAP = new Int2FloatOpenHashMap();

    public ErrorSpore() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.errorSpore),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.errorSpore),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.errorSpore)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.errorSpore;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.errorSpore;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.errorSpore;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.errorSpore;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.errorSpore && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.errorSpore;
    }

    @Override
    public int getMaxLevel() {
        return EarlySetupInitializer.maxLevelConfig.errorSpore;
    }
}

