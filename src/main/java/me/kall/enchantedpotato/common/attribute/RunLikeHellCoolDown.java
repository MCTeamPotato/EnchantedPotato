package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class RunLikeHellCoolDown extends RangedAttribute {
    public RunLikeHellCoolDown() {
        super("run_like_hell_cooldown", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
