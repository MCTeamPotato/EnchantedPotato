package me.kall.enchantedpotato.client.renderer;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class PressurizedCollapseRenderer extends AbstractRenderer {
    private final Object2ObjectArrayMap<BlockPos, RenderEffect> activeEffects = new Object2ObjectArrayMap<>();

    public static final PressurizedCollapseRenderer INSTANCE = new PressurizedCollapseRenderer();

    @Override
    public int getMaxAge() {
        return 50;
    }

    @Override
    public void spawnParticles(Vec3 center, double radius, int age) {

    }

    @Override
    public int getColor() {
        return 0x00FFFF;
    }

    @Override
    public Object2ObjectArrayMap<BlockPos, RenderEffect> getActiveEffects() {
        return this.activeEffects;
    }
}