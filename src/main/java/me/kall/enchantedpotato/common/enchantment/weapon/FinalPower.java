package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class FinalPower extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new FinalPower();

    @Override
    public boolean isDisabled() {
        return DisableConfig.FINAL_POWER.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.VERY_RARE;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(35, 25);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(30, 20);
    }

    @Override
    public int anvilCost() {
        return 6;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.FINAL_POWER;
    }

    public static boolean has(@NotNull LivingEntity livingSource) {
        return getLevelInHands(ModEnchantments.FINAL_POWER, livingSource, livingSource.level()) != 0;
    }
}