package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class SacredRiftwind extends BaseEnchantment {
    public SacredRiftwind() {
        super(Rarity.UNCOMMON, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntityLiving();
        if (entity.level.isClientSide()) return;
        if (event.getSource().getEntity() instanceof LivingEntity) {
            LivingEntity source = (LivingEntity) event.getSource().getEntity();
            Enchantment enchantment = ModEnchantments.SACRED_RIFTWIND.get();
            int level = Math.max(EnchantmentHelper.getItemEnchantmentLevel(enchantment, source.getOffhandItem()), EnchantmentHelper.getItemEnchantmentLevel(enchantment, source.getMainHandItem()));
            if (level != 0) {
                float healthPercent = 1.0F - (source.getHealth() / source.getMaxHealth());
                float bonus = (float) (level * 0.1D * healthPercent);
                event.setAmount(event.getAmount() * (1.0F + bonus));
            }
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.SACRED_RIFTWIND.get();
    }
}