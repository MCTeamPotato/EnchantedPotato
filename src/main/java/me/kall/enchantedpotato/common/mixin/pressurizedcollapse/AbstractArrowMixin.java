package me.kall.enchantedpotato.common.mixin.pressurizedcollapse;

import me.kall.enchantedpotato.common.enchantment.PressurizedCollapse;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    @Unique private float pressurizedCollapse$chargeTime;
    @Unique private int pressurizedCollapse$level;

    protected AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = {"onHitEntity", "onHitBlock"}, at = @At("HEAD"))
    private void onHit(CallbackInfo ci) {
        if (this.level() instanceof ServerLevel && this.getOwner() instanceof Player && this.pressurizedCollapse$chargeTime != 0) {
            double baseRange = 3.0D;
            double maxExtraRange = 7.0D;
            float maxChargeTime = 40F;

            float chargeBonus = Math.min((this.pressurizedCollapse$chargeTime - 20.0F) / (maxChargeTime - 20.0F), 1.0F);
            double range = baseRange + (maxExtraRange * chargeBonus);
            PressurizedCollapse.apply(this.position(), range, this.level(), this.pressurizedCollapse$level);
        }
    }

    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        if (this.getOwner() instanceof Player player) {
            ItemStack bow = player.getMainHandItem();
            if (!Items.BOW.equals(bow.getItem())) bow = player.getOffhandItem();

            this.pressurizedCollapse$level = bow.getEnchantmentLevel(ModEnchantments.PRESSURIZED_COLLAPSE.get());
            if (this.pressurizedCollapse$level != 0) {
                this.pressurizedCollapse$chargeTime = (float) player.getTicksUsingItem();
            }
        }
    }
}
