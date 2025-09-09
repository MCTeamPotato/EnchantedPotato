package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class LotusInWater extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new LotusInWater();

    @Override
    public boolean isDisabled() {
        return DisableConfig.LOTUS_IN_WATER.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(8, 4);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(6, 3);
    }

    @Override
    public int anvilCost() {
        return 1;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.FEET;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.LOTUS_IN_WATER;
    }
}
