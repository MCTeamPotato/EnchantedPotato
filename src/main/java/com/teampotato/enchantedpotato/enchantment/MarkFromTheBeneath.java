package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.markFromTheBeneath)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.markFromTheBeneath;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.markFromTheBeneath;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.markFromTheBeneath;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.markFromTheBeneath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.markFromTheBeneath && super.canApplyAtEnchantingTable(stack) && stack.getItem() instanceof PickaxeItem;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.markFromTheBeneath;
    }
}
