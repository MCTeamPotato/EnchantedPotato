package me.kall.enchantedpotato.common.mixin.lotusinwater;

import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IEntityExtension {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isPushedByFluid(@NotNull FluidType type) {
        if (BaseEnchantment.getLevel(this.getItemBySlot(EquipmentSlot.FEET), this.level(), ModEnchantments.LOTUS_IN_WATER) != 0) {
            return false;
        }
        return super.isPushedByFluid(type);
    }

    @Inject(method = "isPushedByFluid", at = @At("HEAD"), cancellable = true)
    private void onCheckIsPushedByFluid(CallbackInfoReturnable<Boolean> cir) {
        if (BaseEnchantment.getLevel(this.getItemBySlot(EquipmentSlot.FEET), this.level(), ModEnchantments.LOTUS_IN_WATER) != 0) cir.setReturnValue(false);
    }
}
