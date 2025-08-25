package me.kall.enchantedpotato.common.mixin.armorbreaking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.enchantment.weapon.ArmorBreaking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ExtendedLivingEntity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "getDamageAfterArmorAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterAbsorb(FFF)F"))
    private float modifyArmorValue(float damageAmount, float armorValue, float armorToughness, Operation<Float> operation) {
        if (this.level() instanceof ServerLevel){
            int enchantmentLevel = this.getPersistentData().getInt(ArmorBreaking.ARMOR_BREAKING_KEY);
            if (enchantmentLevel != 0) {
                if (armorValue != 0.0F) {

                    float reductionPercent = ArmorBreakingConfig.BASE_ARMOR_REDUCTION.get().floatValue() + ArmorBreakingConfig.GAINED_ARMOR_REDUCTION_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1);
                    float minReductionAmount = ArmorBreakingConfig.MINUS_ARMOR_REDUCTION_AMOUNT.get().floatValue();

                    if (reductionPercent > 1.0F) reductionPercent = 1.0F;

                    float armorReduction = armorValue * reductionPercent;
                    if (armorReduction < minReductionAmount) armorReduction = minReductionAmount;
                    if (armorValue > armorReduction) {
                        armorValue = armorValue - armorReduction;
                    } else {
                        armorValue = 0.0F;
                    }
                }
                if (armorToughness != 0.0F) {
                    float reductionPercent = ArmorBreakingConfig.BASE_ARMOR_TOUGHNESS_REDUCTION.get().floatValue() + ArmorBreakingConfig.GAINED_ARMOR_TOUGHNESS_REDUCTION_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1);
                    if (reductionPercent > 1.0F) reductionPercent = 1.0F;
                    armorToughness = armorToughness * (1.0F - reductionPercent);
                }
            }
        }
        return operation.call(damageAmount, armorValue, armorToughness);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (this.level() instanceof ServerLevel) {
            if (this.getPersistentData().getInt(ArmorBreaking.ARMOR_BREAKING_KEY) == 0) return;
            AttributeInstance duration = this.armorBreaking$getAttribute();
            duration.setBaseValue(duration.getBaseValue() - 1);
            if (duration.getBaseValue() <= 0.0D) this.getPersistentData().remove(ArmorBreaking.ARMOR_BREAKING_KEY);
        }
    }
}
