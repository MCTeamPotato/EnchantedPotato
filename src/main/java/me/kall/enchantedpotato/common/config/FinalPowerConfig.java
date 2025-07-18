package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FinalPowerConfig {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.BooleanValue DISABLE_SHIELD;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("FinalDragon");
        DISABLE_SHIELD = builder.define("WhetherFinalPowerIgnoreShields", true);
        builder.pop();
        INSTANCE = builder.build();
    }
}
