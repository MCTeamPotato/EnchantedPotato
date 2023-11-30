package com.teampotato.enchantedpotato;

import com.teampotato.enchantedpotato.effect.CounterattackMoment;
import com.teampotato.enchantedpotato.enchantment.armor.chest.BlessingOfTheNature;
import com.teampotato.enchantedpotato.enchantment.armor.chest.CaressingMoonlight;
import com.teampotato.enchantedpotato.enchantment.armor.chest.HuaJin;
import com.teampotato.enchantedpotato.enchantment.armor.chest.WellOfBlood;
import com.teampotato.enchantedpotato.enchantment.armor.feet.*;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import com.teampotato.enchantedpotato.enchantment.armor.head.FlameCross;
import com.teampotato.enchantedpotato.enchantment.armor.head.WonderEggPriority;
import com.teampotato.enchantedpotato.enchantment.armor.legs.*;
import com.teampotato.enchantedpotato.enchantment.bow.GraceOfGungnir;
import com.teampotato.enchantedpotato.enchantment.bow.GurenNoYumiya;
import com.teampotato.enchantedpotato.enchantment.bow.PressurizedCollapse;
import com.teampotato.enchantedpotato.enchantment.bow.TrueMan;
import com.teampotato.enchantedpotato.enchantment.breakable.elytra.ShootingStar;
import com.teampotato.enchantedpotato.enchantment.breakable.fishing_rod.RickRod;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.ArmsDrum;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.BoneSuckalaka;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.ShieldBladeCommendation;
import com.teampotato.enchantedpotato.enchantment.crossbow.GoldfishFireworks;
import com.teampotato.enchantedpotato.enchantment.crossbow.MultiLoad;
import com.teampotato.enchantedpotato.enchantment.crossbow.Sniper;
import com.teampotato.enchantedpotato.enchantment.digger.MarkFromTheBeneath;
import com.teampotato.enchantedpotato.enchantment.digger.MineCarve;
import com.teampotato.enchantedpotato.enchantment.digger.UniteStonesOfAll;
import com.teampotato.enchantedpotato.enchantment.trident.Missile;
import com.teampotato.enchantedpotato.enchantment.trident.Triback;
import com.teampotato.enchantedpotato.enchantment.weapon.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod(EarlySetupInitializer.MOD_ID)
public final class EnchantedPotato {

    public static final ModConfigSpec DETAILS_CONFIG;
    public static final ModConfigSpec.IntValue RUN_LIKE_HELL_INVISIBILITY_DURATION;
    public static final ModConfigSpec.IntValue RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION;
    public static final ModConfigSpec.IntValue RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER;
    public static final ModConfigSpec.ConfigValue<Double> RUN_LIKE_HELL_VALID_MIN_HEALTH;
    public static final ModConfigSpec.IntValue BLACK_PARADE_MOVEMENT_SPEED_DURATION;
    public static final ModConfigSpec.IntValue BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER;
    public static final ModConfigSpec.ConfigValue<Float> THIS_IS_LEOPARD_DAMAGE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue THIS_IS_LEOPARD_TARGET_MISS_CHANCE;
    public static final ModConfigSpec.DoubleValue THIS_IS_LEOPARD_VALID_HEIGHT;
    public static final ModConfigSpec.ConfigValue<? extends String> DYING_OF_LIGHT_ENTITIES_DETECTION_RANGE;
    public static final ModConfigSpec.IntValue DYING_OF_LIGHT_DETECTION_TICK_INTERVAL;
    public static final ModConfigSpec.DoubleValue DYING_OF_LIGHT_VILLAGER_HURT_DAMAGE;
    public static final ModConfigSpec.LongValue PRESSURIZED_COLLAPSE_MAX_RANGE_ADDITION;
    public static final ModConfigSpec.ConfigValue<? extends String> PRESSURIZED_COLLAPSE_BASIC_RANGE;
    public static final ModConfigSpec.DoubleValue ERROR_SPORE_DAMAGE_REDUCTION_PER_LEVEL;
    public static final ModConfigSpec.IntValue ERROR_SPORE_CREEPER_SPAWN_CHECKER;
    public static final ModConfigSpec.ConfigValue<? extends String> UNTOUCHABLE_DETECTION_RANGE;
    public static final ModConfigSpec.ConfigValue<Float> UNTOUCHABLE_KNOCK_BACK_STRENGTH;
    public static final ModConfigSpec.IntValue UNTOUCHABLE_COOL_DOWN;
    public static final ModConfigSpec.ConfigValue<? extends String> GUREN_NO_YUMIYA_FIRE_ARROW_RADIUS;
    public static final ModConfigSpec.IntValue GUREN_NO_YUMIYA_FIRE_DURATION_TICKS;
    public static final ModConfigSpec.ConfigValue<? extends String> BONE_SUCKALAKA_DETECTION_RADIUS;
    public static final ModConfigSpec.IntValue LORA_TRAINER_KILL_COUNTS_REQUIREMENT;
    public static final ModConfigSpec.DoubleValue LORA_TRAINER_DAMAGE_BONUS;
    public static final ModConfigSpec.ConfigValue<? extends String> RIPPLE_OF_DEATH_DETECTION_RADIUS;
    public static final ModConfigSpec.DoubleValue RIPPLE_OF_DEATH_EXTRA_DAMAGE;
    public static final ModConfigSpec.DoubleValue HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE;
    public static final ModConfigSpec.DoubleValue HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE;
    public static final ModConfigSpec.IntValue HUA_JIN_STRENGTH_EFFECT_DURATION;
    public static final ModConfigSpec.IntValue HUA_JIN_STRENGTH_EFFECT_AMPLIFIER;
    public static final ModConfigSpec.DoubleValue WONDER_EGG_PRIORITY_EGG_DAMAGE_VALUE;
    public static final ModConfigSpec.IntValue MARK_FROM_THE_BENEATH_VALID_Y;
    public static final ModConfigSpec.DoubleValue MARK_FROM_THE_BENEATH_DIG_SPEED_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue ARMOR_BREAKING_ARMOR_TOUGHNESS_MULTIPLIER;
    public static final ModConfigSpec.IntValue ARMOR_BREAKING_MULTIPLIER_DURATION_TICKS;
    public static final ModConfigSpec.IntValue ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION;
    public static final ModConfigSpec.IntValue BLESSING_OF_THE_NATURE_HEALING_INTERVAL_TICK;
    public static final ModConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_HEALING_AMOUNT;
    public static final ModConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_DAMAGE;
    public static final ModConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MAX_KNOCK_BACK_DAMAGE;
    public static final ModConfigSpec.DoubleValue BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_STRENGTH;
    public static final ModConfigSpec.ConfigValue<? extends String> BLESSING_OF_THE_NATURE_DETECTION_RANGE;
    public static final ModConfigSpec.DoubleValue FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET;
    public static final ModConfigSpec.DoubleValue FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER;
    public static final ModConfigSpec.DoubleValue FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING;
    public static final ModConfigSpec.DoubleValue FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING;
    public static final ModConfigSpec.BooleanValue FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING;
    public static final ModConfigSpec.IntValue FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER;
    public static final ModConfigSpec.IntValue FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET;
    public static final ModConfigSpec.IntValue OCEAN_HUED_TIMER_TICKS;
    public static final ModConfigSpec.IntValue OCEAN_HUED_COOL_DOWN_TICKS;
    public static final ModConfigSpec.DoubleValue OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT;
    public static final ModConfigSpec.DoubleValue OCEAN_HUED_MAX_DAMAGE_ADDITION;
    public static final ModConfigSpec.ConfigValue<? extends String> OCEAN_HUED_DETECTION_RANGE;
    public static final ModConfigSpec.IntValue MINE_CARVE_VALID_ARMOR_VALUE;
    public static final ModConfigSpec.IntValue MINE_CARVE_ARMOR_VALUE_REDUCTION;
    public static final ModConfigSpec.DoubleValue SNIPER_ARROW_SPEED_INCREASE_PER_LEVEL;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ENDER_ENDER_VALID_TARGET;
    public static final ModConfigSpec.IntValue ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL;
    public static final ModConfigSpec.IntValue ENDER_ENDER_TELEPORTATION_LIMIT_TICKS_PER_LEVEL;
    public static final ModConfigSpec.DoubleValue GOLDFISH_FIREWORKS_SHOOT_PERCENT_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
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
        List<String> ENDER_ENDER_VALID_TARGETS = ObjectArrayList.wrap(new String[]{"minecraft:ender_dragon", "minecraft:enderman", "minecraft:endermite", "minecraft:shulker"});
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

    public EnchantedPotato() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EnchantedRegistries.ENCHANTMENTS.register(bus);
        EnchantedRegistries.EFFECTS.register(bus);
        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(Constants::initConstants));
        EnchantedEventFactory.setupEvents(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DETAILS_CONFIG, EarlySetupInitializer.MOD_ID + "/details.toml");
    }

    public static @NotNull List<NeutralMob> getNeutralMobs(AABB area, @NotNull ServerLevel level) {
        List<NeutralMob> list = new ObjectArrayList<>();
        for (Entity entity : level.getEntitiesOfClass(PathfinderMob.class, area)) {
            if (entity instanceof NeutralMob) list.add((NeutralMob) entity);
        }
        return list;
    }

    public static EquipmentSlot @NotNull [] getSlots(String @NotNull [] enchantmentSlots) {
        EquipmentSlot[] slots = new EquipmentSlot[enchantmentSlots.length];
        for (int i = 0; i < enchantmentSlots.length; i++) {
            slots[i] = EquipmentSlot.valueOf(enchantmentSlots[i]);
        }
        return slots;
    }

    public static int getPotatoEnchantmentLevel(LivingEntity entity, Enchantment enchantment, String @NotNull [] equipmentSlots) {
        if (entity == null) return 0;
        for (String slot : equipmentSlots) {
            ItemStack stack = entity.getItemBySlot(EquipmentSlot.valueOf(slot));
            return stack.getEnchantmentLevel(enchantment);
        }
        return 0;
    }

    public static boolean hasPotatoEnchantmentEquipped(LivingEntity entity, String @NotNull [] enchantmentEquipmentSlots, String enchantmentName) {
        if (entity == null) return false;
        for (String slot : enchantmentEquipmentSlots) {
            ItemStack stack =  entity.getItemBySlot(EquipmentSlot.valueOf(slot));
            for (Tag tag : stack.getEnchantmentTags()) {
                if (tag instanceof CompoundTag compoundTag) {
                    ResourceLocation id = ResourceLocation.tryParse(compoundTag.getString("id"));
                    if (id != null && BuiltInRegistries.ENCHANTMENT.get(id) != null && id.getPath().equals(enchantmentName)) return true;
                }
            }
        }
        return false;
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    public static final class EnchantedRegistries {
        public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EarlySetupInitializer.MOD_ID);
        public static final DeferredHolder<MobEffect, CounterattackMoment> COUNTERATTACK_MOMENT;

        public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, EarlySetupInitializer.MOD_ID);
        public static final DeferredHolder<Enchantment, RunLikeHell> RUN_LIKE_HELL;
        public static final DeferredHolder<Enchantment, BlackParade> BLACK_PARADE;
        public static final DeferredHolder<Enchantment, GraceOfGungnir> GRACE_OF_GUNGNIR;
        public static final DeferredHolder<Enchantment, ThisIsLeopard> THIS_IS_LEOPARD;
        public static final DeferredHolder<Enchantment, DyingOfLight> DYING_OF_LIGHT;
        public static final DeferredHolder<Enchantment, PressurizedCollapse> PRESSURIZED_COLLAPSE;
        public static final DeferredHolder<Enchantment, ErrorSpore> ERROR_SPORE;
        public static final DeferredHolder<Enchantment, Untouchable> UNTOUCHABLE;
        public static final DeferredHolder<Enchantment, ShieldBladeCommendation> SHIELD_BLADE_COMMENDATION;
        public static final DeferredHolder<Enchantment, GaiaBlessing> GAIA_BLESSING;
        public static final DeferredHolder<Enchantment, GurenNoYumiya> GUREN_NO_YUMIYA;
        public static final DeferredHolder<Enchantment, BoneSuckalaka> BONE_SUCKALAKA;
        public static final DeferredHolder<Enchantment, LoRATrainer> LORA_TRAINER;
        public static final DeferredHolder<Enchantment, RippleOfDeath> RIPPLE_OF_DEATH;
        public static final DeferredHolder<Enchantment, PoisonOfTheLastBreath> POISON_OF_THE_LAST_BREATH;
        public static final DeferredHolder<Enchantment, HuaJin> HUA_JIN;
        public static final DeferredHolder<Enchantment, WonderEggPriority> WONDER_EGG_PRIORITY;
        public static final DeferredHolder<Enchantment, MarkFromTheBeneath> MARK_FROM_THE_BENEATH;
        public static final DeferredHolder<Enchantment, ArmorBreaking> ARMOR_BREAKING;
        public static final DeferredHolder<Enchantment, BlessingOfTheNature> BLESSING_OF_THE_NATURE;
        public static final DeferredHolder<Enchantment, FlameCross> FLAME_CROSS;
        public static final DeferredHolder<Enchantment, OceanHued> OCEAN_HUED;
        public static final DeferredHolder<Enchantment, CaressingMoonlight> CARESSING_MOONLIGHT;
        public static final DeferredHolder<Enchantment, TrueMan> TRUE_MAN;
        public static final DeferredHolder<Enchantment, MineCarve> MINE_CARVE;
        public static final DeferredHolder<Enchantment, UniteStonesOfAll> UNITE_STONES_OF_ALL;
        public static final DeferredHolder<Enchantment, WendingWatersSereneLotus> WENDING_WATERS_SERENE_LOTUS;
        public static final DeferredHolder<Enchantment, KingOfRiding> KING_OF_RIDING;
        public static final DeferredHolder<Enchantment, LawOfInertia> LAW_OF_INERTIA;
        public static final DeferredHolder<Enchantment, ShootingStar> SHOOTING_STAR;
        public static final DeferredHolder<Enchantment, Triback> TRIBACK;
        public static final DeferredHolder<Enchantment, Sniper> SNIPER;
        public static final DeferredHolder<Enchantment, Musician> MUSICIAN;
        public static final DeferredHolder<Enchantment, EnderEnder> ENDER_ENDER;
        public static final DeferredHolder<Enchantment, MultiLoad> MULTI_LOAD;
        public static final DeferredHolder<Enchantment, BlackElegance> BLACK_ELEGANCE;
        public static final DeferredHolder<Enchantment, WhiteInnocence> WHITE_INNOCENCE;
        public static final DeferredHolder<Enchantment, ArmsDrum> ARMS_DRUM;
        public static final DeferredHolder<Enchantment, RickRod> RICK_ROD;
        public static final DeferredHolder<Enchantment, GoldfishFireworks> GOLDFISH_FIREWORKS;
        public static final DeferredHolder<Enchantment, SoftTouch> SOFT_TOUCH;
        public static final DeferredHolder<Enchantment, Copperholic> COPPERHOLIC;
        public static final DeferredHolder<Enchantment, WellOfBlood> WELL_OF_BLOOD;
        public static final DeferredHolder<Enchantment, Missile> MISSILE;

        static {
            COUNTERATTACK_MOMENT = EFFECTS.register(CounterattackMoment.PATH, () -> CounterattackMoment.INSTANCE);

            RUN_LIKE_HELL = ENCHANTMENTS.register(RunLikeHell.PATH, RunLikeHell::new);
            BLACK_PARADE = ENCHANTMENTS.register(BlackParade.PATH, BlackParade::new);
            GRACE_OF_GUNGNIR = ENCHANTMENTS.register(GraceOfGungnir.PATH, GraceOfGungnir::new);
            THIS_IS_LEOPARD = ENCHANTMENTS.register(ThisIsLeopard.PATH, ThisIsLeopard::new);
            DYING_OF_LIGHT = ENCHANTMENTS.register(DyingOfLight.PATH, DyingOfLight::new);
            PRESSURIZED_COLLAPSE = ENCHANTMENTS.register(PressurizedCollapse.PATH, PressurizedCollapse::new);
            ERROR_SPORE = ENCHANTMENTS.register(ErrorSpore.PATH, ErrorSpore::new);
            UNTOUCHABLE = ENCHANTMENTS.register(Untouchable.PATH, Untouchable::new);
            SHIELD_BLADE_COMMENDATION = ENCHANTMENTS.register(ShieldBladeCommendation.PATH, ShieldBladeCommendation::new);
            GAIA_BLESSING = ENCHANTMENTS.register(GaiaBlessing.PATH, GaiaBlessing::new);
            GUREN_NO_YUMIYA = ENCHANTMENTS.register(GurenNoYumiya.PATH, GurenNoYumiya::new);
            BONE_SUCKALAKA = ENCHANTMENTS.register(BoneSuckalaka.PATH, BoneSuckalaka::new);
            LORA_TRAINER = ENCHANTMENTS.register(LoRATrainer.PATH, LoRATrainer::new);
            RIPPLE_OF_DEATH = ENCHANTMENTS.register(RippleOfDeath.PATH, RippleOfDeath::new);
            POISON_OF_THE_LAST_BREATH = ENCHANTMENTS.register(PoisonOfTheLastBreath.PATH, PoisonOfTheLastBreath::new);
            HUA_JIN = ENCHANTMENTS.register(HuaJin.PATH, HuaJin::new);
            WONDER_EGG_PRIORITY = ENCHANTMENTS.register(WonderEggPriority.PATH, WonderEggPriority::new);
            MARK_FROM_THE_BENEATH = ENCHANTMENTS.register(MarkFromTheBeneath.PATH, MarkFromTheBeneath::new);
            ARMOR_BREAKING = ENCHANTMENTS.register(ArmorBreaking.PATH, ArmorBreaking::new);
            BLESSING_OF_THE_NATURE = ENCHANTMENTS.register(BlessingOfTheNature.PATH, BlessingOfTheNature::new);
            FLAME_CROSS = ENCHANTMENTS.register(FlameCross.PATH, FlameCross::new);
            OCEAN_HUED = ENCHANTMENTS.register(OceanHued.PATH, OceanHued::new);
            CARESSING_MOONLIGHT = ENCHANTMENTS.register(CaressingMoonlight.PATH, CaressingMoonlight::new);
            TRUE_MAN = ENCHANTMENTS.register(TrueMan.PATH, TrueMan::new);
            MINE_CARVE = ENCHANTMENTS.register(MineCarve.PATH, MineCarve::new);
            UNITE_STONES_OF_ALL = ENCHANTMENTS.register(UniteStonesOfAll.PATH, UniteStonesOfAll::new);
            WENDING_WATERS_SERENE_LOTUS = ENCHANTMENTS.register(WendingWatersSereneLotus.PATH, WendingWatersSereneLotus::new);
            KING_OF_RIDING = ENCHANTMENTS.register(KingOfRiding.PATH, KingOfRiding::new);
            LAW_OF_INERTIA = ENCHANTMENTS.register(LawOfInertia.PATH, LawOfInertia::new);
            SHOOTING_STAR = ENCHANTMENTS.register(ShootingStar.PATH, ShootingStar::new);
            TRIBACK = ENCHANTMENTS.register(Triback.PATH, Triback::new);
            SNIPER = ENCHANTMENTS.register(Sniper.PATH, Sniper::new);
            MUSICIAN = ENCHANTMENTS.register(Musician.PATH, Musician::new);
            ENDER_ENDER = ENCHANTMENTS.register(EnderEnder.PATH, EnderEnder::new);
            MULTI_LOAD = ENCHANTMENTS.register(MultiLoad.PATH, MultiLoad::new);
            BLACK_ELEGANCE = ENCHANTMENTS.register(BlackElegance.PATH, BlackElegance::new);
            WHITE_INNOCENCE = ENCHANTMENTS.register(WhiteInnocence.PATH, WhiteInnocence::new);
            ARMS_DRUM = ENCHANTMENTS.register(ArmsDrum.PATH, ArmsDrum::new);
            RICK_ROD = ENCHANTMENTS.register(RickRod.PATH, RickRod::new);
            GOLDFISH_FIREWORKS = ENCHANTMENTS.register(GoldfishFireworks.PATH, GoldfishFireworks::new);
            SOFT_TOUCH = ENCHANTMENTS.register(SoftTouch.PATH, SoftTouch::new);
            COPPERHOLIC = ENCHANTMENTS.register(Copperholic.PATH, Copperholic::new);
            WELL_OF_BLOOD = ENCHANTMENTS.register(WellOfBlood.PATH, WellOfBlood::new);
            MISSILE = ENCHANTMENTS.register(Missile.PATH, Missile::new);
        }
    }
}
