package com.teampotato.enchantedpotato.enchantment.armor.legs;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.thisIsLeopard;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.thisIsLeopard;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.thisIsLeopard;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.thisIsLeopard;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.thisIsLeopard && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.thisIsLeopard;
    }
}
