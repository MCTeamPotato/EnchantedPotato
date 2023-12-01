package com.teampotato.enchantedpotato.mixin.dying_of_light;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILevel;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private void onEat(@NotNull Level level, ItemStack food, CallbackInfoReturnable<ItemStack> cir) {
        if (!level.isNight()) return;
        FoodProperties foodProperties = food.getFoodProperties((LivingEntity) (Object) this);
        if (!EnchantedPotato.EnchantedUtils.hasPotatoEnchantmentEquipped((LivingEntity) (Object) this, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH) || foodProperties == null || foodProperties.isMeat()) return;
        cir.setReturnValue(food);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        if (this.getTags().contains(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip")) {
            Level level = this.level();
            if (level.isNight() && level instanceof ServerLevel serverLevel) {
                this.ep$setDyingOfLightTickCount(this.ep$getDyingOfLightTickCount() + 1);
                if (ep$shouldTickDyingOfLight()) {
                    AABB box = this.getBoundingBox().expandTowards(EnchantedPotato.Constants.dyingOfLightX, EnchantedPotato.Constants.dyingOfLightY, EnchantedPotato.Constants.dyingOfLightZ);
                    for (AbstractVillager villager : serverLevel.getEntitiesOfClass(AbstractVillager.class, box)) villager.setHealth(Math.max(villager.getHealth() - EnchantedPotato.EnchantedConfig.DYING_OF_LIGHT_VILLAGER_HURT_DAMAGE.get().floatValue(), 0.0F));
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

    @Shadow
    public abstract boolean addEffect(MobEffectInstance effectInstance);
    @Shadow public abstract boolean hasEffect(MobEffect effect);
}
