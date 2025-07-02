package me.kall.enchantedpotato.common.mixin.armorbreaking;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.enchantment.ArmorBreaking;
import me.kall.enchantedpotato.common.registry.ModAttributes;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Iterator;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ExtendedLivingEntity {
    @Shadow @Nullable public abstract AttributeInstance getAttribute(Attribute attribute);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "getDamageAfterArmorAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatRules;getDamageAfterAbsorb(FFF)F"))
    private float modifyArmorValue(float damageAmount, float armorValue, float armorToughness) {
        String armorBreaking = null;
        for (String tag : this.getTags()) {
            if (tag.startsWith(ArmorBreaking.TAG)) {
                armorBreaking = tag;
                break;
            }
        }
        if (armorBreaking != null) {
            int enchantmentLevel = Integer.parseInt(armorBreaking.split("g")[1]);
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
        return CombatRules.getDamageAfterAbsorb(damageAmount, armorValue, armorToughness);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        Iterator<String> tags = this.getTags().iterator();
        while (tags.hasNext()) {
            String tag = tags.next();
            if (tag.startsWith(ArmorBreaking.TAG)) {
                AttributeInstance attributeInstance = this.armorBreaking$getAttribute();
                attributeInstance.setBaseValue(attributeInstance.getValue() - 1);
                if (attributeInstance.getValue() <= 0.0D) tags.remove();
                break;
            }
        }
    }

    @Override
    public AttributeInstance armorBreaking$getAttribute() {
        return this.getAttribute(ModAttributes.ARMOR_BREAKING_DURATION.get());
    }
}
