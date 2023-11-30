package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class KingOfRiding extends Enchantment {
    public static final String PATH = "king_of_riding";
    public KingOfRiding() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.kingOfRiding),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.kingOfRiding),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.kingOfRiding)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.kingOfRiding;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.kingOfRiding;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.kingOfRiding;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.kingOfRiding;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.kingOfRiding && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.kingOfRiding;
    }
}
