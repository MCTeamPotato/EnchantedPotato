package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import com.llamalad7.mixinextras.sugar.Local;
import me.kall.enchantedpotato.common.enchantment.GraceOfGungnir;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ProjectileWeaponItem {
    public BowItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void onShoot(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged, CallbackInfo ci, @Local(ordinal = 0) Player player, @Local(ordinal = 0) AbstractArrow arrow) {
        if (stack.getEnchantmentLevel(ModEnchantments.GRACE_OF_GUNGNIR.get()) != 0) {
            LivingEntity entity = GraceOfGungnir.findNearestLivingEntityOnPath(player);
            if (entity == null) return;
            ((ArrowInvoker)arrow).graceOfGungnir$onHitEntity(new EntityHitResult(entity));
            arrow.discard();
        }
    }
}
