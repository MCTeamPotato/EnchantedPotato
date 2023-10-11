package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
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
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.poisonOfTheLastBreath;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.poisonOfTheLastBreath && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.poisonOfTheLastBreath;
    }
}
