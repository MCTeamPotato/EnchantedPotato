package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ThisIsLeopard extends Enchantment {
    public static final String PATH = "this_is_leopard";
    public ThisIsLeopard() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.thisIsLeopard),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.thisIsLeopard),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.thisIsLeopard;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.thisIsLeopard;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.thisIsLeopard;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.thisIsLeopard;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.thisIsLeopard && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.thisIsLeopard;
    }
}
