package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
        return IsTreasureOnlyConfig.runLikeHell;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.runLikeHell;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.runLikeHell;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.runLikeHell;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.runLikeHell && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.runLikeHell;
    }
}
