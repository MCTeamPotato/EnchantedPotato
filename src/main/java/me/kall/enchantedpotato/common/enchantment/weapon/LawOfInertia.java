package me.kall.enchantedpotato.common.enchantment.weapon;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

public class LawOfInertia extends Enchantment {
    public static final String MARK = "InertiaKnockbackDamage";
    public LawOfInertia() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || item instanceof SwordItem || item instanceof BowItem || item instanceof TridentItem;
    }

    public static void onLivingTick(@NotNull LivingEvent.LivingTickEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        if (!(entity.level() instanceof ServerLevel)) return;
        float damage = entity.getPersistentData().getFloat(MARK);
        if (damage == 0.0F) return;

        Vec3 motion = entity.getDeltaMovement();
        if (Math.sqrt(motion.x * motion.x + motion.z * motion.z) < 0.01) {
            entity.getPersistentData().remove(MARK);
        }
    }
}
