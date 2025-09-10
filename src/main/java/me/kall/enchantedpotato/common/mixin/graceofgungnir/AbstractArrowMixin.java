package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import me.kall.enchantedpotato.common.api.PotatoHitResult;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.GraceOfGungnir;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
public abstract class AbstractArrowMixin {
    @Unique
    private LivingEntity graceOfGungnir$target = null;

    @Shadow protected abstract void onHitEntity(@NotNull EntityHitResult result);


    @Inject(method = "shoot", at = @At("TAIL"))
    private void onShoot(CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.getOwner() instanceof Player) {
            Player player = (Player) arrow.getOwner();
            ItemStack bow = player.getMainHandItem();
            if (!(bow.getItem() instanceof BowItem)) bow = player.getOffhandItem();
            if (!(bow.getItem() instanceof BowItem)) return;
            int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GRACE_OF_GUNGNIR.get(), bow);
            if (level == 0) return;
            this.graceOfGungnir$target = GraceOfGungnir.findNearestLivingEntityOnPath(player);
        }
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    private void onHit(EntityHitResult result, CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.getOwner() instanceof Player && !(result instanceof PotatoHitResult)) {
            if (this.graceOfGungnir$target == null) return;
            this.onHitEntity(new PotatoHitResult(this.graceOfGungnir$target));
            ci.cancel();
        }
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void onHitBlock(BlockHitResult result, CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.getOwner() instanceof Player) {
            if (this.graceOfGungnir$target == null) return;
            this.onHitEntity(new PotatoHitResult(this.graceOfGungnir$target));
            ci.cancel();
        }
    }
}
