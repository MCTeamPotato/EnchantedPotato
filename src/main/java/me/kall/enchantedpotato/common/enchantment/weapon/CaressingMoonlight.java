package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.CaressingMoonlightConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class CaressingMoonlight extends Enchantment {
    public CaressingMoonlight() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static void onLivingHurt(@NotNull LivingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            Enchantment enchantment = ModEnchantments.CARESSING_MOONLIGHT.get();
            int level = Math.max(player.getMainHandItem().getEnchantmentLevel(enchantment), player.getOffhandItem().getEnchantmentLevel(enchantment));
            if (level == 0) return;
            float count = 0.0F;
            for (MobEffectInstance activeEffect : event.getEntity().getActiveEffects()) {
                if (activeEffect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL)) count = count + 1.0F;
            }
            float baseDamage = CaressingMoonlightConfig.BASE_DAMAGE_FOR_EACH_EFFECT.get().floatValue();
            float gainedDamagePerLevel = CaressingMoonlightConfig.GAINED_DAMAGE_FOR_EACH_EFFECT_PER_LEVEL.get().floatValue();
            float damageBonus = (baseDamage + gainedDamagePerLevel * (float) (level - 1)) * count;
            event.setAmount(event.getAmount() + damageBonus);
        }
    }
}
