package me.kall.enchantedpotato.client.registry;

import me.kall.enchantedpotato.client.gui.*;
import me.kall.enchantedpotato.client.keybind.LeapfrogKey;
import me.kall.enchantedpotato.client.renderer.GurenNoYumiyaRenderer;
import me.kall.enchantedpotato.client.renderer.PressurizedCollapseRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.jetbrains.annotations.NotNull;

public class ClientRegistries {
    public static final AbstractOverlay[] TEXTS = new AbstractOverlay[6];

    public static void registerEvents(@NotNull IEventBus forgeBus) {
        ClientRegistry.registerKeyBinding(LeapfrogKey.LEAPFROG_KEY);

        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onRenderLevelStage);
        forgeBus.addListener(PressurizedCollapseRenderer.INSTANCE::onClientTick);

        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onClientTick);
        forgeBus.addListener(GurenNoYumiyaRenderer.INSTANCE::onRenderLevelStage);

        forgeBus.addListener(LeapfrogKey::onClientTick);
    }

    static {
        TEXTS[0] = new OceanHuedOverlay.Cooldown();
        TEXTS[1] = new OceanHuedOverlay.HealingAmount();
        TEXTS[2] = new OceanHuedOverlay.Counting();
        TEXTS[3] = new RunLikeHellOverlay();
        TEXTS[4] = new SpaceLeapfrogOverlay();
        TEXTS[5] = new UntouchableOverlay();
    }
}
