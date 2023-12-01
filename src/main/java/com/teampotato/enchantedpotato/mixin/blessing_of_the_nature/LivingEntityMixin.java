package com.teampotato.enchantedpotato.mixin.blessing_of_the_nature;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntity {
    @Shadow public abstract void heal(float healAmount);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        if (this.getTags().contains(EarlySetupInitializer.MOD_ID + ".natureBlessed")) {
            this.ep$setNatureBlessedTickCount(this.ep$getNatureBlessedTickCount() + 1);
            if (this.ep$shouldTickNatureBlessed()) {
                this.heal(EnchantedPotato.EnchantedConfig.BLESSING_OF_THE_NATURE_HEALING_AMOUNT.get().floatValue());
                for (Monster monster : this.level().getEntitiesOfClass(Monster.class, this.getBoundingBox().expandTowards(EnchantedPotato.Constants.blessingOfTheNatureX, EnchantedPotato.Constants.blessingOfTheNatureY, EnchantedPotato.Constants.blessingOfTheNatureZ))) {
                    double d1 = monster.getX() - this.getX();
                    double d0 = monster.getZ() - this.getZ();
                    while (d1 * d1 + d0 * d0 < 1.0E-4) {
                        d1 = (EnchantedPotato.EnchantedUtils.getRandom().nextDouble() - EnchantedPotato.EnchantedUtils.getRandom().nextDouble()) * 0.01;
                        d0 = (EnchantedPotato.EnchantedUtils.getRandom().nextDouble() - EnchantedPotato.EnchantedUtils.getRandom().nextDouble()) * 0.01;
                    }
                    monster.knockback(EnchantedPotato.EnchantedConfig.BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_STRENGTH.get(), d1, d0);
                    monster.hurt(monster.level().damageSources().generic(), EnchantedPotato.EnchantedUtils.getRandom().nextFloat(EnchantedPotato.EnchantedConfig.BLESSING_OF_THE_NATURE_MIN_KNOCK_BACK_DAMAGE.get().floatValue(), EnchantedPotato.EnchantedConfig.BLESSING_OF_THE_NATURE_MAX_KNOCK_BACK_DAMAGE.get().floatValue()));
                }
            }
        }
    }
}
