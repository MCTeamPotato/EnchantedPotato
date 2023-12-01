package com.teampotato.enchantedpotato.mixin.run_like_hell;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntity {
    @Shadow public abstract @NotNull Iterable<ItemStack> getArmorSlots();

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        if (this.removeTag(EarlySetupInitializer.MOD_ID + ".tickCountTrigger")) this.ep$setShouldTrackRunLikeHell(true);
        if (this.ep$shouldTrackRunLikeHell()) {
            this.ep$setRunLikeHellTickCount(this.ep$getRunLikeHellTickCount() + 1);
            if (this.ep$getRunLikeHellTickCount() == EnchantedPotato.EnchantedConfig.RUN_LIKE_HELL_INVISIBILITY_DURATION.get()) {
                this.removeTag(EarlySetupInitializer.MOD_ID + ".blinder");
                this.ep$setRunLikeHellTickCount(0);
                this.ep$setShouldTrackRunLikeHell(false);
            }
        }
    }

    public LivingEntityMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }
}
