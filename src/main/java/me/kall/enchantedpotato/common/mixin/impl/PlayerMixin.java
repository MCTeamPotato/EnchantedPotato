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

    @Override
    public boolean runLikeHell$isInCoolDown() {
        return this.runLikeHell$getCoolDown() != 0;
    }

    @Override
    public int runLikeHell$getCoolDown() {
        return (int) this.runLikeHell$getAttribute().getBaseValue();
    }

    @Override
    public void runLikeHell$setCoolDown(int coolDown) {
        this.runLikeHell$getAttribute().setBaseValue(coolDown);
    }

    @Override
    public boolean untouchable$isInCoolDown() {
        return this.untouchable$getCoolDown() != 0;
    }

    @Override
    public int untouchable$getCoolDown() {
        return (int) this.untouchable$getAttribute().getBaseValue();
    }

    @Override
    public void untouchable$setCoolDown(int coolDown) {
        this.untouchable$getAttribute().setBaseValue(coolDown);
    }


    @Inject(method = "tick", at = @At("RETURN"))
    private void runLikeHell$onTick(CallbackInfo ci) {
        if (this.level() instanceof ServerLevel) {
            if (this.isCreative()) {
                this.runLikeHell$setCoolDown(0);
                this.untouchable$setCoolDown(0);
            }
            if (this.runLikeHell$isInCoolDown()) this.runLikeHell$setCoolDown(this.runLikeHell$getCoolDown() - 1);
            if (this.untouchable$isInCoolDown()) this.untouchable$setCoolDown(this.untouchable$getCoolDown() - 1);
        }
    }

    @Unique
    public AttributeInstance runLikeHell$getAttribute() {
        return this.getAttribute(ModAttributes.RUN_LIKE_HELL_COOLDOWN.get());
    }

    @Unique
    public AttributeInstance untouchable$getAttribute() {
        return this.getAttribute(ModAttributes.UNTOUCHABLE_COOLDOWN.get());
    }
}
