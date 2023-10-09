package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
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
        return EarlySetupInitializer.isTreasureOnlyConfig.untouchable;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.untouchable;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.untouchable;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.untouchable;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.untouchable && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.untouchable;
    }
}
