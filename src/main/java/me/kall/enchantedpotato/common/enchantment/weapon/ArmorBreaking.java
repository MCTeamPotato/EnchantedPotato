package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class ArmorBreaking extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new ArmorBreaking();

    public static final String ARMOR_BREAKING_KEY = "ArmorBreakingLevel";

    @Override
    public boolean isDisabled() {
        return DisableConfig.ARMOR_BREAKING.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 5;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(25, 15);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(20, 12);
    }

    @Override
    public int anvilCost() {
        return 4;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }

    @Override
    public ResourceKey<Enchantment> loc() {
        return ModEnchantments.ARMOR_BREAKING;
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

}
