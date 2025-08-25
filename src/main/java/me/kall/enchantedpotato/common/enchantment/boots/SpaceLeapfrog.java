package me.kall.enchantedpotato.common.enchantment.boots;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SpaceLeapfrog extends Enchantment {
    public SpaceLeapfrog() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static double getDist(int enchantmentLevel) {
        return 5D * (double) enchantmentLevel;
    }

    public static float getExplodeRadius(int enchantmentLevel) {
        return 3F * (float) enchantmentLevel;
    }
}
