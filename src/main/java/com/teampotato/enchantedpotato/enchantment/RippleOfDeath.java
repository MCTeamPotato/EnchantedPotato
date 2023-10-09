package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class RippleOfDeath extends Enchantment {

    public static final String PATH = "ripple_of_death";
    public RippleOfDeath() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.rippleOfDeath),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.rippleOfDeath),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.rippleOfDeath)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.rippleOfDeath;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.rippleOfDeath;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.rippleOfDeath;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.rippleOfDeath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.rippleOfDeath && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.rippleOfDeath;
    }
}
