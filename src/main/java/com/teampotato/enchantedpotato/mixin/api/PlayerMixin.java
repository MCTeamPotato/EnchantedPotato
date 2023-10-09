package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.IPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerMixin implements IPlayer {

    @Unique
    private long ep$startUsingBowTime;

    @Unique
    private long ep$chargeTime;

    @Override
    public void ep$setStartUsingBowTime(long startUsingBowTime) {
        this.ep$startUsingBowTime = startUsingBowTime;
    }

    @Override
    public long ep$getStartUsingBowTime() {
        return this.ep$startUsingBowTime;
    }

    @Override
    public void ep$setRealChargeTime(long chargeTime) {
        this.ep$chargeTime = chargeTime;
    }

    @Override
    public long ep$getRealChargeTime() {
        return this.ep$chargeTime;
    }
}
