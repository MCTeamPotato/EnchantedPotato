package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

public class LawOfInertia extends BaseEnchantment {
    public static final String MARK = "InertiaKnockbackDamage";

    public LawOfInertia() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.LAW_OF_INERTIA.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return BaseEnchantment.canUseAsWeapon(stack) && super.canEnchant(stack);
    }

    public static void onLivingTick(@NotNull LivingEvent.LivingTickEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        if (!(entity.level instanceof ServerLevel)) return;
        float damage = entity.getPersistentData().getFloat(MARK);
        if (damage == 0.0F) return;

        Vec3 motion = entity.getDeltaMovement();
        if (Math.sqrt(motion.x * motion.x + motion.z * motion.z) < 0.01) {
            entity.getPersistentData().remove(MARK);
        }
    }
}
