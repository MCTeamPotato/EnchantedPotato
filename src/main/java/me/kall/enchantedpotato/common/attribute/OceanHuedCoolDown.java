package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class OceanHuedCoolDown extends RangedAttribute {
    public OceanHuedCoolDown() {
        super("ocean_hued_cooldown", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
