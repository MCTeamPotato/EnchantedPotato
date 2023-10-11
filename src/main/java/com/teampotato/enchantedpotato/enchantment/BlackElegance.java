package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
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
        return EarlySetupInitializer.isTreasureOnlyConfig.blackElegance;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.blackElegance;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.blackElegance;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.blackElegance;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.blackElegance && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.blackElegance;
    }
}

