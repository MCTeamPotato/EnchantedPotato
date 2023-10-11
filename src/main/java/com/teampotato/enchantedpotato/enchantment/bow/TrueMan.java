package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class TrueMan extends Enchantment {
    public static final String PATH = "true_man";
    public TrueMan() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.trueMan),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.trueMan),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.trueMan)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.trueMan;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.trueMan;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.trueMan;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.trueMan;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.trueMan && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.trueMan;
    }
}
