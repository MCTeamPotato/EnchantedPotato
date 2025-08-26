package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Mercy extends BaseEnchantment {
    public Mercy() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.MERCY.get();
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return false;
    }

    public static void onLivingDamage(@NotNull LivingDamageEvent event) {
        LivingEntity attacked = event.getEntity();
        if (!event.isCanceled() && attacked.level instanceof ServerLevel) {
            LivingEntity attacker = null;
            if (event.getSource().getEntity() instanceof LivingEntity sourceEntity) {
                attacker = sourceEntity;
            } else if (event.getSource().getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
                attacker = sourceDirectEntity;
            }
            if (attacker == null) return;
            Enchantment enchantment = ModEnchantments.MERCY.get();
            if (Math.max(attacker.getMainHandItem().getEnchantmentLevel(enchantment), attacker.getOffhandItem().getEnchantmentLevel(enchantment)) != 0) {
                float health = attacked.getHealth();
                float damage = event.getAmount();
                if (health < damage) damage = health - 1.0F;
                if (damage < 0.0F) damage = 0.0F;
                event.setAmount(damage);
            }
        }
    }
}
