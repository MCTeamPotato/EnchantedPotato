package com.teampotato.enchantedpotato.enchantment.breakable.shield;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ShieldBladeCommendation extends Enchantment {

    public static final String PATH = "shield_blade_commendation";
    public ShieldBladeCommendation() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.shieldBladeCommendation),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.shieldBladeCommendation),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.shieldBladeCommendation)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.shieldBladeCommendation;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.shieldBladeCommendation && stack.getItem() instanceof ShieldItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.shieldBladeCommendation;
    }
}
