package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BlackParadeConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue SPEED_DURATION, SPEED_AMPLIFIER;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("BlackParade");
        SPEED_DURATION = builder.defineInRange("SpeedDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        SPEED_AMPLIFIER = builder.defineInRange("SpeedAmplifier", 0, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
