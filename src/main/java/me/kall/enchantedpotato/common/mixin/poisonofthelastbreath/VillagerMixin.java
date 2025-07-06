package me.kall.enchantedpotato.common.mixin.poisonofthelastbreath;

import me.kall.enchantedpotato.common.enchantment.weapon.PoisonOfTheLastBreath;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(method = "tellWitnessesThatIWasMurdered", at = @At("HEAD"), cancellable = true)
    private void onTell(Entity murderer, CallbackInfo ci) {
        if (murderer instanceof Player player && PoisonOfTheLastBreath.has(player)) {
            ci.cancel();
        }
    }
}
