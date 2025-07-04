package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import me.kall.enchantedpotato.common.api.ExtendedAbstractArrow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ServerLevel.class, priority = 1200)
public abstract class ServerLevelMixin {
    @Inject(method = "addEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;onAddedToWorld()V"))
    private void canRemove(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof AbstractArrow arrow) {
            ((ExtendedAbstractArrow)arrow).graceOfGungnir$setCanRemove(true);
        }
    }
}
