package me.kall.enchantedpotato.common.mixin.wondereggpriority;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThrownEgg.class)
public abstract class ThrownEggMixin extends ThrowableItemProjectile {
    public ThrownEggMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyConstant(method = "onHitEntity", constant = @Constant(floatValue = 0.0F))
    private float onHit(float constant) {
        if (this.getOwner() instanceof Player player) {
            int level = player.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(ModEnchantments.WONDER_EGG_PRIORITY.get());
            if (level > 0) {
                constant = (float) level * 0.5F;
            }
        }
        return constant;
    }
}
