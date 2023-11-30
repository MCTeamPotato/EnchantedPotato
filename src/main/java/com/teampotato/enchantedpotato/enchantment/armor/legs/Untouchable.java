package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Untouchable extends Enchantment {
    public static final String PATH = "untouchable";

    public Untouchable() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.untouchable),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.untouchable),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.untouchable)
        );
    }
    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.untouchable;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.untouchable;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.untouchable;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.untouchable;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.untouchable && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.untouchable;
    }
}
