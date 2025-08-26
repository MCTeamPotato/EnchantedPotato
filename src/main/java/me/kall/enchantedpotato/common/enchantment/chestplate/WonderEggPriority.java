package me.kall.enchantedpotato.common.enchantment.chestplate;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import net.minecraft.world.entity.EquipmentSlot;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class WonderEggPriority extends BaseEnchantment {
    public WonderEggPriority() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.WONDER_EGG_PRIORITY.get();
    }
}
