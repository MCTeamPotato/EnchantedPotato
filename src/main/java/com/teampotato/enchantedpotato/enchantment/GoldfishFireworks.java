package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class GoldfishFireworks extends Enchantment {
    public static final String PATH = "goldfish_fireworks";
    public GoldfishFireworks() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.goldfishFireworks),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.goldfishFireworks),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.goldfishFireworks)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.goldfishFireworks;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.goldfishFireworks;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.goldfishFireworks;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.goldfishFireworks;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.goldfishFireworks && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.goldfishFireworks;
    }
}

