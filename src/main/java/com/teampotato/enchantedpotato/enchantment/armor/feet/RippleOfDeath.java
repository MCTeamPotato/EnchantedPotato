package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.rippleOfDeath;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.rippleOfDeath;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.rippleOfDeath;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.rippleOfDeath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.rippleOfDeath && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.rippleOfDeath;
    }
}
