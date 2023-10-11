package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Missile extends Enchantment {
    public static final String PATH = "missile";
    public Missile() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.missile),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.missile),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.missile)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.missile;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.missile;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.missile;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.missile;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.missile && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.missile;
    }
}
