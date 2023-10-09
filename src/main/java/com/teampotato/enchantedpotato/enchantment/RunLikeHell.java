package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;


public class RunLikeHell extends Enchantment {

    public static final String PATH = "run_like_hell";

    public RunLikeHell() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.runLikeHell),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.runLikeHell),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.runLikeHell)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.runLikeHell;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.runLikeHell;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.runLikeHell;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.runLikeHell;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.runLikeHell && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.runLikeHell;
    }
}
