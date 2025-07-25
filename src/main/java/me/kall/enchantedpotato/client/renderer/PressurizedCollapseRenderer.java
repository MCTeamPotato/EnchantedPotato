package me.kall.enchantedpotato.client.renderer;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class PressurizedCollapseRenderer extends AbstractRenderer {
    private final Set<RenderEffect> activeEffects = new ObjectOpenHashSet<>();

    public static final PressurizedCollapseRenderer INSTANCE = new PressurizedCollapseRenderer();

    @Override
    public int getMaxAge() {
        return 50;
    }

    @Override public void spawnParticles(Vec3 center, double radius, int age) {}

    @Override
    public int getColor() {
        return 0x00FFFF;
    }

    @Override
    public Set<RenderEffect> getActiveEffects() {
        return this.activeEffects;
    }
}