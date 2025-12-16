package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.LoRATrainerConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class LoRATrainer extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new LoRATrainer();

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
        return 3;
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
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.LORA_TRAINER;
    }

    public static void onLivingDamage(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel) {
            int level = getLevelInHands(ModEnchantments.LORA_TRAINER, player, serverLevel);
            if (level == 0) return;
            LivingEntity entity = event.getEntity();
            int killCount = player.getStats().getValue(Stats.ENTITY_KILLED.get(entity.getType()));
            int baseKillCountRequired = LoRATrainerConfig.BASE_KILL_COUNT.get();
            int savedKillCountPerLevel = LoRATrainerConfig.SAVED_KILL_COUNT_PER_LEVEL.get();
            int required = baseKillCountRequired - savedKillCountPerLevel * (level - 1);
            if (killCount >= required) {
                float baseDamageBonus = 1F + LoRATrainerConfig.BASE_DAMAGE_BONUS.get().floatValue();
                float gainedDamageBonusPerLevel = LoRATrainerConfig.GAINED_DAMAGE_BONUS_PER_LEVEL.get().floatValue();
                float damageBonus = baseDamageBonus + gainedDamageBonusPerLevel * (level - 1);
                event.setAmount(event.getAmount() * damageBonus);
            }
        }
    }
}
