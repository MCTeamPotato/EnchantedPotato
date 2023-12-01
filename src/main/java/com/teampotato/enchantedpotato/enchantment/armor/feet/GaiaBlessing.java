package com.teampotato.enchantedpotato.enchantment.armor.feet;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GaiaBlessing extends Enchantment {
    public static final String PATH = "gaia_bleesing";

    public static final UUID MODIFIER_UUID = UUID.fromString("9773dd69-bc04-4088-a175-22e3f3b5ab7e");
    public GaiaBlessing() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.gaiaBlessing),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.gaiaBlessing),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.gaiaBlessing)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.gaiaBlessing;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.gaiaBlessing;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.gaiaBlessing;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.gaiaBlessing;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.gaiaBlessing && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.gaiaBlessing;
    }
}
