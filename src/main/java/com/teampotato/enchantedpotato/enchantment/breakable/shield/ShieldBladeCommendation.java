package com.teampotato.enchantedpotato.enchantment.breakable.shield;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.shieldBladeCommendation)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.shieldBladeCommendation;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.shieldBladeCommendation;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.shieldBladeCommendation && stack.getItem() instanceof ShieldItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.shieldBladeCommendation;
    }
}
