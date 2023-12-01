package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.softTouch)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.softTouch;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.softTouch;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.softTouch;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.softTouch;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.softTouch && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.softTouch;
    }
}

