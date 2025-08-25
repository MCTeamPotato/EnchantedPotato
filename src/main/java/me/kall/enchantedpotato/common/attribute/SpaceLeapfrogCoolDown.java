package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class SpaceLeapfrogCoolDown extends RangedAttribute {
    public SpaceLeapfrogCoolDown() {
        super("space_leapfrog_cooldown", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
