package com.teampotato.enchantedpotato.config.toml;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
//TODO: transfer to json and make it reloadable
public class DetailsConfig {
    public static final ForgeConfigSpec DETAILS_CONFIG;


    public static final ForgeConfigSpec.IntValue RUN_LIKE_HELL_INVISIBILITY_DURATION;
    public static final ForgeConfigSpec.IntValue RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION;
    public static final ForgeConfigSpec.IntValue RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER;
    public static final ForgeConfigSpec.ConfigValue<Double> RUN_LIKE_HELL_VALID_MIN_HEALTH;

    public static final ForgeConfigSpec.IntValue BLACK_PARADE_MOVEMENT_SPEED_DURATION;
    public static final ForgeConfigSpec.IntValue BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER;

    public static final ForgeConfigSpec.ConfigValue<Float> THIS_IS_LEOPARD_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue THIS_IS_LEOPARD_TARGET_MISS_CHANCE;
    public static final ForgeConfigSpec.DoubleValue THIS_IS_LEOPARD_VALID_HEIGHT;

    public static final ForgeConfigSpec.ConfigValue<? extends String> DYING_OF_LIGHT_ENTITIES_DETECTION_RANGE;
    public static final ForgeConfigSpec.IntValue DYING_OF_LIGHT_DETECTION_TICK_INTERVAL;
    public static final ForgeConfigSpec.DoubleValue DYING_OF_LIGHT_VILLAGER_HURT_DAMAGE;

    public static final ForgeConfigSpec.LongValue PRESSURIZED_COLLAPSE_MAX_RANGE_ADDITION;
    public static final ForgeConfigSpec.ConfigValue<? extends String> PRESSURIZED_COLLAPSE_BASIC_RANGE;

    public static final ForgeConfigSpec.DoubleValue ERROR_SPORE_DAMAGE_REDUCTION_PER_LEVEL;
    public static final ForgeConfigSpec.IntValue ERROR_SPORE_CREEPER_SPAWN_CHECKER;

    public static final ForgeConfigSpec.ConfigValue<? extends String> UNTOUCHABLE_DETECTION_RANGE;
    public static final ForgeConfigSpec.ConfigValue<Float> UNTOUCHABLE_KNOCK_BACK_STRENGTH;
    public static final ForgeConfigSpec.IntValue UNTOUCHABLE_COOL_DOWN;

    public static final ForgeConfigSpec.ConfigValue<? extends String> GUREN_NO_YUMIYA_FIRE_ARROW_RADIUS;
    public static final ForgeConfigSpec.IntValue GUREN_NO_YUMIYA_FIRE_DURATION_TICKS;

    public static final ForgeConfigSpec.ConfigValue<? extends String> BONE_SUCKALAKA_DETECTION_RADIUS;

    public static final ForgeConfigSpec.IntValue LORA_TRAINER_KILL_COUNTS_REQUIREMENT;
    public static final ForgeConfigSpec.DoubleValue LORA_TRAINER_DAMAGE_BONUS;

    public static final ForgeConfigSpec.ConfigValue<? extends String> RIPPLE_OF_DEATH_DETECTION_RADIUS;
    public static final ForgeConfigSpec.DoubleValue RIPPLE_OF_DEATH_EXTRA_DAMAGE;

    public static final ForgeConfigSpec.DoubleValue HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE;
    public static final ForgeConfigSpec.DoubleValue HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE;
    public static final ForgeConfigSpec.IntValue HUA_JIN_STRENGTH_EFFECT_DURATION;
    public static final ForgeConfigSpec.IntValue HUA_JIN_STRENGTH_EFFECT_AMPLIFIER;

    public static final ForgeConfigSpec.DoubleValue WONDER_EGG_PRIORITY_EGG_DAMAGE_VALUE;

    public static final ForgeConfigSpec.IntValue MARK_FROM_THE_BENEATH_VALID_Y;
    public static final ForgeConfigSpec.DoubleValue MARK_FROM_THE_BENEATH_DIG_SPEED_MULTIPLIER;

    public static final ForgeConfigSpec.DoubleValue ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue ARMOR_BREAKING_ARMOR_TOUGHNESS_MULTIPLIER;
    public static final ForgeConfigSpec.IntValue ARMOR_BREAKING_MULTIPLIER_DURATION_TICKS;
    public static final ForgeConfigSpec.IntValue ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION;

    public static final ForgeConfigSpec.IntValue BLESSING_OF_THE_NATURE_HEALING_INTERVAL_TICK;
    public static final ForgeConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_HEALING_AMOUNT;
    public static final ForgeConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MAX_KNOCK_BACK_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_STRENGTH;
    public static final ForgeConfigSpec.ConfigValue<? extends String> BLESSING_OF_THE_NATURE_DETECTION_RANGE;

    public static final ForgeConfigSpec.DoubleValue FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET;
    public static final ForgeConfigSpec.DoubleValue FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER;
    public static final ForgeConfigSpec.DoubleValue FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING;
    public static final ForgeConfigSpec.DoubleValue FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING;
    public static final ForgeConfigSpec.BooleanValue FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING;
    public static final ForgeConfigSpec.IntValue FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER;
    public static final ForgeConfigSpec.IntValue FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET;

    public static final ForgeConfigSpec.IntValue OCEAN_HUED_TIMER_TICKS;
    public static final ForgeConfigSpec.IntValue OCEAN_HUED_COOL_DOWN_TICKS;
    public static final ForgeConfigSpec.DoubleValue OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT;
    public static final ForgeConfigSpec.DoubleValue OCEAN_HUED_MAX_DAMAGE_ADDITION;
    public static final ForgeConfigSpec.ConfigValue<? extends String> OCEAN_HUED_DETECTION_RANGE;

    public static final ForgeConfigSpec.IntValue MINE_CARVE_VALID_ARMOR_VALUE, MINE_CARVE_ARMOR_VALUE_REDUCTION;

    public static final ForgeConfigSpec.DoubleValue SNIPER_ARROW_SPEED_INCREASE_PER_LEVEL;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ENDER_ENDER_VALID_TARGET;

    public static final ForgeConfigSpec.IntValue ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL, ENDER_ENDER_TELEPORTATION_LIMIT_TICKS_PER_LEVEL;

    public static final ForgeConfigSpec.DoubleValue GOLDFISH_FIREWORKS_SHOOT_PERCENT_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("DetailsConfig");

        builder.push("GoldfishFireworks");
        GOLDFISH_FIREWORKS_SHOOT_PERCENT_PER_LEVEL = builder.defineInRange("goldfishFireworksShootPercentPerLevel", 0.15, 0.00, 1.00);
        builder.pop();

        builder.push("EnderEnder");
        ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL = builder.defineInRange("enderEnderDamageIncreasPerLevel", 1, 0, Integer.MAX_VALUE);
        ENDER_ENDER_TELEPORTATION_LIMIT_TICKS_PER_LEVEL = builder.defineInRange("enderEnderTeleportationLimitTicksPerLevel", 40, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("Sniper");
        SNIPER_ARROW_SPEED_INCREASE_PER_LEVEL = builder.defineInRange("sniperArrowSpeedIncreasePercentagePerLevel", 0.30, 0.00, Double.MAX_VALUE);
        builder.pop();

        builder.push("EnderEnder");
        List<String> ENDER_ENDER_VALID_TARGETS = new ObjectArrayList<>();
        ENDER_ENDER_VALID_TARGETS.add("minecraft:ender_dragon");
        ENDER_ENDER_VALID_TARGETS.add("minecraft:enderman");
        ENDER_ENDER_VALID_TARGETS.add("minecraft:endermite");
        ENDER_ENDER_VALID_TARGETS.add("minecraft:shulker");
        ENDER_ENDER_VALID_TARGET = builder.defineList("enderEnderValidTarget", ENDER_ENDER_VALID_TARGETS, o -> true);
        builder.pop();

        builder.push("MineCarve");
        MINE_CARVE_VALID_ARMOR_VALUE = builder.defineInRange("mineCarveValidArmorValue", 2, 0, Integer.MAX_VALUE);
        MINE_CARVE_ARMOR_VALUE_REDUCTION = builder.defineInRange("mineCarveArmorValueReduction", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("RunLikeHell");
        RUN_LIKE_HELL_INVISIBILITY_DURATION = builder.defineInRange("runLikeHellInvisibilityDuration", 300, 0, Integer.MAX_VALUE);
        RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION = builder.defineInRange("runLikeHellMovementSpeedDuration", 300, 0, Integer.MAX_VALUE);
        RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER = builder.defineInRange("runLikeHellMovementSpeedAmplifier", 1, -1, Integer.MAX_VALUE);
        RUN_LIKE_HELL_VALID_MIN_HEALTH = builder.defineInRange("runLikeHellValidMinHealth", 0.30, 0.00, 1.00, Double.class);
        builder.pop();

        builder.push("BlackParade");
        BLACK_PARADE_MOVEMENT_SPEED_DURATION = builder.defineInRange("blackParadeMovementSpeedDuration", 100, 0, Integer.MAX_VALUE);
        BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER = builder.defineInRange("blackParadeMovementSpeedAmplifier", 0, -1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("ThisIsLeopard");
        THIS_IS_LEOPARD_VALID_HEIGHT = builder.defineInRange("thisIsLeopardValidHeight", 90.00, Double.MIN_VALUE, Double.MAX_VALUE);
        THIS_IS_LEOPARD_DAMAGE_MULTIPLIER = builder.defineInRange("thisIsLeopardDamageMultiplier", 1.5F, 1.1F, Float.MAX_VALUE, Float.class);
        THIS_IS_LEOPARD_TARGET_MISS_CHANCE = builder.defineInRange("thisIsLeopardTargetMissChance", 0.5, 0.0, 1.0);
        builder.pop();


        builder.push("DyingOfLight");
        DYING_OF_LIGHT_VILLAGER_HURT_DAMAGE = builder.defineInRange("dyingOfLightVillagerHurtDamage", 1.00, 0.00, Double.MAX_VALUE);
        DYING_OF_LIGHT_ENTITIES_DETECTION_RANGE = builder.define("dyingOfLightEntitiesDetectionRange", "10;5;10");
        DYING_OF_LIGHT_DETECTION_TICK_INTERVAL = builder.defineInRange("dyingOfLightDetectionTickInterval", 40, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("PressurizedCollapse");
        PRESSURIZED_COLLAPSE_BASIC_RANGE = builder.define("pressurizedCollapseBasicRange", "6;4;6");
        PRESSURIZED_COLLAPSE_MAX_RANGE_ADDITION = builder.defineInRange("pressurizedCollapseMaxRangeAddition", 5L, 0L, 20L);
        builder.pop();

        builder.push("ErrorSpore");
        ERROR_SPORE_DAMAGE_REDUCTION_PER_LEVEL = builder.defineInRange("errorSporeDamageReductionPerLevel", 0.15, 0, 1);
        ERROR_SPORE_CREEPER_SPAWN_CHECKER = builder.comment(
                "When the enchantment's level is high enough, you will summon a creeper on attacking enemy, here is the 'high enough' checker",
                "Its calculation: Get the maximum level of this enchantment, divide it into several parts with the number of parts being this value. If the current enchantment level on the item is greater than the minimum value of the maximum part got from the division, the creeper will be summoned.")
                .defineInRange("errorSporeCreeperSpawnChecker", 3, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("Untouchable");
        UNTOUCHABLE_DETECTION_RANGE = builder.define("untouchableDetectionRange", "5;2;5");
        UNTOUCHABLE_KNOCK_BACK_STRENGTH = builder.defineInRange("untouchableKnockBackStrength", 1.0F, 0.1F, Float.MAX_VALUE, Float.class);
        UNTOUCHABLE_COOL_DOWN = builder.defineInRange("untouchableCoolDown", 200, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("GurenNoYumiya");
        GUREN_NO_YUMIYA_FIRE_ARROW_RADIUS = builder.define("gurenNoYumiyaFireArrowRadius", "5;4;5");
        GUREN_NO_YUMIYA_FIRE_DURATION_TICKS = builder.defineInRange("gurenNoYumiyaFireDurationTicks", 160, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("BoneSuckalaka");
        BONE_SUCKALAKA_DETECTION_RADIUS = builder.define("boneSuckalakaDetectionRadius", "10;10;10");
        builder.pop();

        builder.push("LoRATrainer");
        LORA_TRAINER_KILL_COUNTS_REQUIREMENT = builder.defineInRange("loraTrainerKillCountRequirement", 100, 0, Integer.MAX_VALUE);
        LORA_TRAINER_DAMAGE_BONUS = builder.defineInRange("loraTrainerDamageBonus", 0.8, 0, Double.MAX_VALUE);
        builder.pop();

        builder.push("RippleOfDeath");
        RIPPLE_OF_DEATH_DETECTION_RADIUS = builder.define("rippleOfDeathDetectionRadius", "5;4;5");
        RIPPLE_OF_DEATH_EXTRA_DAMAGE = builder.defineInRange("rippleOfDeathExtraDamage", 10D, 0D, Double.MAX_VALUE);
        builder.pop();

        builder.push("HuaJin");
        HUA_JIN_STRENGTH_EFFECT_DURATION = builder.defineInRange("huaJinStrengthEffectDuration", 200, 0, Integer.MAX_VALUE);
        HUA_JIN_STRENGTH_EFFECT_AMPLIFIER = builder.defineInRange("huaJinStrengthEffectAmplifier", 0, -1, Integer.MAX_VALUE);
        HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE = builder.defineInRange("huaJinTriggerDamagePercentage", 0.10, 0.00, 1.00);
        HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE = builder.defineInRange("huaJinDamageReductionPercentage", 0.50, 0.00, 1.00);
        builder.pop();

        builder.push("WonderEggPriority");
        WONDER_EGG_PRIORITY_EGG_DAMAGE_VALUE = builder.defineInRange("wonderEggPriorityEggDamageValue", 1.00, 0.00, Double.MAX_VALUE);
        builder.pop();

        builder.push("markFromTheBeneath");
        MARK_FROM_THE_BENEATH_VALID_Y = builder.defineInRange("markFromTheBeneathValidY", 20, -114514, Integer.MAX_VALUE);
        MARK_FROM_THE_BENEATH_DIG_SPEED_MULTIPLIER = builder.defineInRange("markFromTheBeneathDigSpeedMultiplier", 1.10, 0.00, Double.MAX_VALUE);
        builder.pop();

        builder.push("ArmorBreaking");
        ARMOR_BREAKING_ARMOR_TOUGHNESS_MULTIPLIER = builder.defineInRange("armorBreakingArmorToughnessMultiplier", 0.80, 0.00, Double.MAX_VALUE);
        ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER = builder.defineInRange("armorBreakingArmorValueMultiplier", 0.75, 0.00, Double.MAX_VALUE);
        ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION = builder.defineInRange("armorBreakingMaxArmorValueReduction", 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
        ARMOR_BREAKING_MULTIPLIER_DURATION_TICKS = builder.defineInRange("armorBreakingMultiplierDurationTicks", 200, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("BlessingOfTheNature");
        BLESSING_OF_THE_NATURE_HEALING_INTERVAL_TICK = builder.defineInRange("blessingOfTheNatureHealingIntervalTick", 100, 0, Integer.MAX_VALUE);
        BLESSING_OF_THE_NATURE_DETECTION_RANGE = builder.define("blessingOfTheNatureDetectionRange", "6;4;6");
        BLESSING_OF_THE_NATURE_HEALING_AMOUNT = builder.defineInRange("blessingOfTheNatureHealingAmount", 3.00, 0.00, Double.MAX_VALUE);
        BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_DAMAGE = builder.defineInRange("blessingOfTheNatureMinKnockBackDamage", 1.00, 0.00, Double.MAX_VALUE);
        BLESSING_OF_THE_NATURE_MAX_KNOCK_BACK_DAMAGE = builder.defineInRange("blessingOfTheNatureMaxKnockBackDamage", 3.00, 0.00, Double.MAX_VALUE);
        BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_STRENGTH = builder.defineInRange("blessingOfTheNatureMinKnockBackStrength", 1.00, 0.00, Double.MAX_VALUE);
        builder.pop();

        builder.push("FlameCross");
        FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET = builder.defineInRange("flameCrossFireDurationTicksOnIgnitingTarget", 200, 0, Integer.MAX_VALUE);
        FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER = builder.defineInRange("flameCrossFireDurationTicksOnIgniteOwner", 200, 0, Integer.MAX_VALUE);
        FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET = builder.defineInRange("flameCrossDamageMultiplierOnIgnitedTarget", 1.25, Double.MIN_VALUE, Double.MAX_VALUE);
        FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING = builder.defineInRange("flameCrossIgniteTargetChanceOnIgnitedOwnerRangedAttacking", 0.33, 0.00, 1.00);
        FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING = builder.define("flameCrossIgniteOwnerOnAttacking", true);
        FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER = builder.defineInRange("flameCrossFireDamageMultiplierOnIgnitedOwner", 1.50, Double.MIN_VALUE, Double.MAX_VALUE);
        FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING = builder.defineInRange("flameCrossIgniteTargetChanceOnIgnitedOwnerMeleeAttacking", 0.24, 0.00, 1.00);
        builder.pop();

        builder.push("OceanHued");
        OCEAN_HUED_DETECTION_RANGE = builder.define("oceanHuedDetectionRange", "6;4;6");
        OCEAN_HUED_TIMER_TICKS = builder.defineInRange("oceanHuedTimerTicks", 300, 0, Integer.MAX_VALUE);
        OCEAN_HUED_MAX_DAMAGE_ADDITION = builder.defineInRange("oceanHuedMaxDamageAddition", 12.00, 0.00, Integer.MAX_VALUE);
        OCEAN_HUED_COOL_DOWN_TICKS = builder.defineInRange("oceanHuedCoolDownTicks", 600, 0, Integer.MAX_VALUE);
        OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT = builder.defineInRange("oceanHuedAdditionalDamageBasedOnHealingAmount", 0.50, 0.00, Double.MAX_VALUE);
        builder.pop();
        DETAILS_CONFIG = builder.build();
    }
}
