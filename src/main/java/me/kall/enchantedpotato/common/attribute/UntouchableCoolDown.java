package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class UntouchableCoolDown extends RangedAttribute {
    public UntouchableCoolDown() {
        super("untouchable_cooldown", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
