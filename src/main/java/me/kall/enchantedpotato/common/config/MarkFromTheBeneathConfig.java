package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MarkFromTheBeneathConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.DoubleValue VALID_BASE_MAX_HEIGHT, VALID_BASE_MIN_HEIGHT, MAX_SPEED_BONUS, MIN_SPEED_BONUS, GAINED_BASE_HEIGHT_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("MarkFromTheBeneath");
        VALID_BASE_MAX_HEIGHT = builder.defineInRange("ValidBaseMaxHeight", 50, -64, Double.MAX_VALUE);
        VALID_BASE_MIN_HEIGHT = builder.defineInRange("ValidBaseMinHeight", -64, -64, Double.MAX_VALUE);
        MAX_SPEED_BONUS = builder.defineInRange("MaxSpeedBonus", 1.5D, 0D, Double.MAX_VALUE);
        MIN_SPEED_BONUS = builder.defineInRange("MinSpeedBonus", 0.2D, 0D, Double.MAX_VALUE);
        GAINED_BASE_HEIGHT_PER_LEVEL = builder.defineInRange("GainedBaseHeightAdditionPerLevel", 10D, 0D, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
