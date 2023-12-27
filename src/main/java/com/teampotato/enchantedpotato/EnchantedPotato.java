package com.teampotato.enchantedpotato;

import com.teampotato.enchantedpotato.api.*;
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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod(EarlySetupInitializer.MOD_ID)
public final class EnchantedPotato {

    public EnchantedPotato() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = NeoForge.EVENT_BUS;
        final ModLoadingContext ctx = ModLoadingContext.get();

        EnchantedRegistries.ENCHANTMENTS.register(modBus);
        EnchantedRegistries.EFFECTS.register(modBus);
        EnchantedEventFactory.setupEvents(modBus, forgeBus);

        ctx.registerConfig(ModConfig.Type.COMMON, EnchantedConfig.DETAILS_CONFIG, EarlySetupInitializer.MOD_ID + "/details.toml");
    }

    public static final class EnchantedUtils {
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
    }

    public static final class EnchantedConfig {
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

    public static final class Constants {
        public static double oceanHuedX;
        public static double oceanHuedY;
        public static double oceanHuedZ;
        public static double blessingOfTheNatureX;
        public static double blessingOfTheNatureY;
        public static double blessingOfTheNatureZ;

        public static double dyingOfLightX;
        public static double dyingOfLightY;
        public static double dyingOfLightZ;

        public static double pressurizedCollapseX;
        public static double pressurizedCollapseY;
        public static double pressurizedCollapseZ;

        public static double untouchableX;
        public static double untouchableY;
        public static double untouchableZ;

        public static double gurenNoYumiyaX;
        public static double gurenNoYumiyaY;
        public static double gurenNoYumiyaZ;

        public static double boneSuckalakaX;
        public static double boneSuckalakaY;
        public static double boneSuckalakaZ;

        public static double rippleOfDeathX;
        public static double rippleOfDeathY;
        public static double rippleOfDeathZ;

        public static void initConstants(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                String[] oceanHuedRange = EnchantedConfig.OCEAN_HUED_DETECTION_RANGE.get().split(";");
                oceanHuedX = Double.parseDouble(oceanHuedRange[0]);
                oceanHuedY = Double.parseDouble(oceanHuedRange[1]);
                oceanHuedZ = Double.parseDouble(oceanHuedRange[2]);

                String[] blessingOfTheNatureRange = EnchantedConfig.BLESSING_OF_THE_NATURE_DETECTION_RANGE.get().split(";");
                blessingOfTheNatureX = Double.parseDouble(blessingOfTheNatureRange[0]);
                blessingOfTheNatureY = Double.parseDouble(blessingOfTheNatureRange[1]);
                blessingOfTheNatureZ = Double.parseDouble(blessingOfTheNatureRange[2]);

                String[] dyingOfLightRange = EnchantedConfig.DYING_OF_LIGHT_ENTITIES_DETECTION_RANGE.get().split(";");
                dyingOfLightX = Double.parseDouble(dyingOfLightRange[0]);
                dyingOfLightY = Double.parseDouble(dyingOfLightRange[1]);
                dyingOfLightZ = Double.parseDouble(dyingOfLightRange[2]);

                String[] pressurizedCollapseRange = EnchantedConfig.PRESSURIZED_COLLAPSE_BASIC_RANGE.get().split(";");
                pressurizedCollapseX = Double.parseDouble(pressurizedCollapseRange[0]);
                pressurizedCollapseY = Double.parseDouble(pressurizedCollapseRange[1]);
                pressurizedCollapseZ = Double.parseDouble(pressurizedCollapseRange[2]);

                String[] untouchableRange = EnchantedConfig.UNTOUCHABLE_DETECTION_RANGE.get().split(";");
                untouchableX = Double.parseDouble(untouchableRange[0]);
                untouchableY = Double.parseDouble(untouchableRange[1]);
                untouchableZ = Double.parseDouble(untouchableRange[2]);

                String[] gurenNoYumiyaRange = EnchantedConfig.GUREN_NO_YUMIYA_FIRE_ARROW_RADIUS.get().split(";");
                gurenNoYumiyaX = Double.parseDouble(gurenNoYumiyaRange[0]);
                gurenNoYumiyaY = Double.parseDouble(gurenNoYumiyaRange[1]);
                gurenNoYumiyaZ = Double.parseDouble(gurenNoYumiyaRange[2]);

                String[] boneSuckalakaRange = EnchantedConfig.BONE_SUCKALAKA_DETECTION_RADIUS.get().split(";");
                boneSuckalakaX = Double.parseDouble(boneSuckalakaRange[0]);
                boneSuckalakaY = Double.parseDouble(boneSuckalakaRange[1]);
                boneSuckalakaZ = Double.parseDouble(boneSuckalakaRange[2]);

                String[] rippleOfDeathRange = EnchantedConfig.RIPPLE_OF_DEATH_DETECTION_RADIUS.get().split(";");
                rippleOfDeathX = Double.parseDouble(rippleOfDeathRange[0]);
                rippleOfDeathY = Double.parseDouble(rippleOfDeathRange[1]);
                rippleOfDeathZ = Double.parseDouble(rippleOfDeathRange[2]);

                for (int i = 1; i <= EnchantedRegistries.ENDER_ENDER.get().getMaxLevel(); i ++) {
                    EnderEnder.ENDER_ENDER_TELEPORTATION_LIMIT_LEVEL_MAP.put(i, EnchantedConfig.ENDER_ENDER_TELEPORTATION_LIMIT_TICKS_PER_LEVEL.get().intValue());
                }

                for (int i = 1; i <= EnchantedRegistries.ERROR_SPORE.get().getMaxLevel(); i ++) {
                    double damageReductionPercent = EnchantedConfig.ERROR_SPORE_DAMAGE_REDUCTION_PER_LEVEL.get() * i;
                    if (damageReductionPercent >= 1.00) damageReductionPercent = 1.00;
                    ErrorSpore.ERROR_SPORE_LEVEL_MAP.put(i, (float) damageReductionPercent);
                }

                EnderEnder.ENDER_ENDER_TELEPORTATION_LIMIT_LEVEL_MAP.put(0, 0);
                ErrorSpore.ERROR_SPORE_LEVEL_MAP.put(0, 0.0F);

                MineCarve.ARMOR_VALUE_MODIFIER = new AttributeModifier(MineCarve.ARMOR_VALUE_MODIFIER_UUID, "mineCarveArmorValue", -EnchantedConfig.MINE_CARVE_ARMOR_VALUE_REDUCTION.get(), AttributeModifier.Operation.ADDITION);
                MarkFromTheBeneath.DIG_SPEED_MODIFIER = new AttributeModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER_UUID, "markFromTheBeneathDigSpeed", (EnchantedConfig.MARK_FROM_THE_BENEATH_DIG_SPEED_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
                ArmorBreaking.ARMOR_VALUE_MODIFIER = new AttributeModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER_UUID, "armorBreakingArmorValue", (EnchantedConfig.ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
                ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER = new AttributeModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER_UUID, "armorBreakingArmorToughness", (EnchantedConfig.ARMOR_BREAKING_ARMOR_TOUGHNESS_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
            });
        }
    }

    public static final class EnchantedEventFactory {

        static void setupEvents(final IEventBus modBus, final IEventBus forgeBus) {
            modBus.addListener(Constants::initConstants);
            forgeBus.addListener(EnchantedEventFactory::equipChangeCenter);
            if (EarlySetupInitializer.functionConfig.armorBreaking) forgeBus.addListener(EnchantedEventFactory::handleArmorBreaking);
            if (EarlySetupInitializer.functionConfig.blackParade) forgeBus.addListener(EnchantedEventFactory::handleBlackParade);
            if (EarlySetupInitializer.functionConfig.boneSuckalaka) forgeBus.addListener(EnchantedEventFactory::handleBoneSuckalaka);
            if (EarlySetupInitializer.functionConfig.dyingOfLight) forgeBus.addListener(EventPriority.HIGHEST, EnchantedEventFactory::handleDyingOfLight);
            if (EarlySetupInitializer.functionConfig.enderEnder) {
                forgeBus.addListener(EnchantedEventFactory::handleEnderEnderOnLivingHurt);
                forgeBus.addListener(EnchantedEventFactory::handleEnderEnderOnEnderTp);
                modBus.addListener(EnchantedEventFactory::handleEnderEnderOnCommonSetup);
            }
            if (EarlySetupInitializer.functionConfig.errorSpore) {
                forgeBus.addListener(EventPriority.HIGHEST, EnchantedEventFactory::handleErrorSporeOnLivingHurt);
                forgeBus.addListener(EventPriority.HIGHEST, EnchantedEventFactory::handleErrorSporeOnLivingDrop);
            }
            if (EarlySetupInitializer.functionConfig.flameCross) forgeBus.addListener(EnchantedEventFactory::handleFlameCross);
            if (EarlySetupInitializer.functionConfig.gaiaBlessing) {
                forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleGaiaBlessingOnLivingSpawn);
                forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleGaiaBlessingOnLivingTick);
            }
            if (EarlySetupInitializer.functionConfig.huaJin) forgeBus.addListener(EnchantedEventFactory::handleHuaJin);
            if (EarlySetupInitializer.functionConfig.loraTrainer) forgeBus.addListener(EnchantedEventFactory::handleLoRATrainer);
            if (EarlySetupInitializer.functionConfig.markFromTheBeneath) forgeBus.addListener(EnchantedEventFactory::handleMarkFromTheBeneath);
            if (EarlySetupInitializer.functionConfig.mineCarve) forgeBus.addListener(EnchantedEventFactory::handleMineCarve);
            if (EarlySetupInitializer.functionConfig.oceanHued) {
                forgeBus.addListener(EnchantedEventFactory::handleOceanHuedOnLivingHeal);
                forgeBus.addListener(EnchantedEventFactory::handleOceanHuedOnLivingTick);
                forgeBus.addListener(EnchantedEventFactory::handleOceanHuedOnLivingHurt);
            }
            if (EarlySetupInitializer.functionConfig.pressurizedCollapse) forgeBus.addListener(EnchantedEventFactory::handlePressurizedCollapse);
            if (EarlySetupInitializer.functionConfig.rippleOfDeath) forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleRippleOfDeath);
            if (EarlySetupInitializer.functionConfig.runLikeHell) forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleRunLikeHell);
            if (EarlySetupInitializer.functionConfig.shieldBladeCommendation) {
                forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleShieldBladeCommendationOnLivingHurt);
                forgeBus.addListener(EventPriority.LOWEST, EnchantedEventFactory::handleShieldBladeCommendationOnShieldBlock);
            }
            if (EarlySetupInitializer.functionConfig.thisIsLeopard) {
                forgeBus.addListener(EventPriority.HIGHEST, EnchantedEventFactory::handleThisIsLeopardOnLivingChangeTarget);
                forgeBus.addListener(EnchantedEventFactory::handleThisIsLeopardOnLivingAttack);
            }
            if (EarlySetupInitializer.functionConfig.untouchable) {
                forgeBus.addListener(EnchantedEventFactory::handleUntouchableOnLivingTick);
                forgeBus.addListener(EnchantedEventFactory::handleUntouchableOnLivingHurt);
            }
        }

        static void equipChangeCenter(LivingEquipmentChangeEvent event) {
            LivingEntity entity = event.getEntity();
            String fromEnchantments = event.getFrom().getEnchantmentTags().toString();
            String toEnchantments = event.getTo().getEnchantmentTags().toString();
            if (entity.level().isClientSide) return;
            if (EarlySetupInitializer.functionConfig.untouchable) {
                if (fromEnchantments.contains(Untouchable.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".untouchable");
                if (toEnchantments.contains(Untouchable.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".untouchable");
            }
            if (EarlySetupInitializer.functionConfig.blessingOfTheNature) {
                if (fromEnchantments.contains(BlessingOfTheNature.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".natureBlessed");
                if (toEnchantments.contains(BlessingOfTheNature.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".natureBlessed");
            }
            if (EarlySetupInitializer.functionConfig.dyingOfLight) {
                if (fromEnchantments.contains(DyingOfLight.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip");
                if (toEnchantments.contains(DyingOfLight.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip");
            }
        }

        static void handleArmorBreaking(LivingAttackEvent event) {
            LivingEntity entity = event.getEntity();
            AttributeInstance armorValueAttribute = entity.getAttribute(Attributes.ARMOR);
            AttributeInstance armorToughnessAttribute = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
            if (armorToughnessAttribute == null || armorValueAttribute == null || entity.level().isClientSide) return;
            if (event.getSource().getDirectEntity() instanceof LivingEntity sourceDirectEntity && EnchantedUtils.hasPotatoEnchantmentEquipped(sourceDirectEntity, EarlySetupInitializer.equipmentSlotConfig.armorBreaking, ArmorBreaking.PATH)) {
                if (!armorValueAttribute.hasModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER) && armorValueAttribute.getValue() * (1.00 - EnchantedConfig.ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER.get()) <= EnchantedConfig.ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION.get().doubleValue()) armorValueAttribute.addTransientModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER);
                if (!armorToughnessAttribute.hasModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER)) armorToughnessAttribute.addTransientModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER);
            }
        }

        static void handleBlackParade(LivingDeathEvent event) {
            DamageSource damageSource = event.getSource();
            if (damageSource.getDirectEntity() instanceof LivingEntity directSourceEntity) {
                MobEffectInstance effect = directSourceEntity.getEffect(MobEffects.MOVEMENT_SPEED);
                if (EnchantedUtils.hasPotatoEnchantmentEquipped(directSourceEntity, EarlySetupInitializer.equipmentSlotConfig.blackParade, BlackParade.PATH)) {
                    if (effect != null) {
                        directSourceEntity.removeEffect(effect.getEffect());
                        directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedConfig.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get() + effect.getDuration(), EnchantedConfig.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                    } else {
                        directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedConfig.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get(), EnchantedConfig.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                    }
                }
            }
        }

        static void handleBoneSuckalaka(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            if (player instanceof ServerPlayer serverPlayer && serverPlayer.isShiftKeyDown() && serverPlayer.isUsingItem() && serverPlayer.getUseItem().getItem() instanceof ShieldItem && EnchantedUtils.hasPotatoEnchantmentEquipped(serverPlayer, EarlySetupInitializer.equipmentSlotConfig.boneSuckalaka, BoneSuckalaka.PATH)) {
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                for (AbstractSkeleton skeleton : player.level().getEntitiesOfClass(AbstractSkeleton.class, new AABB(x - Constants.boneSuckalakaX, y - Constants.boneSuckalakaY, z - Constants.boneSuckalakaZ, x + Constants.boneSuckalakaX, y + Constants.boneSuckalakaY, z + Constants.boneSuckalakaZ))) {
                    skeleton.setDeltaMovement(player.position());
                }
            }
        }

        static void handleDyingOfLight(LivingChangeTargetEvent event) {
            if (event.isCanceled()) return;
            LivingEntity entity = event.getEntity();
            LivingEntity target = event.getOriginalTarget();
            if (EnchantedUtils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH) && entity.level().isNight() && entity instanceof Monster) {
                LivingEntity lastHurtMob = target.getLastHurtMob();
                if (lastHurtMob != null && lastHurtMob.getUUID().equals(entity.getUUID())) return;
                event.setCanceled(true);
            }
        }

        static void handleEnderEnderOnLivingHurt(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            DamageSource damageSource = event.getSource();
            if (event.isCanceled() || attacked.level().isClientSide || !((EnderTarget)attacked.getType()).isEnderTarget() || !(damageSource.getDirectEntity() instanceof LivingEntity entity)) return;
            int level = EnchantedUtils.getPotatoEnchantmentLevel(entity, EnchantedRegistries.ENDER_ENDER.get(), EarlySetupInitializer.equipmentSlotConfig.enderEnder);
            if (level > 0) {
                if (attacked.getTags().stream().noneMatch(tag -> tag.startsWith(EarlySetupInitializer.MOD_ID + ".end."))) {
                    attacked.addTag(EarlySetupInitializer.MOD_ID + ".end." + level);
                }
                event.setAmount(event.getAmount() + (float) level * EnchantedConfig.ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL.get().floatValue());
            }
        }

        static void handleEnderEnderOnCommonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE) {
                    ((EnderTarget)entityType).setIsEnderTarget(EnchantedConfig.ENDER_ENDER_VALID_TARGET.get().contains(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString()));
                }
            });
        }

        static void handleEnderEnderOnEnderTp(EntityTeleportEvent.EnderEntity event) {
            LivingEntity attacked = event.getEntityLiving();
            if (((EnderTarget)attacked.getType()).isEnderTarget()) {
                if (attacked instanceof EnderMan enderMan && ((EnderTeleporter) enderMan).ep$cantEnderTp() ||
                        attacked instanceof Shulker shulker && ((EnderTeleporter) shulker).ep$cantEnderTp()) {
                    event.setCanceled(true);
                }
            }
        }

        static void handleErrorSporeOnLivingHurt(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            Level level = entity.level();
            DamageSource source = event.getSource();
            if (level.isClientSide) return;
            event.setAmount(event.getAmount() * (1.0F - ErrorSpore.ERROR_SPORE_LEVEL_MAP.get(EnchantedUtils.getPotatoEnchantmentLevel(entity, EnchantedRegistries.ERROR_SPORE.get(), EarlySetupInitializer.equipmentSlotConfig.errorSpore))));
            if (source.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
                int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(EnchantedRegistries.ERROR_SPORE.get(), sourceDirectEntity);
                int creeperSpawnChecker = EnchantedConfig.ERROR_SPORE_CREEPER_SPAWN_CHECKER.get();
                if (source.getSourcePosition() != null && enchantmentLevel < EnchantedRegistries.ERROR_SPORE.get().getMaxLevel() && enchantmentLevel > EnchantedRegistries.ERROR_SPORE.get().getMaxLevel() / creeperSpawnChecker * (creeperSpawnChecker - 1)) {
                    Creeper creeper = new Creeper(EntityType.CREEPER, level);
                    creeper.setPos(source.getSourcePosition());
                    creeper.addTag(EarlySetupInitializer.MOD_ID + ".errorSpore");
                    level.addFreshEntity(creeper);
                }
            }
        }

        static void handleErrorSporeOnLivingDrop(LivingDropsEvent event) {
            LivingEntity entity = event.getEntity();
            if (!entity.level().isClientSide && entity.removeTag(EarlySetupInitializer.MOD_ID + ".errorSpore")) event.setCanceled(true);
        }

        static void handleFlameCross(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide) return;
            DamageSource damageSource = event.getSource();
            Entity sourceEntity = damageSource.getEntity();
            Entity sourceDirectEntity = damageSource.getDirectEntity();
            if (sourceDirectEntity instanceof LivingEntity source && sourceEntity != null && EnchantedUtils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH)) {
                if (EnchantedConfig.FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING.get()) ((IEntity)source).ep$setTicksOnFire(EnchantedConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER.get());
                if (sourceDirectEntity.getStringUUID().equals(sourceEntity.getStringUUID())) {
                    if (ThreadLocalRandom.current().nextDouble(0.00, 1.00) < EnchantedConfig.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING.get()) {
                        ((IEntity)entity).ep$setTicksOnFire(EnchantedConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                    }
                } else {
                    if (ThreadLocalRandom.current().nextDouble(0.00, 1.00) < EnchantedConfig.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING.get()) {
                        ((IEntity)entity).ep$setTicksOnFire(EnchantedConfig.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                    }
                }
                if (entity.isOnFire()) event.setAmount(event.getAmount() * EnchantedConfig.FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET.get().floatValue());
            }
            if (EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH) && damageSource.is(DamageTypeTags.IS_FIRE)) {
                event.setAmount(event.getAmount() * EnchantedConfig.FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER.get().floatValue());
            }
        }

        static void handleGaiaBlessingOnLivingTick(LivingEvent.LivingTickEvent event) {
            if (event.isCanceled()) return;
            LivingEntity entity = event.getEntity();
            double y = entity.getY();
            AttributeInstance attributeInstance = entity.getAttribute(Attributes.ARMOR);
            if (attributeInstance == null || y >= 50 || entity.level().isClientSide || !EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.gaiaBlessing, GaiaBlessing.PATH)) return;
            AttributeModifier attributeModifier = attributeInstance.getModifier(GaiaBlessing.MODIFIER_UUID);
            if (attributeModifier == null) return;
            ((IAttributeModifier)attributeModifier).ep$setAmount(50.00 - y);
        }

        static void handleGaiaBlessingOnLivingSpawn(MobSpawnEvent.FinalizeSpawn event) {
            if (event.isCanceled()) return;
            LivingEntity entity = event.getEntity();
            AttributeInstance attributeInstance = entity.getAttribute(Attributes.ARMOR);
            if (attributeInstance == null || entity.level().isClientSide) return;
            attributeInstance.addPermanentModifier(new AttributeModifier(GaiaBlessing.MODIFIER_UUID, () -> GaiaBlessing.PATH + "_modifier", 0, AttributeModifier.Operation.ADDITION));
        }

        static void handleHuaJin(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide() || !EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.huaJin, HuaJin.PATH)) return;
            float amount = event.getAmount();
            float health = entity.getHealth();
            float huaJin = EnchantedConfig.HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE.get().floatValue();
            if (amount > health * huaJin) {
                event.setAmount(amount - (amount - health * huaJin) * EnchantedConfig.HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE.get().floatValue());
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, EnchantedConfig.HUA_JIN_STRENGTH_EFFECT_DURATION.get(), EnchantedConfig.HUA_JIN_STRENGTH_EFFECT_AMPLIFIER.get()));
            }
        }

        static void handleLoRATrainer(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            if (event.getSource().getDirectEntity() instanceof ServerPlayer player && !entity.level().isClientSide()) {
                if (player.getStats().getValue(Stats.ENTITY_KILLED.get(entity.getType())) < EnchantedConfig.LORA_TRAINER_KILL_COUNTS_REQUIREMENT.get()) return;
                if (EnchantedUtils.hasPotatoEnchantmentEquipped(player, EarlySetupInitializer.equipmentSlotConfig.loraTrainer, LoRATrainer.PATH)) {
                    event.setAmount((EnchantedConfig.LORA_TRAINER_DAMAGE_BONUS.get().floatValue() + 1.0F) * event.getAmount());
                }
            }
        }

        static void handleMarkFromTheBeneath(LivingEvent.LivingTickEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide) return;
            AttributeInstance digSpeedAttribute = entity.getAttribute(Attributes.ATTACK_SPEED);
            if (entity.getY() > EnchantedConfig.MARK_FROM_THE_BENEATH_VALID_Y.get() || event.isCanceled() || digSpeedAttribute == null || digSpeedAttribute.hasModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER) || !(entity.getUseItem().getItem() instanceof PickaxeItem) || !EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.markFromTheBeneath, MarkFromTheBeneath.PATH)) {
                if (digSpeedAttribute != null) digSpeedAttribute.removeModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER_UUID);
                return;
            }
            digSpeedAttribute.addTransientModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER);
        }

        static void handleMineCarve(LivingAttackEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide) return;
            DamageSource damageSource = event.getSource();
            if (damageSource.getDirectEntity() instanceof LivingEntity source && entity.getArmorValue() >= EnchantedConfig.MINE_CARVE_VALID_ARMOR_VALUE.get() && EnchantedUtils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.mineCarve, MineCarve.PATH)) {
                AttributeInstance armorValue = entity.getAttribute(Attributes.ARMOR);
                if (armorValue == null || armorValue.hasModifier(MineCarve.ARMOR_VALUE_MODIFIER)) return;
                armorValue.addTransientModifier(MineCarve.ARMOR_VALUE_MODIFIER);
            }
        }

        static void handleOceanHuedOnLivingHeal(LivingHealEvent event) {
            LivingEntity healed = event.getEntity();
            ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) healed;
            if (iLivingEntity.ep$getIsOceanHuedAttackReady() || iLivingEntity.ep$getOceanHuedCoolDownTickCount() != 0) return;
            if (!iLivingEntity.ep$getOceanHuedTimerStatus()) {
                iLivingEntity.ep$setOceanHuedTimerStatus(true);
                iLivingEntity.ep$setOceanHuedHealingAmount(event.getAmount());
            } else {
                iLivingEntity.ep$setOceanHuedHealingAmount(iLivingEntity.ep$getOceanHuedHealingAmount() + event.getAmount());
            }
        }

        static void handleOceanHuedOnLivingTick(LivingEvent.LivingTickEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide) return;
            ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) entity;
            if (iLivingEntity.ep$getOceanHuedTimerStatus()) {
                iLivingEntity.ep$setOceanHuedTimerTickCount(iLivingEntity.ep$getOceanHuedTimerTickCount() + 1);
                if (iLivingEntity.ep$getOceanHuedTimerTickCount() >= EnchantedConfig.OCEAN_HUED_TIMER_TICKS.get()) {
                    iLivingEntity.ep$resetOceanHuedTimer();
                    iLivingEntity.ep$setIsOceanHuedAttackReady(true);
                }
            }
            if (iLivingEntity.ep$getOceanHuedCoolDownTickCount() > 0) {
                iLivingEntity.ep$setOceanHuedCoolDownTickCount(iLivingEntity.ep$getOceanHuedCoolDownTickCount() - 1);
            }
        }

        static void handleOceanHuedOnLivingHurt(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            DamageSource damageSource = event.getSource();
            if (damageSource.getDirectEntity() instanceof LivingEntity sourceEntity) {
                ILivingEntity.OceanHued iLivingEntity = (ILivingEntity.OceanHued) sourceEntity;
                if (iLivingEntity.ep$getIsOceanHuedAttackReady()) {
                    float maxDamage = EnchantedConfig.OCEAN_HUED_MAX_DAMAGE_ADDITION.get().floatValue();
                    float amount = iLivingEntity.ep$getOceanHuedHealingAmount() * EnchantedConfig.OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT.get().floatValue();
                    if (amount >= maxDamage) amount = maxDamage;
                    double x = attacked.getX();
                    double y = attacked.getY();
                    double z = attacked.getZ();
                    for (LivingEntity target : attacked.level().getEntitiesOfClass(LivingEntity.class, new AABB(x - Constants.oceanHuedX, y - Constants.oceanHuedY, z - Constants.oceanHuedZ, x + Constants.oceanHuedX, y + Constants.oceanHuedY, z + Constants.oceanHuedZ))) {
                        target.hurt(target.level().damageSources().inWall(), amount);
                    }
                    iLivingEntity.ep$setIsOceanHuedAttackReady(false);
                    iLivingEntity.ep$setOceanHuedCoolDownTickCount(EnchantedConfig.OCEAN_HUED_COOL_DOWN_TICKS.get());
                }
            }
        }

        static void handlePressurizedCollapse(PlayerInteractEvent.RightClickItem event) {
            Player player = event.getEntity();
            if (player.level().isClientSide || !(event.getItemStack().getItem() instanceof BowItem)) return;

            if (((IPlayer)player).ep$getStartUsingBowTime() != 0) {
                ((IPlayer)player).ep$setRealChargeTime(System.currentTimeMillis() - ((IPlayer)player).ep$getStartUsingBowTime());
            }

            ((IPlayer)player).ep$setStartUsingBowTime(System.currentTimeMillis());
        }

        static void handleRippleOfDeath(LivingDeathEvent event) {
            if (event.isCanceled()) return;
            DamageSource damageSource = event.getSource();
            if (damageSource.getDirectEntity() instanceof LivingEntity entity) {
                Level level = entity.level();
                if (level.isClientSide()) return;
                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();
                if (EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.rippleOfDeath, RippleOfDeath.PATH)) {
                    for (Monster monster : level.getEntitiesOfClass(Monster.class, new AABB(x - Constants.rippleOfDeathX, y - Constants.rippleOfDeathY, z - Constants.rippleOfDeathZ, x + Constants.rippleOfDeathX, y + Constants.rippleOfDeathY, z + Constants.rippleOfDeathZ))) {
                        monster.hurt(monster.level().damageSources().generic(), EnchantedConfig.RIPPLE_OF_DEATH_EXTRA_DAMAGE.get().floatValue());
                    }
                }
            }
        }

        static void handleRunLikeHell(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            if (attacked.level().isClientSide) return;
            if (!event.isCanceled() && EnchantedUtils.hasPotatoEnchantmentEquipped(attacked, EarlySetupInitializer.equipmentSlotConfig.runLikeHell, RunLikeHell.PATH) && ((Float) attacked.getHealth()).doubleValue() - ((Float) event.getAmount()).doubleValue() < ((Float) attacked.getMaxHealth()).doubleValue() * EnchantedConfig.RUN_LIKE_HELL_VALID_MIN_HEALTH.get()) {
                attacked.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EnchantedConfig.RUN_LIKE_HELL_INVISIBILITY_DURATION.get()));
                attacked.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedConfig.RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION.get(), EnchantedConfig.RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER.get()));
                attacked.addTag(EarlySetupInitializer.MOD_ID + ".blinder");
                attacked.addTag(EarlySetupInitializer.MOD_ID + ".tickCountTrigger");
                return;
            }

            DamageSource damageSource = event.getSource();
            if (damageSource.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
                sourceDirectEntity.removeTag(EarlySetupInitializer.MOD_ID + ".blinder");
                ((ILivingEntity)sourceDirectEntity).ep$setRunLikeHellTickCount(0);
                ((ILivingEntity)sourceDirectEntity).ep$setShouldTrackRunLikeHell(false);
            }
        }

        static void handleShieldBladeCommendationOnShieldBlock(ShieldBlockEvent event) {
            LivingEntity entity = event.getEntity();
            if (!event.isCanceled() && !entity.level().isClientSide && EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.shieldBladeCommendation, ShieldBladeCommendation.PATH)) {
                entity.addEffect(new MobEffectInstance(CounterattackMoment.INSTANCE, 40, 0));
                entity.addTag(EarlySetupInitializer.MOD_ID + ".counter_attacker");
            }
        }

        static void handleShieldBladeCommendationOnLivingHurt(LivingHurtEvent event) {
            DamageSource source = event.getSource();
            if (source.getEntity() instanceof Player sourcePlayer && sourcePlayer.removeTag(EarlySetupInitializer.MOD_ID + ".counter_attacker")) {
                event.setAmount(event.getAmount() * 1.5F);
                sourcePlayer.resetAttackStrengthTicker();//TODO: Not sure.
            }
        }

        static void handleThisIsLeopardOnLivingAttack(LivingHurtEvent event) {
            DamageSource source = event.getSource();
            if (source.getDirectEntity() instanceof LivingEntity entity && entity.getY() > 95.00 && EnchantedUtils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH)) {
                event.setAmount(event.getAmount() * EnchantedConfig.THIS_IS_LEOPARD_DAMAGE_MULTIPLIER.get());
            }
        }

        static void handleThisIsLeopardOnLivingChangeTarget(LivingChangeTargetEvent event) {
            LivingEntity target = event.getOriginalTarget();
            if (target.getY() > EnchantedConfig.THIS_IS_LEOPARD_VALID_HEIGHT.get() && EnchantedUtils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH) && ThreadLocalRandom.current().nextDouble(0.00,1.00) < EnchantedConfig.THIS_IS_LEOPARD_TARGET_MISS_CHANCE.get()) {
                event.setCanceled(true);
            }
        }

        static void handleUntouchableOnLivingHurt(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            Level level = entity.level();
            DamageSource damageSource = event.getSource();
            if (!level.isClientSide && ((ILivingEntity)entity).ep$ShouldTickUntouchable() && damageSource.getDirectEntity() instanceof LivingEntity sourceDirectEntity && entity.getTags().contains(EarlySetupInitializer.MOD_ID + ".untouchable")) {
                for (Monster monster : level.getEntitiesOfClass(Monster.class, entity.getBoundingBox().expandTowards(Constants.untouchableX, Constants.untouchableY, Constants.untouchableZ))) {
                    monster.knockback(EnchantedConfig.UNTOUCHABLE_KNOCK_BACK_STRENGTH.get(), sourceDirectEntity.getX() - entity.getX(), sourceDirectEntity.getZ() - entity.getZ());
                }
            }
        }

        static void handleUntouchableOnLivingTick(LivingEvent.LivingTickEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide) return;
            if (entity.getTags().contains(EarlySetupInitializer.MOD_ID + ".untouchable")) {
                ILivingEntity iLivingEntity = (ILivingEntity) entity;
                iLivingEntity.ep$setUntouchableTickCount(iLivingEntity.ep$getUntouchableTickCount() + 1);
            }
        }
    }
}
