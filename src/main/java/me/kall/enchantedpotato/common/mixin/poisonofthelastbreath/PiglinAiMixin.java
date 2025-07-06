package me.kall.enchantedpotato.common.mixin.poisonofthelastbreath;

import me.kall.enchantedpotato.common.enchantment.weapon.PoisonOfTheLastBreath;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @Inject(method = "broadcastAngerTarget", at = @At("HEAD"), cancellable = true)
    private static void onAnger(AbstractPiglin piglin, LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player player && PoisonOfTheLastBreath.has(player)) ci.cancel();
    }
}
