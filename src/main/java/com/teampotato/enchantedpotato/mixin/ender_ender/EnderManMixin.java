package com.teampotato.enchantedpotato.mixin.ender_ender;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.EnderTeleporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderMan.class)
public abstract class EnderManMixin extends Monster implements EnderTeleporter {
    @Unique
    private int ep$cantTpTicks;

    protected EnderManMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/EnderMan;updatePersistentAnger(Lnet/minecraft/server/level/ServerLevel;Z)V", shift = At.Shift.BEFORE))
    private void onTick(CallbackInfo ci) {
        if (this.ep$cantTpTicks == 0) {
            String endTag = "";
            for (String tag : this.getTags()) {
                if (tag.startsWith(EarlySetupInitializer.MOD_ID + ".end.")) {
                    endTag = tag;
                    this.getTags().remove(tag);
                    break;
                }
            }
            if (!endTag.isEmpty()) this.ep$cantTpTicks = Integer.parseInt(endTag.split("\\.")[2]) * EnchantedPotato.EnchantedConfig.ENDER_ENDER_TELEPORTATION_LIMIT_TICKS_PER_LEVEL.get();
        } else {
            this.ep$cantTpTicks = this.ep$cantTpTicks - 1;
        }
    }

    @Override
    public boolean ep$cantEnderTp() {
        return this.ep$cantTpTicks != 0;
    }
}
