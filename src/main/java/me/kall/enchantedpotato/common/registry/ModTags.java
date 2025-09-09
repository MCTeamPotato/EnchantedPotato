package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {
    public static final TagKey<Enchantment> BOW_COM = TagKey.create(Registries.ENCHANTMENT, EnchantedPotato.loc("exclusive_set/bow_com"));
    public static final TagKey<Enchantment> MENDING_COM = TagKey.create(Registries.ENCHANTMENT, EnchantedPotato.loc("exclusive_set/mending_com"));
}
