package me.kall.enchantedpotato.common.mixin.unitestonesofall;

import me.kall.enchantedpotato.common.enchantment.digger.UniteStonesOfAll;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "playerDestroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V", shift = At.Shift.AFTER), cancellable = true)
    private void onPlayerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        if (UniteStonesOfAll.isTargetBlock(state)) {
            boolean hasSilkTouch = stack.getEnchantmentLevel(Enchantments.SILK_TOUCH) != 0;
            UniteStonesOfAll.handleBlockDrops(level, pos, state, hasSilkTouch);
            ci.cancel();
        }
    }
}
