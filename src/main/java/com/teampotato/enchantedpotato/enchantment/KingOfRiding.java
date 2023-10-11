package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class KingOfRiding extends Enchantment {
    public static final String PATH = "king_of_riding";
    public KingOfRiding() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.kingOfRiding),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.kingOfRiding),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.kingOfRiding)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.kingOfRiding;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.kingOfRiding;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.kingOfRiding;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.kingOfRiding;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.kingOfRiding && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.kingOfRiding;
    }
}
