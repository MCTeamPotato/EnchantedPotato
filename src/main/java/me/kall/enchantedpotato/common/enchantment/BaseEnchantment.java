package me.kall.enchantedpotato.common.enchantment;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class BaseEnchantment {
    public abstract boolean isDisabled();

    public abstract HolderSet<Item> supportedItems(HolderGetter<Item> items);
    public abstract int weight();
    public abstract int maxLevel();
    public abstract Enchantment.Cost dynamicCost();
    public abstract Enchantment.Cost constantCost();
    public abstract int anvilCost();
    public abstract EquipmentSlotGroup slotGroup();

    public Enchantment.EnchantmentDefinition definition(HolderGetter<Item> items) {
        return Enchantment.definition(supportedItems(items), weight(), maxLevel(), dynamicCost(), constantCost(), anvilCost(), slotGroup());
    }

    public static void removeEnchantment(@NotNull ItemStack stack, Enchantment enchantment) {
        EnchantmentHelper.updateEnchantments(stack, enchantments -> enchantments.removeIf(entry -> entry.value().equals(enchantment)));
    }

    public static boolean canUseAsWeapon(@NotNull ItemStack stack) {
        return stack.getAttributeModifiers().modifiers().stream().anyMatch(entry -> entry.attribute().value().equals(Attributes.ATTACK_DAMAGE.value()));
    }

    public static int getLevel(@NotNull ItemStack stack, @NotNull Level level, ResourceKey<Enchantment> enchantmentKey) {

        HolderLookup.RegistryLookup<Enchantment> enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return stack.getEnchantmentLevel(enchantments.getOrThrow(enchantmentKey));
    }

    public static int getLevelInHands(ResourceKey<Enchantment> enchantmentKey, LivingEntity player, Level serverLevel) {
        return Math.max(getLevel(player.getMainHandItem(), serverLevel, enchantmentKey), getLevel(player.getOffhandItem(), serverLevel, enchantmentKey));
    }
}
