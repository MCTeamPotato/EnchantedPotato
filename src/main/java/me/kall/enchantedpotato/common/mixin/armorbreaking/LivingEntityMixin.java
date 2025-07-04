package me.kall.enchantedpotato.common.mixin.armorbreaking;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.enchantment.ArmorBreaking;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ExtendedLivingEntity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D", at = @At("RETURN"), cancellable = true)
    private void onGetArmorValue(Attribute attribute, CallbackInfoReturnable<Double> cir) {
        if (!attribute.equals(Attributes.ARMOR)) return;
        int enchantmentLevel = armorBreaking$getLevel();
        if (enchantmentLevel == 0) return;
        float armorValue = cir.getReturnValue().floatValue();
        if (armorValue != 0.00F) {
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
            cir.setReturnValue((double) armorValue);
        }
    }

    @Inject(method = "getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D", at = @At("RETURN"), cancellable = true)
    private void onGetArmorToughnessValue(Attribute attribute, CallbackInfoReturnable<Double> cir) {
        if (!attribute.equals(Attributes.ARMOR_TOUGHNESS)) return;
        int enchantmentLevel = armorBreaking$getLevel();
        if (enchantmentLevel == 0) return;
        float armorToughness = cir.getReturnValue().floatValue();
        if (armorToughness != 0.0F) {
            float reductionPercent = ArmorBreakingConfig.BASE_ARMOR_TOUGHNESS_REDUCTION.get().floatValue() + ArmorBreakingConfig.GAINED_ARMOR_TOUGHNESS_REDUCTION_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1);
            if (reductionPercent > 1.0F) reductionPercent = 1.0F;
            armorToughness = armorToughness * (1.0F - reductionPercent);
        }
        cir.setReturnValue((double) armorToughness);
    }

    @Unique
    private int armorBreaking$getLevel() {
        String armorBreaking = null;
        for (String tag : this.getTags()) {
            if (tag.startsWith(ArmorBreaking.TAG)) {
                armorBreaking = tag;
                break;
            }
        }
        return armorBreaking != null ? Integer.parseInt(armorBreaking.split("g")[1]) : 0;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        Iterator<String> tags = this.getTags().iterator();
        while (tags.hasNext()) {
            String tag = tags.next();
            if (tag.startsWith(ArmorBreaking.TAG)) {
                AttributeInstance attributeInstance = this.armorBreaking$getAttribute();
                attributeInstance.setBaseValue(attributeInstance.getBaseValue() - 1);
                if (attributeInstance.getBaseValue() <= 0.0D) tags.remove();
                break;
            }
        }
    }
}
