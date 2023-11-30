package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.wendingWatersSereneLotus;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.wendingWatersSereneLotus && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.wendingWatersSereneLotus;
    }
}
