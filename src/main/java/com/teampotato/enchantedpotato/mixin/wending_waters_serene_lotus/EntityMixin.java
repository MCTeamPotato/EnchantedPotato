package com.teampotato.enchantedpotato.mixin.wending_waters_serene_lotus;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.enchantment.armor.feet.WendingWatersSereneLotus;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Unique
    private boolean ep$cantPush;

    @Unique
    private Entity ep$getThis() {
        return (Entity)(Object)this;
    }

    @Inject(method = "updateFluidHeightAndDoFluidPushing()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPushedByFluid()Z", shift = At.Shift.AFTER))
    private void onUpdateFluidPushingPre(CallbackInfo ci) {
        if (ep$getThis() instanceof LivingEntity entity) this.ep$cantPush = EnchantedPotato.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.wendingWatersSereneLotus, WendingWatersSereneLotus.PATH);
    }

    @Redirect(method = "updateFluidHeightAndDoFluidPushing()V", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isPushedByFluid(Lnet/neoforged/neoforge/fluids/FluidType;)Z"))
    private boolean onCheckCanPush(@NotNull Entity instance, FluidType fluidType) {
        return instance.isPushedByFluid(fluidType) && !this.ep$cantPush;
    }

    @Inject(method = "updateFluidHeightAndDoFluidPushing()V", remap = false, at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2ObjectMap;forEach(Ljava/util/function/BiConsumer;)V", remap = false, shift = At.Shift.BEFORE))
    private void onUpdateFluidPushingPost(CallbackInfo ci) {
        if (ep$getThis() instanceof LivingEntity) this.ep$cantPush = false;
    }
}
