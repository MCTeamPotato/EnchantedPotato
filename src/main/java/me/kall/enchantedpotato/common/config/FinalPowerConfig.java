package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FinalPowerConfig {
    public static final ModConfigSpec INSTANCE;
    public static final ModConfigSpec.BooleanValue DISABLE_SHIELD;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("FinalDragon");
        DISABLE_SHIELD = builder.define("WhetherFinalPowerIgnoreShields", true);
        builder.pop();
        INSTANCE = builder.build();
    }
}
