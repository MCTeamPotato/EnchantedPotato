package com.teampotato.enchantedpotato.enchantment.armor.head;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class DyingOfLight extends Enchantment {
    public static final String PATH = "dying_of_light";

    public DyingOfLight() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.dyingOfLight),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.dyingOfLight),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.dyingOfLight)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.dyingOfLight;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.dyingOfLight;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.dyingOfLight;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.dyingOfLight;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.dyingOfLight && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.dyingOfLight;
    }
}
