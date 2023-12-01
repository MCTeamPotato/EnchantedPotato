package com.teampotato.enchantedpotato.mixin.pressurized_collapse;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.IPlayer;
import com.teampotato.enchantedpotato.enchantment.bow.PressurizedCollapse;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class MixinAbstractArrow extends Entity {
    public MixinAbstractArrow(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
    @Unique
    private boolean ep$pressurizedCollapseTracked;

    @Unique
    private AbstractArrow ep$getThis() {
        return (AbstractArrow) (Object) this;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;tick()V", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        Level level = this.level();
        if (level.isClientSide || this.ep$pressurizedCollapseTracked) return;
        if (this.ep$getThis().getOwner() instanceof LivingEntity owner && EnchantedPotato.EnchantedUtils.hasPotatoEnchantmentEquipped(owner, EarlySetupInitializer.equipmentSlotConfig.pressurizedCollapse, PressurizedCollapse.PATH)) {
            if (owner instanceof Player player) {
                long additionalChargeTime = ((IPlayer)player).ep$getRealChargeTime() - ((long)BowItem.MAX_DRAW_DURATION);
                this.addTag(EarlySetupInitializer.MOD_ID + ".gravityArrow." + additionalChargeTime);
                ((IPlayer)player).ep$setRealChargeTime(0);
                ((IPlayer)player).ep$setStartUsingBowTime(0);
            } else {
                this.addTag(EarlySetupInitializer.MOD_ID + ".gravityArrow.0");
            }
        }
        this.ep$pressurizedCollapseTracked = true;
    }
}
