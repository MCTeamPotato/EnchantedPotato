package com.teampotato.enchantedpotato.enchantment.breakable.elytra;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ShootingStar extends Enchantment {
    public static final String PATH = "shooting_star";
    public ShootingStar() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.shootingStar),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.shootingStar),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.shootingStar)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.shootingStar;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.shootingStar;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.shootingStar;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.shootingStar;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.shootingStar && stack.getItem() instanceof ElytraItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.shootingStar;
    }
}
