package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.RunLikeHellConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

public class RunLikeHell extends Enchantment {
    public RunLikeHell() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public static void onLivingHurt(@NotNull LivingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel) {
            if (player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.RUN_LIKE_HELL.get()) == 0 || event.isCanceled()) return;
            float amount = event.getAmount();
            if (player.getHealth() - amount <= player.getMaxHealth() * RunLikeHellConfig.getPercent() && !((ExtendedPlayer)player).runLikeHell$isInCoolDown()) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, RunLikeHellConfig.INVISIBILITY_DURATION.get()));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, RunLikeHellConfig.SPEED_DURATION.get(), RunLikeHellConfig.SPEED_AMPLIFIER.get()));
                ((ExtendedPlayer)player).runLikeHell$setCoolDown(RunLikeHellConfig.COOL_DOWN.get());
            }
        }
    }

    public static void onLivingTick(@NotNull LivingEvent.LivingTickEvent event) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && !event.isCanceled() && event.getEntity() instanceof Mob mob) {
            LivingEntity target = mob.getTarget();
            if (target != null && target.hasEffect(MobEffects.INVISIBILITY)) {
                mob.setTarget(null);
            }
        }
    }

    public static void onChangeTarget(@NotNull LivingChangeTargetEvent event) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && event.getNewTarget() instanceof ServerPlayer player && player.hasEffect(MobEffects.INVISIBILITY)) event.setCanceled(true);
    }
}
