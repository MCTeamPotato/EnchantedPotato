package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.BoneSuckalaka;
import com.teampotato.enchantedpotato.util.Constants;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class BoneSuckalakaEvents {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.@NotNull PlayerTickEvent event) {
        Player player = event.player;
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.isShiftKeyDown() && serverPlayer.isUsingItem() && serverPlayer.getUseItem().getItem() instanceof ShieldItem && Utils.hasPotatoEnchantmentEquipped(serverPlayer, EarlySetupInitializer.equipmentSlotConfig.boneSuckalaka, BoneSuckalaka.PATH)) {
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            for (AbstractSkeleton skeleton : player.level().getEntitiesOfClass(AbstractSkeleton.class, new AABB(x - Constants.boneSuckalakaX, y - Constants.boneSuckalakaY, z - Constants.boneSuckalakaZ, x + Constants.boneSuckalakaX, y + Constants.boneSuckalakaY, z + Constants.boneSuckalakaZ))) {
                skeleton.setDeltaMovement(player.position());
            }
        }
    }
}
