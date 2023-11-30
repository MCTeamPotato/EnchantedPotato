package com.teampotato.enchantedpotato.enchantment.armor.chest;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class CaressingMoonlight extends Enchantment {
    public static final String PATH = "caressing_moonlight";
    public CaressingMoonlight() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.caressingMoonlight),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.caressingMoonlight),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.caressingMoonlight)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.caressingMoonlight;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.caressingMoonlight;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.caressingMoonlight;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.caressingMoonlight;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.caressingMoonlight && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.caressingMoonlight;
    }
}
