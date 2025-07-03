package me.kall.enchantedpotato.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ArmorBreakingDuration extends RangedAttribute {
    public ArmorBreakingDuration() {
        super("armor_breaking_duration", 0.00, 0.00, Double.MAX_VALUE);
    }
}
