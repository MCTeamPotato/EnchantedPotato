package com.teampotato.enchantedpotato.enchantment.breakable.shield;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ArmsDrum extends Enchantment {
    public static final String PATH = "arms_drum";
    public ArmsDrum() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.armsDrum),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.armsDrum),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.armsDrum)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.armsDrum;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.armsDrum;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.armsDrum;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.armsDrum;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.armsDrum && super.canApplyAtEnchantingTable(stack) && stack.getItem() instanceof ShieldItem;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.armsDrum;
    }
}

