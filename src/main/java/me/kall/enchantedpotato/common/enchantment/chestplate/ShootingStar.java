package me.kall.enchantedpotato.common.enchantment.chestplate;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ShootingStar extends Enchantment {
    public ShootingStar() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        return stack.getItem() instanceof ElytraItem && super.canEnchant(stack);
    }
}
