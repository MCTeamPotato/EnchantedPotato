package com.teampotato.enchantedpotato.mixin;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILevel;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.DyingOfLight;
import com.teampotato.enchantedpotato.util.Constants;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("resource")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntity {
    @Shadow public abstract void heal(float healAmount);

    @Shadow public abstract @NotNull Iterable<ItemStack> getArmorSlots();

    @Unique private static final ThreadLocalRandom ep$random = ThreadLocalRandom.current();


    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private void onEat(Level level, ItemStack food, CallbackInfoReturnable<ItemStack> cir) {
        if (!EarlySetupInitializer.functionConfig.dyingOfLight || !level.isNight()) return;
        FoodProperties foodProperties = food.getFoodProperties((LivingEntity) (Object) this);
        if (!Utils.hasPotatoEnchantmentEquipped((LivingEntity) (Object) this, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH) || foodProperties == null || foodProperties.isMeat()) return;
        cir.setReturnValue(food);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        AABB boundingBox = this.getBoundingBox();
        if (EarlySetupInitializer.functionConfig.runLikeHell) {
            if (this.removeTag(EarlySetupInitializer.MOD_ID + ".tickCountTrigger")) this.ep$setShouldTrackRunLikeHell(true);
            if (this.ep$getShouldTrackRunLikeHell()) {
                this.ep$setRunLikeHellTickCount(this.ep$getRunLikeHellTickCount() + 1);
                if (this.ep$getRunLikeHellTickCount() == DetailsConfig.RUN_LIKE_HELL_INVISIBILITY_DURATION.get()) {
                    this.removeTag(EarlySetupInitializer.MOD_ID + ".blinder");
                    this.ep$setRunLikeHellTickCount(0);
                    this.ep$setShouldTrackRunLikeHell(false);
                }
            }
        }

        if (EarlySetupInitializer.functionConfig.blessingOfTheNature && this.getTags().contains(EarlySetupInitializer.MOD_ID + ".natureBlessed")) {
            this.ep$setNatureBlessedTickCount(this.ep$getNatureBlessedTickCount() + 1);
            if (this.ep$shouldTickNatureBlessed()) {
                this.heal(DetailsConfig.BLESSING_OF_THE_NATURE_HEALING_AMOUNT.get().floatValue());
                for (Monster monster : this.level().getEntitiesOfClass(Monster.class, boundingBox.expandTowards(Constants.blessingOfTheNatureX, Constants.blessingOfTheNatureY, Constants.blessingOfTheNatureZ))) {
                    double d1 = monster.getX() - this.getX();
                    double d0 = monster.getZ() - this.getZ();
                    while (d1 * d1 + d0 * d0 < 1.0E-4) {
                        d1 = (ep$random.nextDouble() - ep$random.nextDouble()) * 0.01;
                        d0 = (ep$random.nextDouble() - ep$random.nextDouble()) * 0.01;
                    }
                    monster.knockback(DetailsConfig.BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_STRENGTH.get(), d1, d0);
                    monster.hurt(monster.level().damageSources().generic(), ep$random.nextFloat(DetailsConfig.BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_DAMAGE.get().floatValue(), DetailsConfig.BLESSING_OF_THE_NATURE_MAX_KNOCK_BACK_DAMAGE.get().floatValue()));
                }
            }
        }

        if (EarlySetupInitializer.functionConfig.dyingOfLight && this.getTags().contains(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip")) {
            Level level = this.level();
            if (level.isNight() && level instanceof ServerLevel serverLevel) {
                this.ep$setDyingOfLightTickCount(this.ep$getDyingOfLightTickCount() + 1);
                if (ep$shouldTickDyingOfLight()) {
                    AABB box = boundingBox.expandTowards(Constants.dyingOfLightX, Constants.dyingOfLightY, Constants.dyingOfLightZ);
                    for (AbstractVillager villager : serverLevel.getEntitiesOfClass(AbstractVillager.class, box)) villager.setHealth(Math.max(villager.getHealth() - DetailsConfig.DYING_OF_LIGHT_VILLAGER_HURT_DAMAGE.get().floatValue(), 0.0F));
                    for (NeutralMob entity : ((ILevel) serverLevel).ep$getNeutralMobs(box)) {
                        if (entity instanceof Mob mob) mob.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, Optional.of((LivingEntity) (Object) this));
                        entity.setTarget((LivingEntity) (Object) this);
                    }
                    if (!this.hasEffect(MobEffects.NIGHT_VISION)) this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));
                    this.ep$setDyingOfLightTickCount(0);
                }
            }
        }
    }

    public LivingEntityMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }
    @Shadow public abstract boolean addEffect(MobEffectInstance effectInstance);
    @Shadow public abstract boolean hasEffect(MobEffect effect);
}
