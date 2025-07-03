package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class OceanHuedHealingAmount extends RangedAttribute {
    public OceanHuedHealingAmount() {
        super("ocean_hued_healing_amount", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }
}
