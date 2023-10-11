package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class Copperholic extends Enchantment {
    public static final String PATH = "copperholic";
    public Copperholic() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.copperholic),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.copperholic),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.copperholic)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.copperholic;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.copperholic;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.copperholic;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.copperholic;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.copperholic && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.copperholic;
    }
}

