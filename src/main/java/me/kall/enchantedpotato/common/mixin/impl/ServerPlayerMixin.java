package me.kall.enchantedpotato.common.mixin.impl;

import com.mojang.authlib.GameProfile;
import me.kall.enchantedpotato.common.api.ExtendedServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements ExtendedServerPlayer {
    @Unique
    private int runLikeHell$CoolDown, untouchable$CoolDown;

    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Override
    public boolean runLikeHell$isInCoolDown() {
        return this.runLikeHell$CoolDown != 0;
    }

    @Override
    public void runLikeHell$setCoolDown(int coolDown) {
        this.runLikeHell$CoolDown = coolDown;
    }

    @Override
    public boolean untouchable$isInCoolDown() {
        return this.untouchable$CoolDown != 0;
    }

    @Override
    public void untouchable$setCoolDown(int coolDown) {
        this.untouchable$CoolDown = coolDown;
    }


    @Inject(method = "tick", at = @At("RETURN"))
    private void runLikeHell$onTick(CallbackInfo ci) {
        if (this.runLikeHell$CoolDown != 0) this.runLikeHell$CoolDown--;
        if (this.untouchable$CoolDown != 0) this.untouchable$CoolDown--;
    }

    @Inject(method = "disconnect", at = @At("RETURN"))
    private void runLikeHell$onDisconnect(CallbackInfo ci) {
        this.runLikeHell$CoolDown = 0;
        this.untouchable$CoolDown = 0;
    }
}
