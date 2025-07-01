package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class PressurizedCollapseConfig {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.DoubleValue BASE_RANGE, MAX_EXTRA_RANGE, MAX_CHARGE_TIME;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("PressurizedCollapse");
        BASE_RANGE = builder.defineInRange("BaseRange", 3.0, 0, Double.MAX_VALUE);
        MAX_EXTRA_RANGE = builder.defineInRange("MaxRangeBonus", 7.0D, 0, Double.MAX_VALUE);
        MAX_CHARGE_TIME = builder.defineInRange("MaxValidChargeTicks", 40, 0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
