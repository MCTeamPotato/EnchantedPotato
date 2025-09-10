package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

public class FinalPower extends BaseEnchantment {
    public FinalPower() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public static boolean entityHasFinalPower(@NotNull LivingEntity livingSource) {
        Enchantment enchantment = ModEnchantments.FINAL_POWER.get();
        return EnchantmentHelper.getItemEnchantmentLevel(enchantment, livingSource.getOffhandItem()) != 0 || EnchantmentHelper.getItemEnchantmentLevel(enchantment, livingSource.getMainHandItem()) != 0;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.FINAL_POWER.get();
    }
}