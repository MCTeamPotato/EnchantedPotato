package com.teampotato.enchantedpotato.enchantment.breakable.shield;

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
        return EarlySetupInitializer.isTreasureOnlyConfig.armsDrum;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.armsDrum;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.armsDrum;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.armsDrum;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.armsDrum && super.canApplyAtEnchantingTable(stack) && stack.getItem() instanceof ShieldItem;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.armsDrum;
    }
}

