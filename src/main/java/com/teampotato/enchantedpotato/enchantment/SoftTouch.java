package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class SoftTouch extends Enchantment {
    public static final String PATH = "soft_touch";
    public SoftTouch() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.softTouch),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.softTouch),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.softTouch)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.softTouch;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.softTouch;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.softTouch;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.softTouch;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.softTouch && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.softTouch;
    }
}

