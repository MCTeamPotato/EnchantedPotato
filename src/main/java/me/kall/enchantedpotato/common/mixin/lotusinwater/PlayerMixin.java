package me.kall.enchantedpotato.common.mixin.lotusinwater;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "isPushedByFluid", at = @At("HEAD"), cancellable = true)
    private void onCheckIsPushedByFluid(CallbackInfoReturnable<Boolean> cir) {
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.LOTUS_IN_WATER.get(), this.getItemBySlot(EquipmentSlot.FEET)) != 0) cir.setReturnValue(false);
    }
}
