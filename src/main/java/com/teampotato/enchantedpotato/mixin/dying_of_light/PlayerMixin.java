package com.teampotato.enchantedpotato.mixin.dying_of_light;

import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.ILivingEntity;
import com.teampotato.enchantedpotato.api.IPlayer;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IPlayer {
    private PlayerMixin(EntityType<? extends LivingEntity> arg, Level arg2) {
        super(arg, arg2);
    }

    @Shadow protected FoodData foodData;

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", ordinal = 2, shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        if (!EnchantedPotato.EnchantedUtils.hasPotatoEnchantmentEquipped((Player)(Object)this, EarlySetupInitializer.equipmentSlotConfig.dyingOfLight, DyingOfLight.PATH)) return;
        Level level = this.level();
        if (level.isNight() && ((ILivingEntity) this).ep$shouldTickDyingOfLight() && ThreadLocalRandom.current().nextDouble(0.00, 10.01) > 9.50) {
            this.foodData.setFoodLevel(this.foodData.getFoodLevel() - 1);
        }
    }
}
