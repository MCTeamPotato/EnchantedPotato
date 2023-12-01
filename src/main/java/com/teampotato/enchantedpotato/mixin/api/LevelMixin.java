package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.api.ILevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("resource")
@Mixin(Level.class)
public abstract class LevelMixin implements ILevel {
    @Override
    public List<NeutralMob> ep$getNeutralMobs(AABB area) {
        if (ep$getThis() instanceof ServerLevel serverLevel) {
            return EnchantedPotato.EnchantedUtils.getNeutralMobs(area, serverLevel);
        } else {
            return Collections.emptyList();
        }
    }

    @Unique
    private Level ep$getThis() {
        return (Level) (Object) this;
    }
}
