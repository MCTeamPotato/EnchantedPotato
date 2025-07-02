package me.kall.enchantedpotato.common.api;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public interface ExtendedLivingEntity {
    @Nullable Player enchantedPotato$getLastHurtByPlayer();

    AttributeInstance armorBreaking$getAttribute();

    int armorBreaking$getInterval();
    void armorBreaking$bumpInterval();
    void armorBreaking$clearInterval();

    int natureBlessing$getInterval();
    void natureBlessing$setInterval(int interval);
}
