package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class GurenNoYumiya extends Enchantment {
    public static final String PATH = "guren_no_yumiya";
    public GurenNoYumiya() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.gurenNoYumiya),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.gurenNoYumiya),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.gurenNoYumiya)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.gurenNoYumiya;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.gurenNoYumiya;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.gurenNoYumiya;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.gurenNoYumiya;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.gurenNoYumiya && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.gurenNoYumiya;
    }
}
