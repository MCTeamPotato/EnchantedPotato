package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.oceanHued)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.oceanHued;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.oceanHued;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.oceanHued;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.oceanHued;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.oceanHued && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.oceanHued;
    }
}
