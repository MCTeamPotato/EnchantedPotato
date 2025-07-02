package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.config.MarkFromTheBeneathConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class MarkFromTheBeneath extends Enchantment {
    public MarkFromTheBeneath() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public int getMaxLevel() {
        return 3;
    }

    public static void onPlayerDig(PlayerEvent.@NotNull BreakSpeed event) {
        if (!event.isCanceled()) {
            Player player = event.getEntity();
            double height = player.getY();
            int level = player.getMainHandItem().getEnchantmentLevel(ModEnchantments.MARK_FROM_THE_BENEATH.get());
            if (level == 0) return;
            double validBaseHeight = MarkFromTheBeneathConfig.VALID_BASE_MAX_HEIGHT.get();
            double gainedHeightPerLevel = MarkFromTheBeneathConfig.GAINED_BASE_HEIGHT_PER_LEVEL.get() * (double) (level - 1);
            double validHeight = validBaseHeight + gainedHeightPerLevel;
            if (height > validHeight) return;
            double extraSpeed = heightToSpeed(MarkFromTheBeneathConfig.VALID_BASE_MIN_HEIGHT.get(), validBaseHeight, MarkFromTheBeneathConfig.MIN_SPEED_BONUS.get(), MarkFromTheBeneathConfig.MAX_SPEED_BONUS.get(), height);
            event.setNewSpeed(event.getOriginalSpeed() * (float) (1D + extraSpeed));
        }
    }

    public static double heightToSpeed(double bottom, double top, double slow, double quick, double height) {
        double slope = (quick - slow) / (top - bottom);
        return quick - slope * (height - bottom);
    }
}
