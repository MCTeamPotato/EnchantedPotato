package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class WendingWatersSereneLotus extends Enchantment {
    public static final String PATH = "wending_waters_erene_lotus";
    public WendingWatersSereneLotus() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.wendingWatersSereneLotus),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.wendingWatersSereneLotus),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.wendingWatersSereneLotus)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.wendingWatersSereneLotus && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.wendingWatersSereneLotus;
    }
}
