package com.teampotato.enchantedpotato.enchantment;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class LoRATrainer extends Enchantment {
    public static final String PATH = "lora_trainer";
    public LoRATrainer() {
        super(
                Rarity.valueOf(EarlySetupInitializer.rarityConfig.loraTrainer),
                EnchantmentCategory.valueOf(EarlySetupInitializer.enchantmentCategoryConfig.loraTrainer),
                Utils.getSlots(EarlySetupInitializer.equipmentSlotConfig.loraTrainer)
        );
    }

    @Override
    public boolean isTreasureOnly() {
        return EarlySetupInitializer.isTreasureOnlyConfig.loraTrainer;
    }

    @Override
    public boolean isCurse() {
        return EarlySetupInitializer.isCurseConfig.loraTrainer;
    }

    @Override
    public boolean isTradeable() {
        return EarlySetupInitializer.isTradeableConfig.loraTrainer;
    }

    @Override
    public boolean isDiscoverable() {
        return EarlySetupInitializer.isDiscoverableConfig.loraTrainer;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return EarlySetupInitializer.canApplyAtEnchantingTableConfig.loraTrainer && super.canApplyAtEnchantingTable(stack) && (item instanceof SwordItem || item instanceof TridentItem);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return EarlySetupInitializer.isAllowedOnBooksConfig.loraTrainer;
    }
}
