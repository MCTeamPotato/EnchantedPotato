package com.teampotato.enchantedpotato.enchantment.breakable.shield;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BoneSuckalaka extends Enchantment {
    public static final String PATH = "bone_suckalaka";
    public BoneSuckalaka() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.boneSuckalaka),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.boneSuckalaka),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.boneSuckalaka)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.boneSuckalaka;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.boneSuckalaka;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.boneSuckalaka;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.boneSuckalaka;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.boneSuckalaka && stack.getItem() instanceof ShieldItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.boneSuckalaka;
    }
}