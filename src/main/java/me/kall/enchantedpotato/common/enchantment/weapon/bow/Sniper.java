package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Sniper extends Enchantment {
    public Sniper() {
        super(Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public int getMaxLevel() {
        return 5;
    }
}
