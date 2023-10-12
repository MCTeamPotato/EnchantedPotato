package com.teampotato.enchantedpotato.enchantment.armor.head;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.wonderEggPriority;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.wonderEggPriority;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.wonderEggPriority;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.wonderEggPriority;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.wonderEggPriority && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.wonderEggPriority;
    }
}
