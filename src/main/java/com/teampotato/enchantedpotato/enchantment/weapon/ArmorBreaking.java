package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ArmorBreaking extends Enchantment {
    public static final String PATH = "armor_breaking";

    public static AttributeModifier ARMOR_VALUE_MODIFIER = null;

    public static final UUID ARMOR_VALUE_MODIFIER_UUID = UUID.fromString("9283dd59-bc04-4066-a175-62e3f2b5ab6e");
    public static AttributeModifier ARMOR_TOUGHNESS_MODIFIER = null;

    public static final UUID ARMOR_TOUGHNESS_MODIFIER_UUID = UUID.fromString("4283dd69-bc04-4066-a175-78e3f3b8ab7e");

    public ArmorBreaking() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.armorBreaking),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.armorBreaking),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.armorBreaking)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.armorBreaking;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.armorBreaking;
    }

    public boolean isTradeable() {
        return IsTradeableConfig.armorBreaking;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.armorBreaking;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.armorBreaking && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.armorBreaking;
    }
}
