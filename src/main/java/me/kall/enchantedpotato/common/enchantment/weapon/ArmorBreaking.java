package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class ArmorBreaking extends BaseEnchantment {
    public static final String ARMOR_BREAKING_KEY = "ArmorBreakingLevel";

    public ArmorBreaking() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.ARMOR_BREAKING.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return BaseEnchantment.canUseAsWeapon(stack.getItem()) && super.canEnchant(stack);
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level instanceof ServerLevel serverLevel) {
            Enchantment enchantment = ModEnchantments.ARMOR_BREAKING.get();
            int level = Math.max(player.getMainHandItem().getEnchantmentLevel(enchantment), player.getOffhandItem().getEnchantmentLevel(enchantment));
            if (level == 0) return;
            LivingEntity entity = event.getEntity();
            entity.getPersistentData().putInt(ARMOR_BREAKING_KEY, level);
            ((ExtendedLivingEntity)entity).armorBreaking$getAttribute().setBaseValue(ArmorBreakingConfig.BASE_DURATION.get().doubleValue() + ArmorBreakingConfig.GAINED_DURATION_PER_LEVEL.get().doubleValue() * (double) (level - 1));
        }
    }
}
