package com.teampotato.enchantedpotato.mixin.poison_of_the_last_breath;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.weapon.PoisonOfTheLastBreath;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class SlimeMixin extends Mob {
    protected SlimeMixin(EntityType<? extends Mob> arg, Level arg2) {
        super(arg, arg2);
    }

    @Inject(method = "remove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Slime;isDeadOrDying()Z", shift = At.Shift.AFTER), cancellable = true)
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        if (EnchantedPotato.EnchantedUtils.hasPotatoEnchantmentEquipped(this.getLastHurtByMob(), EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath, PoisonOfTheLastBreath.PATH)) {
            ci.cancel();
            super.remove(reason);
        }
    }
}
