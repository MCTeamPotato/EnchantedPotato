package me.kall.enchantedpotato.common.mixin.runlikehell;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.kall.enchantedpotato.common.config.RunLikeHellConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Mob.class)
public abstract class MobMixin {
    @WrapMethod(method = "setTarget")
    private void setTarget(LivingEntity target, Operation<Void> original) {
        if (RunLikeHellConfig.ALLOW_BETTER_INVISIBILITY.get() && target instanceof ServerPlayer && target.hasEffect(MobEffects.INVISIBILITY)) return;
        original.call(target);
    }
}
