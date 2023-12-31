package com.teampotato.enchantedpotato.enchantment.armor.chest;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class HuaJin extends Enchantment {
    public static final String PATH = "hua_jin";
    public HuaJin() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.huaJin),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.huaJin),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.huaJin)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.huaJin;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.huaJin;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.huaJin;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.huaJin;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.huaJin && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.huaJin;
    }
}
