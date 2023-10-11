package com.teampotato.enchantedpotato.mixin.run_like_hell;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    @Shadow @Nullable
    private LivingEntity target;
    
    @Shadow public abstract void setTarget(@Nullable LivingEntity arg);
    protected MobMixin(EntityType<? extends LivingEntity> arg, Level arg2) {
        super(arg, arg2);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        if (this.target != null && this.target.getTags().contains(EarlySetupInitializer.MOD_ID + ".blinder")) {
            this.setTarget(null);
            if (this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isEmpty()) return;
            this.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
        }
    }
}
