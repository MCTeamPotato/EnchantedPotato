package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import me.kall.enchantedpotato.common.enchantment.GraceOfGungnir;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    @Shadow protected abstract void onHitEntity(@NotNull EntityHitResult result);

    protected AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        if (this.getOwner() instanceof Player player) {
            ItemStack bow = player.getMainHandItem();
            if (!Items.BOW.equals(bow.getItem())) bow = player.getOffhandItem();
            int level = bow.getEnchantmentLevel(ModEnchantments.GUREN_NO_YUMIYA.get());
            if (level == 0) return;
            LivingEntity entity = GraceOfGungnir.findNearestLivingEntityOnPath(player);
            if (entity == null) return;
            this.onHitEntity(new EntityHitResult(entity));
            this.addTag("discard");
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (this.removeTag("discard")) this.discard();
    }
}
