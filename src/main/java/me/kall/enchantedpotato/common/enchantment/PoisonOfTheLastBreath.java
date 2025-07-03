package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class PoisonOfTheLastBreath extends Enchantment {
    public PoisonOfTheLastBreath() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || item instanceof SwordItem || item instanceof BowItem || item instanceof TridentItem;
    }

    public static boolean has(@NotNull Player player) {
        Enchantment enchantment = ModEnchantments.CARESSING_MOONLIGHT.get();
        int level = Math.max(player.getMainHandItem().getEnchantmentLevel(enchantment), player.getOffhandItem().getEnchantmentLevel(enchantment));
        return level != 0;
    }
}
