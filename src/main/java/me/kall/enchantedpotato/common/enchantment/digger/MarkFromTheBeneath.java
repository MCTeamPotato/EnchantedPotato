package me.kall.enchantedpotato.common.enchantment.digger;

import me.kall.enchantedpotato.common.config.MarkFromTheBeneathConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class MarkFromTheBeneath extends BaseEnchantment {
    public static void onPlayerDig(PlayerEvent.@NotNull BreakSpeed event) {
        if (!event.isCanceled()) {
            Player player = event.getEntity();
            double height = player.getY();
            int level = getLevelInHands(ModEnchantments.MARK_FROM_THE_BENEATH, player, player.level());
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

    @Override
    public boolean isDisabled() {
        return DisableConfig.MARK_FROM_THE_BENEATH.get();
    }


    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.MINING_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(10, 5);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(8, 4);
    }

    @Override
    public int anvilCost() {
        return 1;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
