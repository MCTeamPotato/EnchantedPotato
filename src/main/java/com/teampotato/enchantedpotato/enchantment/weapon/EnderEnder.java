package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class EnderEnder extends Enchantment {
    public static final String PATH = "ender_ender";
    public static final Int2IntOpenHashMap ENDER_ENDER_TELEPORTATION_LIMIT_LEVEL_MAP = new Int2IntOpenHashMap();

    public EnderEnder() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.enderEnder),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.enderEnder),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.enderEnder)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.enderEnder;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.enderEnder;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.enderEnder;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.enderEnder;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.enderEnder && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.enderEnder;
    }

    @Override
    public int getMaxLevel() {
        return EarlySetupInitializer.maxLevelConfig.enderEnder;
    }
}

