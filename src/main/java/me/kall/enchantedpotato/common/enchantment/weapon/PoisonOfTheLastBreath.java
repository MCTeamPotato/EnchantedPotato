package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

public class PoisonOfTheLastBreath extends BaseEnchantment {
    public PoisonOfTheLastBreath() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.POISON_OF_THE_LAST_BREATH.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return BaseEnchantment.canUseAsWeapon(stack) && super.canEnchant(stack);
    }

    public static boolean has(@NotNull Player player) {
        Enchantment enchantment = ModEnchantments.POISON_OF_THE_LAST_BREATH.get();
        int level = Math.max(EnchantmentHelper.getItemEnchantmentLevel(enchantment, player.getOffhandItem()), EnchantmentHelper.getItemEnchantmentLevel(enchantment, player.getMainHandItem()));
        return level != 0;
    }
}
