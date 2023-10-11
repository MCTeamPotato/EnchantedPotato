package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BlackParade extends Enchantment {
    public static final String PATH = "black_parade";
    public BlackParade() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.blackParade),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.blackParade),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.blackParade)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.blackParade;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.blackParade;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.blackParade;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.blackParade;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.blackParade && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.blackParade;
    }
}
