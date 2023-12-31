package com.teampotato.enchantedpotato.enchantment.weapon;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.config.attributes.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class LoRATrainer extends Enchantment {
    public static final String PATH = "lora_trainer";
    public LoRATrainer() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.loraTrainer),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.loraTrainer),
                EnchantedPotato.EnchantedUtils.getSlots(EarlySetupInitializer.equipmentSlotConfig.loraTrainer)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return IsTreasureOnlyConfig.loraTrainer;
    }

    @Override
    public boolean isCurse() {
        return IsCurseConfig.loraTrainer;
    }

    @Override
    public boolean isTradeable() {
        return IsTradeableConfig.loraTrainer;
    }

    @Override
    public boolean isDiscoverable() {
        return IsDiscoverableConfig.loraTrainer;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return CanApplyAtEnchantingTableConfig.loraTrainer && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return IsAllowedOnBooksConfig.loraTrainer;
    }
}
