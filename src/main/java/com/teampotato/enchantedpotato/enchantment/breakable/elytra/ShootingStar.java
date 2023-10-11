package com.teampotato.enchantedpotato.enchantment.breakable.elytra;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.shootingStar)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.shootingStar;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.shootingStar;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.shootingStar;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.shootingStar;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.shootingStar && stack.getItem() instanceof ElytraItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.shootingStar;
    }
}
