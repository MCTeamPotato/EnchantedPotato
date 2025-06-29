package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RunLikeHellConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue LOW_HEALTH_PERCENT, INVISIBILITY_DURATION, SPEED_DURATION, COOL_DOWN, SPEED_AMPLIFIER;
    public static final ForgeConfigSpec.BooleanValue ALLOW_BETTER_INVISIBILITY;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("RunLikeHell");
        LOW_HEALTH_PERCENT = builder.defineInRange("LowHealthPercent(%)", 30, 0, 100);
        INVISIBILITY_DURATION = builder.defineInRange("InvisibilityDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        SPEED_DURATION = builder.defineInRange("SpeedDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        SPEED_AMPLIFIER = builder.defineInRange("SpeedAmplifier", 0, 0, Integer.MAX_VALUE);
        COOL_DOWN = builder.defineInRange("EnchantmentCoolDown(ticks)", 1200, 0, Integer.MAX_VALUE);
        ALLOW_BETTER_INVISIBILITY = builder.comment("Players with Invisibility will never be targeted by mobs").define("AllowBetterInvisibility", true);
        builder.pop();
        INSTANCE = builder.build();
    }

    public static float getPercent() {
        return LOW_HEALTH_PERCENT.get().floatValue() / 100.0F;
    }
}
