package me.kall.enchantedpotato.client.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.client.gui.OceanHuedOverlay;
import me.kall.enchantedpotato.client.gui.RunLikeHellOverlay;
import me.kall.enchantedpotato.client.gui.SpaceLeapfrogOverlay;
import me.kall.enchantedpotato.client.gui.UntouchableOverlay;
import me.kall.enchantedpotato.client.keybind.LeapfrogKey;
import me.kall.enchantedpotato.client.renderer.GurenNoYumiyaRenderer;
import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.jetbrains.annotations.NotNull;

public class ClientModEvents {
    public static void registerGui(@NotNull RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("run_like_hell_cooldown"), new RunLikeHellOverlay());
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("untouchable_cooldown"), new UntouchableOverlay());
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("ocean_hued_counting"), new OceanHuedOverlay.Counting());
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("ocean_hued_cooldown"), new OceanHuedOverlay.Cooldown());
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("ocean_hued_healing_amount"), new OceanHuedOverlay.HealingAmount());
        event.registerAbove(VanillaGuiLayers.HOTBAR, EnchantedPotato.loc("space_leapfrog_cooldown"), new SpaceLeapfrogOverlay());
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

        forgeBus.addListener(LeapfrogKey::onClientTick);
    }
}
