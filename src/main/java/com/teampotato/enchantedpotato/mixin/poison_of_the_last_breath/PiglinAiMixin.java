package com.teampotato.enchantedpotato.mixin.poison_of_the_last_breath;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.PoisonOfTheLastBreath;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @Inject(method = "maybeRetaliate", at = @At("HEAD"), cancellable = true)
    private static void onAnger(AbstractPiglin piglin, LivingEntity target, CallbackInfo ci) {
        if (Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath, PoisonOfTheLastBreath.PATH)) {
            ci.cancel();
        }
    }
}