package me.kall.enchantedpotato.common.api;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public interface ExtendedLivingEntity {
    @Nullable Player enchantedPotato$getLastHurtByPlayer();
}
