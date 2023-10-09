package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.caressingMoonlight)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.caressingMoonlight;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.caressingMoonlight;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.caressingMoonlight;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.caressingMoonlight;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.caressingMoonlight && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.caressingMoonlight;
    }
}
