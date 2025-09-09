package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class SacredRiftwind extends BaseEnchantment {
    @Override
    public boolean isDisabled() {
        return DisableConfig.SACRED_RIFTWIND.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public int maxLevel() {
        return 5;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(28, 18);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(22, 15);
    }

    @Override
    public int anvilCost() {
        return 5;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

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

}