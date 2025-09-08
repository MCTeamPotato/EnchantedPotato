package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class ArmorBreaking extends BaseEnchantment {
    public static final String ARMOR_BREAKING_KEY = "ArmorBreakingLevel";

    @Override
    public boolean isDisabled() {
        return DisableConfig.ARMOR_BREAKING.get();
    }

    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel serverLevel && !event.isCanceled()) {
            int level = getLevelInHands(ModEnchantments.ARMOR_BREAKING, player, serverLevel);
            if (level == 0) return;
            LivingEntity entity = event.getEntity();
            entity.getPersistentData().putInt(ARMOR_BREAKING_KEY, level);
            ((ExtendedLivingEntity)entity).armorBreaking$getAttribute().setBaseValue(ArmorBreakingConfig.BASE_DURATION.get().doubleValue() + ArmorBreakingConfig.GAINED_DURATION_PER_LEVEL.get().doubleValue() * (double) (level - 1));
        }
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        if (isDisabled()) return HolderSet.empty();
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int maxLevel() {
        return 0;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
