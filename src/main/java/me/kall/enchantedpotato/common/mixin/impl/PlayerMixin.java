package me.kall.enchantedpotato.common.mixin.impl;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.registry.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements ExtendedPlayer {

    @Shadow public abstract boolean isCreative();

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique private AttributeInstance runLikeHell$getAttribute() {return this.getAttribute(ModAttributes.RUN_LIKE_HELL_COOLDOWN.get());}
    @Unique private AttributeInstance untouchable$getAttribute() {return this.getAttribute(ModAttributes.UNTOUCHABLE_COOLDOWN.get());}
    @Unique private AttributeInstance oceanHued$getCoolDownAttribute() {return this.getAttribute(ModAttributes.OCEAN_HUED_COOLDOWN.get());}
    @Unique private AttributeInstance oceanHued$getCountingAttribute() {return this.getAttribute(ModAttributes.OCEAN_HUED_COUNTING.get());}
    @Unique private AttributeInstance oceanHued$getHealingAmountAttribute() {return this.getAttribute(ModAttributes.OCEAN_HUED_HEALING_AMOUNT.get());}
    @Unique private AttributeInstance spaceLeapfrog$getAttribute() {return this.getAttribute(ModAttributes.SPACE_LEAPFROG_COOLDOWN.get());}

    @Override public boolean runLikeHell$isInCoolDown() {return this.runLikeHell$getCoolDown() != 0;}
    @Override public int runLikeHell$getCoolDown() {return (int) this.runLikeHell$getAttribute().getBaseValue();}
    @Override public void runLikeHell$setCoolDown(int coolDown) {this.runLikeHell$getAttribute().setBaseValue(coolDown);}

    @Override public boolean untouchable$isInCoolDown() {return this.untouchable$getCoolDown() != 0;}
    @Override public int untouchable$getCoolDown() {return (int) this.untouchable$getAttribute().getBaseValue();}
    @Override public void untouchable$setCoolDown(int coolDown) {this.untouchable$getAttribute().setBaseValue(coolDown);}

    @Override public boolean spaceLeapfrog$isInCoolDown() {return this.spaceLeapfrog$getCoolDown() != 0;}
    @Override public int spaceLeapfrog$getCoolDown() {return (int) this.spaceLeapfrog$getAttribute().getBaseValue();}
    @Override public void spaceLeapfrog$setCoolDown(int coolDown) {this.spaceLeapfrog$getAttribute().setBaseValue(coolDown);}

    @Override public double oceanHued$getHealingAmount() {return this.oceanHued$getHealingAmountAttribute().getBaseValue();}
    @Override public void oceanHued$setHealingAmount(double healingAmount) {this.oceanHued$getHealingAmountAttribute().setBaseValue(healingAmount);}

    @Override public boolean oceanHued$isInCounting() {return this.oceanHued$getCountingAttribute().getBaseValue() != 0.00 && !this.oceanHued$isReady();}
    @Override public int oceanHued$getCountingTicks() {return (int) this.oceanHued$getCountingAttribute().getBaseValue();}
    @Override public void oceanHued$setCountingTicks(double countingTicks) {this.oceanHued$getCountingAttribute().setBaseValue(countingTicks);}
    @Override public boolean oceanHued$isReady() {return this.oceanHued$getCountingTicks() >= 300;}

    @Override public boolean oceanHued$isInCoolDown() {return this.oceanHued$getCoolDown() != 0;}
    @Override public int oceanHued$getCoolDown() {return (int) this.oceanHued$getCoolDownAttribute().getBaseValue();}
    @Override public void oceanHued$setCoolDown(int coolDown) {this.oceanHued$getCoolDownAttribute().setBaseValue(coolDown);}

    @Inject(method = "tick", at = @At("RETURN"))
    private void onTick(CallbackInfo ci) {
        if (this.level instanceof ServerLevel) {
            if (this.isCreative()) {
                this.runLikeHell$setCoolDown(0);
                this.untouchable$setCoolDown(0);
                this.oceanHued$setCoolDown(0);
                this.spaceLeapfrog$setCoolDown(0);
            }

            if (this.runLikeHell$isInCoolDown()) this.runLikeHell$setCoolDown(this.runLikeHell$getCoolDown() - 1);
            if (this.untouchable$isInCoolDown()) this.untouchable$setCoolDown(this.untouchable$getCoolDown() - 1);
            if (this.spaceLeapfrog$isInCoolDown()) this.spaceLeapfrog$setCoolDown(this.spaceLeapfrog$getCoolDown() - 1);
            if (this.oceanHued$isInCoolDown()) {
                this.oceanHued$setCoolDown(this.oceanHued$getCoolDown() - 1);
                return;
            }

            if (this.oceanHued$isInCounting()) this.oceanHued$setCountingTicks(this.oceanHued$getCountingTicks() + 1);
        }
    }
}
