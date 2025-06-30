package me.kall.enchantedpotato.common.mixin.poisonofthelastbreath;

import me.kall.enchantedpotato.common.enchantment.PoisonOfTheLastBreath;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HurtByTargetGoal.class)
public abstract class HurtByTargetGoalMixin extends TargetGoal {
    public HurtByTargetGoalMixin(Mob mob, boolean mustSee) {
        super(mob, mustSee);
    }

    @Inject(method = "alertOthers", at = @At("HEAD"), cancellable = true)
    private void onAlertOthers(CallbackInfo ci) {
        if (this.mob.getLastHurtByMob() instanceof Player player && PoisonOfTheLastBreath.has(player)) ci.cancel();
    }
}
