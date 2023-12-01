package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class OceanHued extends Enchantment {
    public static final String PATH = "ocean_hued";
    public OceanHued() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.oceanHued),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.oceanHued),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.oceanHued)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.oceanHued;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.oceanHued;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.oceanHued;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.oceanHued;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.oceanHued && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.oceanHued;
    }
}
