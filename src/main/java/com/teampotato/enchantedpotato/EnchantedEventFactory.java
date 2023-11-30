package com.teampotato.enchantedpotato;

import com.teampotato.enchantedpotato.api.*;
import com.teampotato.enchantedpotato.effect.CounterattackMoment;
import com.teampotato.enchantedpotato.enchantment.armor.chest.BlessingOfTheNature;
import com.teampotato.enchantedpotato.enchantment.armor.chest.HuaJin;
import com.teampotato.enchantedpotato.enchantment.armor.feet.BlackParade;
import com.teampotato.enchantedpotato.enchantment.armor.feet.GaiaBlessing;
import com.teampotato.enchantedpotato.enchantment.armor.feet.RippleOfDeath;
import com.teampotato.enchantedpotato.enchantment.armor.feet.RunLikeHell;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import com.teampotato.enchantedpotato.enchantment.armor.head.FlameCross;
import com.teampotato.enchantedpotato.enchantment.armor.legs.ErrorSpore;
import com.teampotato.enchantedpotato.enchantment.armor.legs.ThisIsLeopard;
import com.teampotato.enchantedpotato.enchantment.armor.legs.Untouchable;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.BoneSuckalaka;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.ShieldBladeCommendation;
import com.teampotato.enchantedpotato.enchantment.digger.MarkFromTheBeneath;
import com.teampotato.enchantedpotato.enchantment.digger.MineCarve;
import com.teampotato.enchantedpotato.enchantment.weapon.ArmorBreaking;
import com.teampotato.enchantedpotato.enchantment.weapon.LoRATrainer;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.util.Constants;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EnchantedEventFactory {
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
        if (event.getSource().getDirectEntity() instanceof LivingEntity sourceDirectEntity && Utils.hasPotatoEnchantmentEquipped(sourceDirectEntity, EarlySetupInitializer.equipmentSlotConfig.armorBreaking, ArmorBreaking.PATH)) {
            if (!armorValueAttribute.hasModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER) && armorValueAttribute.getValue() * (1.00 - EnchantedPotato.ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER.get()) <= EnchantedPotato.ARMOR_BREAKING_MAX_ARMOR_VALUE_REDUCTION.get().doubleValue()) armorValueAttribute.addTransientModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER);
            if (!armorToughnessAttribute.hasModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER)) armorToughnessAttribute.addTransientModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER);
        }
    }

    static void handleBlackParade(LivingDeathEvent event) {
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity directSourceEntity) {
            MobEffectInstance effect = directSourceEntity.getEffect(MobEffects.MOVEMENT_SPEED);
            if (Utils.hasPotatoEnchantmentEquipped(directSourceEntity, EarlySetupInitializer.equipmentSlotConfig.blackParade, BlackParade.PATH)) {
                if (effect != null) {
                    directSourceEntity.removeEffect(effect.getEffect());
                    directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedPotato.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get() + effect.getDuration(), EnchantedPotato.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                } else {
                    directSourceEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedPotato.BLACK_PARADE_MOVEMENT_SPEED_DURATION.get(), EnchantedPotato.BLACK_PARADE_MOVEMENT_SPEED_AMPLIFIER.get()));
                }
            }
        }
    }

    static void handleBoneSuckalaka(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.isShiftKeyDown() && serverPlayer.isUsingItem() && serverPlayer.getUseItem().getItem() instanceof ShieldItem && Utils.hasPotatoEnchantmentEquipped(serverPlayer, EarlySetupInitializer.equipmentSlotConfig.boneSuckalaka, BoneSuckalaka.PATH)) {
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
        if (Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH) && entity.level().isNight() && entity instanceof Monster) {
            LivingEntity lastHurtMob = target.getLastHurtMob();
            if (lastHurtMob != null && lastHurtMob.getUUID().equals(entity.getUUID())) return;
            event.setCanceled(true);
        }
    }

    static void handleEnderEnderOnLivingHurt(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (event.isCanceled() || attacked.level().isClientSide || !((EnderTarget)attacked.getType()).isEnderTarget() || !(damageSource.getDirectEntity() instanceof LivingEntity entity)) return;
        int level = Utils.getPotatoEnchantmentLevel(entity, EnchantedPotato.EnchantedRegistries.ENDER_ENDER.get(), EarlySetupInitializer.equipmentSlotConfig.enderEnder);
        if (level > 0) {
            if (attacked.getTags().stream().noneMatch(tag -> tag.startsWith(EarlySetupInitializer.MOD_ID + ".end."))) {
                attacked.addTag(EarlySetupInitializer.MOD_ID + ".end." + level);
            }
            event.setAmount(event.getAmount() + (float) level * EnchantedPotato.ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL.get().floatValue());
        }
    }

    static void handleEnderEnderOnCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE) {
                ((EnderTarget)entityType).setIsEnderTarget(EnchantedPotato.ENDER_ENDER_VALID_TARGET.get().contains(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString()));
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
        event.setAmount(event.getAmount() * (1.0F - ErrorSpore.ERROR_SPORE_LEVEL_MAP.get(Utils.getPotatoEnchantmentLevel(entity, EnchantedPotato.EnchantedRegistries.ERROR_SPORE.get(), EarlySetupInitializer.equipmentSlotConfig.errorSpore))));
        if (source.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(EnchantedPotato.EnchantedRegistries.ERROR_SPORE.get(), sourceDirectEntity);
            int creeperSpawnChecker = EnchantedPotato.ERROR_SPORE_CREEPER_SPAWN_CHECKER.get();
            if (source.getSourcePosition() != null && enchantmentLevel < EnchantedPotato.EnchantedRegistries.ERROR_SPORE.get().getMaxLevel() && enchantmentLevel > EnchantedPotato.EnchantedRegistries.ERROR_SPORE.get().getMaxLevel() / creeperSpawnChecker * (creeperSpawnChecker - 1)) {
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
        if (sourceDirectEntity instanceof LivingEntity source && sourceEntity != null && Utils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH)) {
            if (EnchantedPotato.FLAME_CROSS_IGNITE_OWNER_ON_ATTACKING.get()) ((IEntity)source).ep$setTicksOnFire(EnchantedPotato.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_OWNER.get());
            if (sourceDirectEntity.getStringUUID().equals(sourceEntity.getStringUUID())) {
                if (Utils.getRandom().nextDouble(0.00, 1.00) < EnchantedPotato.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_MELEE_ATTACKING.get()) {
                    ((IEntity)entity).ep$setTicksOnFire(EnchantedPotato.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                }
            } else {
                if (Utils.getRandom().nextDouble(0.00, 1.00) < EnchantedPotato.FLAME_CROSS_IGNITE_TARGET_CHANCE_ON_IGNITED_OWNER_RANGED_ATTACKING.get()) {
                    ((IEntity)entity).ep$setTicksOnFire(EnchantedPotato.FLAME_CROSS_FIRE_DURATION_TICKS_ON_IGNITING_TARGET.get());
                }
            }
            if (entity.isOnFire()) event.setAmount(event.getAmount() * EnchantedPotato.FLAME_CROSS_DAMAGE_MULTIPLIER_ON_IGNITED_TARGET.get().floatValue());
        }
        if (Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.flameCross, FlameCross.PATH) && damageSource.is(DamageTypeTags.IS_FIRE)) {
            event.setAmount(event.getAmount() * EnchantedPotato.FLAME_CROSS_FIRE_DAMAGE_MULTIPLIER_ON_IGNITED_OWNER.get().floatValue());
        }
    }

    static void handleGaiaBlessingOnLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.isCanceled()) return;
        LivingEntity entity = event.getEntity();
        double y = entity.getY();
        AttributeInstance attributeInstance = entity.getAttribute(Attributes.ARMOR);
        if (attributeInstance == null || y >= 50 || entity.level().isClientSide || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.gaiaBlessing, GaiaBlessing.PATH)) return;
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
        if (entity.level().isClientSide() || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.huaJin, HuaJin.PATH)) return;
        float amount = event.getAmount();
        float health = entity.getHealth();
        float huaJin = EnchantedPotato.HUA_JIN_TRIGGER_DAMAGE_PERCENTAGE.get().floatValue();
        if (amount > health * huaJin) {
            event.setAmount(amount - (amount - health * huaJin) * EnchantedPotato.HUA_JIN_DAMAGE_REDUCTION_PERCENTAGE.get().floatValue());
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, EnchantedPotato.HUA_JIN_STRENGTH_EFFECT_DURATION.get(), EnchantedPotato.HUA_JIN_STRENGTH_EFFECT_AMPLIFIER.get()));
        }
    }

    static void handleLoRATrainer(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof ServerPlayer player && !entity.level().isClientSide()) {
            if (player.getStats().getValue(Stats.ENTITY_KILLED.get(entity.getType())) < EnchantedPotato.LORA_TRAINER_KILL_COUNTS_REQUIREMENT.get()) return;
            if (Utils.hasPotatoEnchantmentEquipped(player, EarlySetupInitializer.equipmentSlotConfig.loraTrainer, LoRATrainer.PATH)) {
                event.setAmount((EnchantedPotato.LORA_TRAINER_DAMAGE_BONUS.get().floatValue() + 1.0F) * event.getAmount());
            }
        }
    }

    static void handleMarkFromTheBeneath(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        AttributeInstance digSpeedAttribute = entity.getAttribute(Attributes.ATTACK_SPEED);
        if (entity.getY() > EnchantedPotato.MARK_FROM_THE_BENEATH_VALID_Y.get() || event.isCanceled() || digSpeedAttribute == null || digSpeedAttribute.hasModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER) || !(entity.getUseItem().getItem() instanceof PickaxeItem) || !Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.markFromTheBeneath, MarkFromTheBeneath.PATH)) {
            if (digSpeedAttribute != null) digSpeedAttribute.removeModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER_UUID);
            return;
        }
        digSpeedAttribute.addTransientModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER);
    }

    static void handleMineCarve(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity source && entity.getArmorValue() >= EnchantedPotato.MINE_CARVE_VALID_ARMOR_VALUE.get() && Utils.hasPotatoEnchantmentEquipped(source, EarlySetupInitializer.equipmentSlotConfig.mineCarve, MineCarve.PATH)) {
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
            if (iLivingEntity.ep$getOceanHuedTimerTickCount() >= EnchantedPotato.OCEAN_HUED_TIMER_TICKS.get()) {
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
                float maxDamage = EnchantedPotato.OCEAN_HUED_MAX_DAMAGE_ADDITION.get().floatValue();
                float amount = iLivingEntity.ep$getOceanHuedHealingAmount() * EnchantedPotato.OCEAN_HUED_ADDITIONAL_DAMAGE_PERCENT_BASED_ON_HEALING_AMOUNT.get().floatValue();
                if (amount >= maxDamage) amount = maxDamage;
                double x = attacked.getX();
                double y = attacked.getY();
                double z = attacked.getZ();
                for (LivingEntity target : attacked.level().getEntitiesOfClass(LivingEntity.class, new AABB(x - Constants.oceanHuedX, y - Constants.oceanHuedY, z - Constants.oceanHuedZ, x + Constants.oceanHuedX, y + Constants.oceanHuedY, z + Constants.oceanHuedZ))) {
                    target.hurt(target.level().damageSources().inWall(), amount);
                }
                iLivingEntity.ep$setIsOceanHuedAttackReady(false);
                iLivingEntity.ep$setOceanHuedCoolDownTickCount(EnchantedPotato.OCEAN_HUED_COOL_DOWN_TICKS.get());
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
            if (Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.rippleOfDeath, RippleOfDeath.PATH)) {
                for (Monster monster : level.getEntitiesOfClass(Monster.class, new AABB(x - Constants.rippleOfDeathX, y - Constants.rippleOfDeathY, z - Constants.rippleOfDeathZ, x + Constants.rippleOfDeathX, y + Constants.rippleOfDeathY, z + Constants.rippleOfDeathZ))) {
                    monster.hurt(monster.level().damageSources().generic(), EnchantedPotato.RIPPLE_OF_DEATH_EXTRA_DAMAGE.get().floatValue());
                }
            }
        }
    }

    static void handleRunLikeHell(LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        if (attacked.level().isClientSide) return;
        if (!event.isCanceled() && Utils.hasPotatoEnchantmentEquipped(attacked, EarlySetupInitializer.equipmentSlotConfig.runLikeHell, RunLikeHell.PATH) && ((Float) attacked.getHealth()).doubleValue() - ((Float) event.getAmount()).doubleValue() < ((Float) attacked.getMaxHealth()).doubleValue() * EnchantedPotato.RUN_LIKE_HELL_VALID_MIN_HEALTH.get()) {
            attacked.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EnchantedPotato.RUN_LIKE_HELL_INVISIBILITY_DURATION.get()));
            attacked.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EnchantedPotato.RUN_LIKE_HELL_MOVEMENT_SPEED_DURATION.get(), EnchantedPotato.RUN_LIKE_HELL_MOVEMENT_SPEED_AMPLIFIER.get()));
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
        if (!event.isCanceled() && !entity.level().isClientSide && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.shieldBladeCommendation, ShieldBladeCommendation.PATH)) {
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
        if (source.getDirectEntity() instanceof LivingEntity entity && entity.getY() > 95.00 && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH)) {
            event.setAmount(event.getAmount() * EnchantedPotato.THIS_IS_LEOPARD_DAMAGE_MULTIPLIER.get());
        }
    }

    static void handleThisIsLeopardOnLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity target = event.getOriginalTarget();
        if (target.getY() > EnchantedPotato.THIS_IS_LEOPARD_VALID_HEIGHT.get() && Utils.hasPotatoEnchantmentEquipped(target, EarlySetupInitializer.equipmentSlotConfig.thisIsLeopard, ThisIsLeopard.PATH) && Utils.getRandom().nextDouble(0.00,1.00) < EnchantedPotato.THIS_IS_LEOPARD_TARGET_MISS_CHANCE.get()) {
            event.setCanceled(true);
        }
    }

    static void handleUntouchableOnLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        DamageSource damageSource = event.getSource();
        if (!level.isClientSide && ((ILivingEntity)entity).ep$ShouldTickUntouchable() && damageSource.getDirectEntity() instanceof LivingEntity sourceDirectEntity && entity.getTags().contains(EarlySetupInitializer.MOD_ID + ".untouchable")) {
            for (Monster monster : level.getEntitiesOfClass(Monster.class, entity.getBoundingBox().expandTowards(Constants.untouchableX, Constants.untouchableY, Constants.untouchableZ))) {
                monster.knockback(EnchantedPotato.UNTOUCHABLE_KNOCK_BACK_STRENGTH.get(), sourceDirectEntity.getX() - entity.getX(), sourceDirectEntity.getZ() - entity.getZ());
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

    static void setupEvents(final IEventBus modBus) {
        final IEventBus forgeBus = NeoForge.EVENT_BUS;
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
}
