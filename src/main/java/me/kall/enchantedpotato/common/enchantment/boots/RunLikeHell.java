package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.RunLikeHellConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

public class RunLikeHell extends BaseEnchantment {
    public RunLikeHell() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public static void onLivingHurt(@NotNull LivingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer)) return;
        ServerPlayer player = (ServerPlayer) event.getEntity();
        if (!event.isCanceled() && player.level instanceof ServerLevel) {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.RUN_LIKE_HELL.get(), player.getItemBySlot(EquipmentSlot.FEET)) == 0 || event.isCanceled()) return;
            float amount = event.getAmount();
            if (player.getHealth() - amount <= player.getMaxHealth() * RunLikeHellConfig.getPercent() && !((ExtendedPlayer)player).runLikeHell$isInCoolDown()) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, RunLikeHellConfig.INVISIBILITY_DURATION.get()));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, RunLikeHellConfig.SPEED_DURATION.get(), RunLikeHellConfig.SPEED_AMPLIFIER.get()));
                ((ExtendedPlayer)player).runLikeHell$setCoolDown(RunLikeHellConfig.COOL_DOWN.get());
            }
        }
    }

    public static void onLivingTick(@NotNull LivingEvent.LivingUpdateEvent event) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && !event.isCanceled() && event.getEntity() instanceof Mob) {
            Mob mob = (Mob) event.getEntity();
            LivingEntity target = mob.getTarget();
            if (target != null && target.hasEffect(MobEffects.INVISIBILITY)) {
                mob.setTarget(null);
            }
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.RUN_LIKE_HELL.get();
    }
}
