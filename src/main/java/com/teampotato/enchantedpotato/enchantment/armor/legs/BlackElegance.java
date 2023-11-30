package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BlackElegance extends Enchantment {
    public static final String PATH = "black_elegance";
    public BlackElegance() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.blackElegance),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.blackElegance),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.blackElegance)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.blackElegance;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.blackElegance;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.blackElegance;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.blackElegance;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.blackElegance && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.blackElegance;
    }
}

