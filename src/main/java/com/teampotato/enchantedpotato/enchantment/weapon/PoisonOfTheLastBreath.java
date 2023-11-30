package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.reloadable.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class PoisonOfTheLastBreath extends Enchantment {
    public static final String PATH = "poison_of_the_last_breath";
    public PoisonOfTheLastBreath() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.poisonOfTheLastBreath),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.poisonOfTheLastBreath),
                EnchantedPotato.getSlots(EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.poisonOfTheLastBreath && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.poisonOfTheLastBreath;
    }
}
