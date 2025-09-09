package me.kall.enchantedpotato.common.enchantment;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseEnchantment {
    public abstract boolean isDisabled();

    public abstract HolderSet<Item> supportedItems(HolderGetter<Item> items);
    public abstract Rarity rarity();
    public abstract Enchantment.Cost dynamicCost();
    public abstract Enchantment.Cost constantCost();
    public abstract int anvilCost();
    public abstract EquipmentSlotGroup slotGroup();
    public abstract ResourceKey<Enchantment> loc();

    public @Nullable TagKey<Enchantment> incompatible() {
        return null;
    }

    public int maxLevel() {
        return 1;
    }

    public Enchantment.EnchantmentDefinition definition(HolderGetter<Item> items) {
        return Enchantment.definition(isDisabled() ? HolderSet.empty() : supportedItems(items), rarity().getWeight(), maxLevel(), dynamicCost(), constantCost(), anvilCost(), slotGroup());
    }

    public void register(@NotNull BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        Enchantment.Builder builder = Enchantment.enchantment(definition(items));
        TagKey<Enchantment> incompatible = incompatible();
        if (incompatible != null) {
            builder = builder.exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(incompatible));
        }
        context.register(loc(), builder.build(loc().location()));
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static Enchantment.@NotNull Cost cost(int base, int perLevelAboveFirst) {
        return new Enchantment.Cost(base, perLevelAboveFirst);
    }

    public static void removeEnchantment(@NotNull ItemStack stack, Enchantment enchantment) {
        EnchantmentHelper.updateEnchantments(stack, enchantments -> enchantments.removeIf(entry -> entry.value().equals(enchantment)));
    }

    public static @NotNull Enchantment get(ResourceKey<Enchantment> key, @NotNull Level level) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return enchantments.getOrThrow(key).value();
    }

    public static int getLevel(@NotNull ItemStack stack, @NotNull Level level, ResourceKey<Enchantment> enchantmentKey) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return stack.getEnchantmentLevel(enchantments.getOrThrow(enchantmentKey));
    }

    public static int getLevelInHands(ResourceKey<Enchantment> enchantmentKey, @NotNull LivingEntity entity, Level level) {
        return Math.max(getLevel(entity.getMainHandItem(), level, enchantmentKey), getLevel(entity.getOffhandItem(), level, enchantmentKey));
    }

    public enum Rarity {
        COMMON(10),
        UNCOMMON(5),
        RARE(2),
        VERY_RARE(1);

        private final int weight;

        Rarity(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }
    }
}
