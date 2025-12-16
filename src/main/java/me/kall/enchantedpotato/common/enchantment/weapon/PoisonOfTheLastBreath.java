package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class PoisonOfTheLastBreath extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new PoisonOfTheLastBreath();

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(12, 8);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(10, 6);
    }

    @Override
    public int anvilCost() {
        return 1;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.POISON_OF_THE_LAST_BREATH;
    }

    public static boolean has(@NotNull Player player) {
        return getLevelInHands(ModEnchantments.POISON_OF_THE_LAST_BREATH, player, player.level()) != 0;
    }
}
