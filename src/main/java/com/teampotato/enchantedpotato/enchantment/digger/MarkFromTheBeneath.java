package com.teampotato.enchantedpotato.enchantment.digger;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MarkFromTheBeneath extends Enchantment {
    public static AttributeModifier DIG_SPEED_MODIFIER = null;
    public static final String PATH = "mark_from_the_beneath";

    public static final UUID DIG_SPEED_MODIFIER_UUID = UUID.fromString("9283dd69-bc04-4066-a175-73e3f3b5ab7e");
    public MarkFromTheBeneath() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.markFromTheBeneath),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.markFromTheBeneath),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.markFromTheBeneath)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.markFromTheBeneath;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.markFromTheBeneath;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.markFromTheBeneath;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.markFromTheBeneath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.markFromTheBeneath && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.markFromTheBeneath;
    }
}
