package com.teampotato.enchantedpotato.enchantment.breakable.shield;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.boneSuckalaka)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.boneSuckalaka;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.boneSuckalaka;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.boneSuckalaka;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.boneSuckalaka;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.boneSuckalaka && stack.getItem() instanceof ShieldItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.boneSuckalaka;
    }
}