package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.RippleOfDeathConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class RippleOfDeath extends BaseEnchantment {
    public RippleOfDeath() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.RIPPLE_OF_DEATH.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return BaseEnchantment.canUseAsWeapon(stack) && super.canEnchant(stack);
    }

    public static void onLivingDeath(@NotNull LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getSource().getEntity();
            if (!event.isCanceled() && player.level instanceof ServerLevel) {
                int enchantmentLevel = Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.RIPPLE_OF_DEATH.get(), player.getOffhandItem()), EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.RIPPLE_OF_DEATH.get(), player.getMainHandItem()));
                if (enchantmentLevel == 0) return;
                int baseRadius = RippleOfDeathConfig.BASE_RADIUS.get();
                int gainedRadiusPerLevel = RippleOfDeathConfig.GAINED_RADIUS_PER_LEVEL.get();
                int radius = baseRadius + gainedRadiusPerLevel * (enchantmentLevel - 1);
                AABB box = event.getEntity().getBoundingBox().inflate(radius);
                Predicate<LivingEntity> filter = entity -> entity.isAlive() && entity instanceof Mob && !entity.getUUID().equals(player.getUUID());
                float basicDamagePercent = RippleOfDeathConfig.BASIC_DAMAGE_PERCENT.get().floatValue();
                float gainedDamagePercentPerLevel = RippleOfDeathConfig.GAINED_DAMAGE_PERCENT_PER_LEVEL.get().floatValue();
                float damagePercent = basicDamagePercent + gainedDamagePercentPerLevel * (enchantmentLevel - 1);
                for (LivingEntity entity : player.level.getEntitiesOfClass(LivingEntity.class, box, filter)) {
                    entity.hurt(DamageSource.indirectMagic(player, player), entity.getMaxHealth() * damagePercent);
                }
            }
        }
    }
}
