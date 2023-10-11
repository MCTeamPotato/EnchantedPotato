package com.teampotato.enchantedpotato.mixin.guren_no_yumiya;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.IPlayer;
import com.teampotato.enchantedpotato.enchantment.bow.GurenNoYumiya;
import com.teampotato.enchantedpotato.util.Utils;
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
    private AbstractArrow ep$getThis() {
        return (AbstractArrow) (Object) this;
    }

    @Unique
    private boolean ep$gurenNoYumiyaTracked;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;tick()V", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        Level level = this.level();
        if (level.isClientSide || this.ep$gurenNoYumiyaTracked) return;
        if (ep$getThis().getOwner() instanceof LivingEntity owner && Utils.hasPotatoEnchantmentEquipped(owner, EarlySetupInitializer.equipmentSlotConfig.gurenNoYumiya, GurenNoYumiya.PATH) && owner instanceof Player player && (int)((IPlayer)player).ep$getRealChargeTime() - BowItem.MAX_DRAW_DURATION >= 20) {
            this.addTag(EarlySetupInitializer.MOD_ID + ".fireArrow");
            ((IPlayer)player).ep$setRealChargeTime(0);
            ((IPlayer)player).ep$setStartUsingBowTime(0);
        }
        this.ep$gurenNoYumiyaTracked = true;
    }
}
