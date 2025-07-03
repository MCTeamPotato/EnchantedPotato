package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class OceanHuedCounting extends RangedAttribute {
    public OceanHuedCounting() {
        super("ocean_hued_counting", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
