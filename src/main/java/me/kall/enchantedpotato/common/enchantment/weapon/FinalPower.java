package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class FinalPower extends Enchantment {
    public FinalPower() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public static boolean entityHasFinalPower(@NotNull LivingEntity livingSource) {
        Enchantment enchantment = ModEnchantments.FINAL_POWER.get();
        return livingSource.getMainHandItem().getEnchantmentLevel(enchantment) != 0 || livingSource.getOffhandItem().getEnchantmentLevel(enchantment) != 0;
    }
}