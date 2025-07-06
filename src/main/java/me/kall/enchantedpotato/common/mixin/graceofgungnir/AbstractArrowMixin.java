package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import me.kall.enchantedpotato.common.api.PotatoHitResult;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.GraceOfGungnir;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    @Unique
    private LivingEntity graceOfGungnir$target = null;

    @Shadow protected abstract void onHitEntity(@NotNull EntityHitResult result);

    protected AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "shoot", at = @At("TAIL"))
    private void onShoot(CallbackInfo ci) {
        if (this.getOwner() instanceof Player player) {
            ItemStack bow = player.getMainHandItem();
            if (!Items.BOW.equals(bow.getItem())) bow = player.getOffhandItem();
            if (!Items.BOW.equals(bow.getItem())) return;
            int level = bow.getEnchantmentLevel(ModEnchantments.GRACE_OF_GUNGNIR.get());
            if (level == 0) return;
            this.graceOfGungnir$target = GraceOfGungnir.findNearestLivingEntityOnPath(player);
        }
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    private void onHit(EntityHitResult result, CallbackInfo ci) {
        if (this.getOwner() instanceof Player && !(result instanceof PotatoHitResult)) {
            if (this.graceOfGungnir$target == null) return;
            this.onHitEntity(new PotatoHitResult(this.graceOfGungnir$target));
            ci.cancel();
        }
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void onHitBlock(BlockHitResult result, CallbackInfo ci) {
        if (this.getOwner() instanceof Player) {
            if (this.graceOfGungnir$target == null) return;
            this.onHitEntity(new PotatoHitResult(this.graceOfGungnir$target));
            ci.cancel();
        }
    }
}
