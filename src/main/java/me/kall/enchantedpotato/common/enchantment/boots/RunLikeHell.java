package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.RunLikeHellConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.jetbrains.annotations.NotNull;

public class RunLikeHell extends BaseEnchantment {
    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel) {
            if (getLevel(player.getItemBySlot(EquipmentSlot.FEET), serverLevel, ModEnchantments.RUN_LIKE_HELL) == 0 || event.isCanceled()) return;
            float amount = event.getAmount();
            if (player.getHealth() - amount <= player.getMaxHealth() * RunLikeHellConfig.getPercent() && !((ExtendedPlayer)player).runLikeHell$isInCoolDown()) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, RunLikeHellConfig.INVISIBILITY_DURATION.get()));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, RunLikeHellConfig.SPEED_DURATION.get(), RunLikeHellConfig.SPEED_AMPLIFIER.get()));
                ((ExtendedPlayer)player).runLikeHell$setCoolDown(RunLikeHellConfig.COOL_DOWN.get());
            }
        }
    }

    public static void onLivingTick(@NotNull EntityTickEvent.Pre event) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && !event.isCanceled() && event.getEntity() instanceof Mob mob) {
            LivingEntity target = mob.getTarget();
            if (target != null && target.hasEffect(MobEffects.INVISIBILITY)) {
                mob.setTarget(null);
            }
        }
    }

    public static void onChangeTarget(@NotNull LivingChangeTargetEvent event) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && event.getNewAboutToBeSetTarget() instanceof ServerPlayer player && player.hasEffect(MobEffects.INVISIBILITY)) event.setCanceled(true);
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.RUN_LIKE_HELL.get();
    }


    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int maxLevel() {
        return 0;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.FEET;
    }
}
