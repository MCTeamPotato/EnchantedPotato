package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.graceOfGungnir)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.graceOfGungnir;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.graceOfGungnir;
    }

    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.graceOfGungnir;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.graceOfGungnir;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.graceOfGungnir && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.graceOfGungnir;
    }
}
