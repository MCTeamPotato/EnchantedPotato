package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class LawOfInertia extends Enchantment {
    public static final String PATH = "law_of_inertia";
    public LawOfInertia() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.lawOfInertia),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.lawOfInertia),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.lawOfInertia)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.lawOfInertia;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.lawOfInertia;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.lawOfInertia;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.lawOfInertia;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.lawOfInertia && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.lawOfInertia;
    }
}
