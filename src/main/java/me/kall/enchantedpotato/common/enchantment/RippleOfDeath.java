package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.common.config.RippleOfDeathConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class RippleOfDeath extends Enchantment {
    public RippleOfDeath() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || item instanceof SwordItem || item instanceof BowItem || item instanceof TridentItem;
    }

    public static void onLivingDeath(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel level) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
            if (stack.getEnchantmentLevel(ModEnchantments.RIPPLE_OF_DEATH.get()) == 0) stack = player.getItemBySlot(EquipmentSlot.OFFHAND);
            int enchantmentLevel = stack.getEnchantmentLevel(ModEnchantments.RIPPLE_OF_DEATH.get());
            if (enchantmentLevel == 0) return;
            int baseRadius = RippleOfDeathConfig.BASE_RADIUS.get();
            int gainedRadiusPerLevel = RippleOfDeathConfig.GAINED_RADIUS_PER_LEVEL.get();
            int radius = baseRadius + gainedRadiusPerLevel * (enchantmentLevel - 1);
            AABB box = event.getEntity().getBoundingBox().inflate(radius);
            Predicate<LivingEntity> filter = entity -> entity.isAlive() && entity instanceof Mob && !entity.getUUID().equals(player.getUUID());
            float basicDamagePercent = RippleOfDeathConfig.BASIC_DAMAGE_PERCENT.get().floatValue();
            float gainedDamagePercentPerLevel = RippleOfDeathConfig.GAINED_DAMAGE_PERCENT_PER_LEVEL.get().floatValue();
            float damagePercent = basicDamagePercent + gainedDamagePercentPerLevel * (enchantmentLevel - 1);
            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, box, filter)) {
                entity.hurt(player.damageSources().indirectMagic(player, player), entity.getMaxHealth() * damagePercent);
            }
        }
    }
}
