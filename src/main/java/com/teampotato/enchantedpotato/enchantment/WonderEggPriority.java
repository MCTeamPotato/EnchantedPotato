package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class WonderEggPriority extends Enchantment {
    public static final String PATH = "wonder_egg_priority";
    public WonderEggPriority() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.wonderEggPriority),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.wonderEggPriority),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.wonderEggPriority)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.wonderEggPriority;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.wonderEggPriority;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.wonderEggPriority;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.wonderEggPriority;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.wonderEggPriority && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.wonderEggPriority;
    }
}
