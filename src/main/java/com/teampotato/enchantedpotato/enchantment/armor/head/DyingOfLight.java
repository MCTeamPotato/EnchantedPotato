package com.teampotato.enchantedpotato.enchantment.armor.head;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class DyingOfLight extends Enchantment {
    public static final String PATH = "dying_of_light";

    public DyingOfLight() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.dyingOfLight),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.dyingOfLight),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.dyingOfLight)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.dyingOfLight;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.dyingOfLight;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.dyingOfLight;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.dyingOfLight;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.dyingOfLight && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.dyingOfLight;
    }
}
