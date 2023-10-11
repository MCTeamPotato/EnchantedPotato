package com.teampotato.enchantedpotato.mixin.wending_waters_serene_lotus;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.armor.feet.WendingWatersSereneLotus;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Unique
    private boolean ep$cantPush;

    @Unique
    private Entity ep$getThis() {
        return (Entity)(Object)this;
    }

    @Inject(method = "updateFluidHeightAndDoFluidPushing(Ljava/util/function/Predicate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPushedByFluid()Z", shift = At.Shift.AFTER))
    private void onUpdateFluidPushingPre(Predicate<FluidState> shouldUpdate, CallbackInfo ci) {
        if (ep$getThis() instanceof LivingEntity entity) this.ep$cantPush = Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.wendingWatersSereneLotus, WendingWatersSereneLotus.PATH);
    }

    @Redirect(method = "updateFluidHeightAndDoFluidPushing(Ljava/util/function/Predicate;)V", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPushedByFluid(Lnet/minecraftforge/fluids/FluidType;)Z"))
    private boolean onCheckCanPush(@NotNull Entity instance, FluidType fluidType) {
        return instance.isPushedByFluid(fluidType) && !this.ep$cantPush;
    }

    @Inject(method = "updateFluidHeightAndDoFluidPushing(Ljava/util/function/Predicate;)V", remap = false, at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2ObjectMap;forEach(Ljava/util/function/BiConsumer;)V", remap = false, shift = At.Shift.BEFORE))
    private void onUpdateFluidPushingPost(Predicate<FluidState> shouldUpdate, CallbackInfo ci) {
        if (ep$getThis() instanceof LivingEntity) this.ep$cantPush = false;
    }
}
