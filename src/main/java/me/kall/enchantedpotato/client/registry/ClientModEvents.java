package me.kall.enchantedpotato.client.registry;

import me.kall.enchantedpotato.client.gui.OceanHuedOverlay;
import me.kall.enchantedpotato.client.gui.RunLikeHellOverlay;
import me.kall.enchantedpotato.client.gui.UntouchableOverlay;
import me.kall.enchantedpotato.client.keybind.LeapfrogKey;
import me.kall.enchantedpotato.client.renderer.GurenNoYumiyaRenderer;
import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

public class ClientModEvents {
    public static void registerGui(@NotNull RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "run_like_hell_cooldown", new RunLikeHellOverlay());
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "untouchable_cooldown", new UntouchableOverlay());
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "ocean_hued_counting", new OceanHuedOverlay.Counting());
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "ocean_hued_cooldown", new OceanHuedOverlay.Cooldown());
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "ocean_hued_healing_amount", new OceanHuedOverlay.HealingAmount());
    }

    public static void registerKey(@NotNull RegisterKeyMappingsEvent event) {
        event.register(LeapfrogKey.LEAPFROG_KEY);
    }

    public static void register(@NotNull IEventBus modBus, @NotNull IEventBus forgeBus) {
        modBus.addListener(ClientModEvents::registerGui);
        modBus.addListener(ClientModEvents::registerKey);

        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onRenderLevelStage);
        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onClientTick);

        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onClientTick);
        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onRenderLevelStage);
    }
}
