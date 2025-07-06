package me.kall.enchantedpotato.common.config;

import com.google.common.base.Predicates;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class UniteStonesOfAllConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> UNITED_STONES, QUARTZ_STONES;
    public static final ForgeConfigSpec.DoubleValue QUARTZ_DROP_CHANCE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("UniteStonesOfAll");
        UNITED_STONES = builder.defineList("UnitedStones", List.of("minecraft:andesite", "minecraft:granite", "minecraft:deepslate", "minecraft:tuff", "minecraft:calcite"), Predicates.alwaysTrue());
        QUARTZ_STONES = builder.defineList("QuartzStones", List.of("minecraft:granite", "minecraft:andesite"), Predicates.alwaysTrue());
        QUARTZ_DROP_CHANCE = builder.defineInRange("QuartzDropChance", 0.15D, 0.00, 1.00);
        builder.pop();
        INSTANCE = builder.build();
    }
}
