package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BlackParadeConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.IntValue SPEED_DURATION, SPEED_AMPLIFIER;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("BlackParade");
        SPEED_DURATION = builder.defineInRange("SpeedDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        SPEED_AMPLIFIER = builder.defineInRange("SpeedAmplifier", 0, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
