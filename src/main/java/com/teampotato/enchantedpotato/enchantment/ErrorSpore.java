package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ErrorSpore extends Enchantment {
    public static final ErrorSpore ERROR_SPORE = new ErrorSpore();

    public static final String PATH = "error_spore";

    public static final Int2FloatOpenHashMap ERROR_SPORE_LEVEL_MAP = new Int2FloatOpenHashMap();
    public static int maxLevel;

    public ErrorSpore() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.errorSpore),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.errorSpore),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.errorSpore)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.errorSpore;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.errorSpore;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.errorSpore;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.errorSpore;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.errorSpore && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.errorSpore;
    }

    @Override
    public int getMaxLevel() {
        return EarlySetupInitializer.maxLevelConfig.errorSpore;
    }
}

