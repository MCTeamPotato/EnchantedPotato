package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BlackParade extends Enchantment {
    public static final String PATH = "black_parade";
    public BlackParade() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.blackParade),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.blackParade),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.blackParade)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.blackParade;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.blackParade;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.blackParade;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.blackParade;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.blackParade && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.blackParade;
    }
}
