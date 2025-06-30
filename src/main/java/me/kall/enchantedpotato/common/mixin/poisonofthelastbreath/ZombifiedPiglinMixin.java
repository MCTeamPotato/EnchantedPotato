package me.kall.enchantedpotato.common.mixin.poisonofthelastbreath;

import me.kall.enchantedpotato.common.enchantment.PoisonOfTheLastBreath;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombifiedPiglin.class)
public abstract class ZombifiedPiglinMixin extends Zombie {
    public ZombifiedPiglinMixin(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "alertOthers", at = @At("HEAD"), cancellable = true)
    private void onAlertOthers(CallbackInfo ci) {
        if (this.getTarget() instanceof Player player && PoisonOfTheLastBreath.has(player)) {
            ci.cancel();
        }
    }
}
