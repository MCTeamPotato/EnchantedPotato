package com.teampotato.enchantedpotato.enchantment.bow;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class GraceOfGungnir extends Enchantment {
    public static final String PATH = "grace_of_gungnir";
    public GraceOfGungnir() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.graceOfGungnir),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.graceOfGungnir),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.graceOfGungnir)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.graceOfGungnir;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.graceOfGungnir;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.graceOfGungnir;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.graceOfGungnir;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.graceOfGungnir && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.graceOfGungnir;
    }
}
