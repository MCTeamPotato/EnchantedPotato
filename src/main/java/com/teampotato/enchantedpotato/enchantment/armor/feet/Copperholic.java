package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.copperholic)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.copperholic;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.copperholic;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.copperholic;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.copperholic;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.copperholic && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.copperholic;
    }
}

