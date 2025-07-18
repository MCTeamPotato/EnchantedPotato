package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MendingMirrorConfig {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.DoubleValue REMAINING_DURABILITY;
    public static final ForgeConfigSpec.BooleanValue PLAY_SOUND;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("MendingMirror");
        REMAINING_DURABILITY = builder.defineInRange("RemainingDurability", 0.30, 0.01, 1.00);
        PLAY_SOUND = builder.define("PlaySoundOnRecovering", true);
        builder.pop();
        INSTANCE = builder.build();
    }
}
