package me.kall.enchantedpotato.common.config;

import com.google.common.base.Predicates;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class UniteStonesOfAllConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> UNITED_STONES, QUARTZ_STONES;
    public static final ModConfigSpec.DoubleValue QUARTZ_DROP_CHANCE;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("UniteStonesOfAll");
        UNITED_STONES = builder.defineList("UnitedStones", List.of("minecraft:andesite", "minecraft:granite", "minecraft:deepslate", "minecraft:tuff", "minecraft:calcite"), () -> "", Predicates.alwaysTrue());
        QUARTZ_STONES = builder.defineList("QuartzStones", List.of("minecraft:granite", "minecraft:andesite"), () -> "", Predicates.alwaysTrue());
        QUARTZ_DROP_CHANCE = builder.defineInRange("QuartzDropChance", 0.15D, 0.00, 1.00);
        builder.pop();
        INSTANCE = builder.build();
    }
}
