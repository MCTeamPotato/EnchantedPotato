package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Mercy extends BaseEnchantment {
    @Override
    public boolean isDisabled() {
        return DisableConfig.MERCY.get();
    }

    public static void onLivingDamage(@NotNull LivingIncomingDamageEvent event) {
        LivingEntity attacked = event.getEntity();
        if (!event.isCanceled() && attacked.level() instanceof ServerLevel level) {
            LivingEntity attacker = null;
            if (event.getSource().getEntity() instanceof LivingEntity sourceEntity) {
                attacker = sourceEntity;
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
                attacker = sourceDirectEntity;
            }
            if (attacker == null) return;
            if (getLevelInHands(ModEnchantments.MERCY, attacker, level) != 0) {
                float health = attacked.getHealth();
                float damage = event.getAmount();
                if (health < damage) damage = health - 1.0F;
                if (damage < 0.0F) damage = 0.0F;
                event.setAmount(damage);
            }
        }
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
}
