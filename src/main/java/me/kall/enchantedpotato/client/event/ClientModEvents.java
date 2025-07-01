package me.kall.enchantedpotato.client.event;

import me.kall.enchantedpotato.client.gui.RunLikeHellOverlay;
import me.kall.enchantedpotato.client.gui.UntouchableOverlay;
import me.kall.enchantedpotato.client.renderer.GurenNoYumiyaRenderer;
import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

public class ClientModEvents {
    public static void registerGui(@NotNull RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "run_like_hell_cooldown", new RunLikeHellOverlay());
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "untouchable_cooldown", new UntouchableOverlay());
    }

    public static void register(@NotNull IEventBus modBus, @NotNull IEventBus forgeBus) {
        modBus.addListener(ClientModEvents::registerGui);

        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onRenderLevelStage);
        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onClientTick);

        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onClientTick);
        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onRenderLevelStage);
    }
}
