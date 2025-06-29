package me.kall.enchantedpotato.common.mixin.graceofgungnir;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractArrow.class)
public interface ArrowInvoker {
    @Invoker("onHitEntity")
    void graceOfGungnir$onHitEntity(EntityHitResult result);
}
