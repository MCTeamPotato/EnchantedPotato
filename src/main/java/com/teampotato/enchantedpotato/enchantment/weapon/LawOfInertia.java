package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class LawOfInertia extends Enchantment {
    public static final String PATH = "law_of_inertia";
    public LawOfInertia() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.lawOfInertia),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.lawOfInertia),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.lawOfInertia)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.lawOfInertia;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.lawOfInertia;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.lawOfInertia;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.lawOfInertia;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.lawOfInertia && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.lawOfInertia;
    }
}
