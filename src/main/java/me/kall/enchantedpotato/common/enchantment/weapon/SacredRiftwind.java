package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class SacredRiftwind extends BaseEnchantment {
    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) return;
        if (event.getSource().getEntity() instanceof LivingEntity source) {
            int level = getLevelInHands(ModEnchantments.SACRED_RIFTWIND, source, source.level());
            if (level != 0) {
                float healthPercent = 1.0F - (source.getHealth() / source.getMaxHealth());
                float bonus = (float) (level * 0.1D * healthPercent);
                event.setAmount(event.getAmount() * (1.0F + bonus));
            }
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.SACRED_RIFTWIND.get();
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