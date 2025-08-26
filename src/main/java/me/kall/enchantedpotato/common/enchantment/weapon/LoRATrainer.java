package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.LoRATrainerConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class LoRATrainer extends BaseEnchantment {

    public LoRATrainer() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.LORA_TRAINER.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return BaseEnchantment.canUseAsWeapon(stack.getItem()) && super.canEnchant(stack);
    }

    public static void onLivingDamage(@NotNull LivingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (stack.getEnchantmentLevel(ModEnchantments.LORA_TRAINER.get()) == 0) stack = player.getItemBySlot(EquipmentSlot.OFFHAND);
            int level = stack.getEnchantmentLevel(ModEnchantments.LORA_TRAINER.get());
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
