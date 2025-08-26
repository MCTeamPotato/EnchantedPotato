package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import net.minecraft.world.entity.EquipmentSlot;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LotusInWater extends BaseEnchantment {
    public LotusInWater() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.LOTUS_IN_WATER.get();
    }
}
