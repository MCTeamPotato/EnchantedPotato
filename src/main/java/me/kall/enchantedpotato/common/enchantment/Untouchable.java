package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.UntouchableConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class Untouchable extends Enchantment {
    public Untouchable() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
    }

    public int getMaxLevel() {
        return 3;
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel) {
            int enchantmentLevel = player.getItemBySlot(EquipmentSlot.LEGS).getEnchantmentLevel(ModEnchantments.UNTOUCHABLE.get());
            if (enchantmentLevel == 0 || ((ExtendedPlayer)player).untouchable$isInCoolDown()) return;
            int coolDown = UntouchableConfig.BASIC_COOLDOWN.get() - UntouchableConfig.SAVED_COOLDOWN_PER_LEVEL.get() * (enchantmentLevel - 1);
            if (coolDown > 0) ((ExtendedPlayer)player).untouchable$setCoolDown(coolDown);
            knockBack(player, enchantmentLevel);
        }
    }

    private static void knockBack(@NotNull Player player, int level) {
        double radius = UntouchableConfig.BASIC_RADIUS.get() + (level * UntouchableConfig.GAINED_RADIUS_PER_LEVEL.get());
        double force = UntouchableConfig.BASIC_FORCE.get() + (level * UntouchableConfig.GAINED_FORCE_PER_LEVEL.get());
        int slowDuration = UntouchableConfig.BASIC_SLOWNESS_DURATION.get() + (level * UntouchableConfig.GAINED_SLOWNESS_DURATION_PER_LEVEL.get());

        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius), e -> e instanceof Monster)
                .forEach(entity -> {
                    Vec3 dir = entity.position().subtract(player.position()).normalize();
                    entity.push(dir.x * force, 0.4, dir.z * force);
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slowDuration, 1));
                });
    }
}
