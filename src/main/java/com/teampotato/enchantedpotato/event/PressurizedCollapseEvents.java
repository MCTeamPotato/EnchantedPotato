package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.api.IPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class PressurizedCollapseEvents {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.@NotNull RightClickItem event) {
        Player player = event.getEntity();
        if (player.level().isClientSide || !(event.getItemStack().getItem() instanceof BowItem)) return;

        if (((IPlayer)player).ep$getStartUsingBowTime() != 0) {
            ((IPlayer)player).ep$setRealChargeTime(System.currentTimeMillis() - ((IPlayer)player).ep$getStartUsingBowTime());
        }

        ((IPlayer)player).ep$setStartUsingBowTime(System.currentTimeMillis());
    }
}
