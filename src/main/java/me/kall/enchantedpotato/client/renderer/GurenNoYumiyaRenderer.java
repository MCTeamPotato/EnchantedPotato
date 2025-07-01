package me.kall.enchantedpotato.client.renderer;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GurenNoYumiyaRenderer extends AbstractRenderer {
    private final Object2ObjectArrayMap<BlockPos, RenderEffect> activeEffects = new Object2ObjectArrayMap<>();

    public static final GurenNoYumiyaRenderer INSTANCE = new GurenNoYumiyaRenderer();

    @Override
    public int getColor() {
        return 0xFF0000;
    }

    @Override
    public Object2ObjectArrayMap<BlockPos, RenderEffect> getActiveEffects() {
        return this.activeEffects;
    }

    @Override
    public int getMaxAge() {
        return 50;
    }

    @Override
    public void spawnParticles(Vec3 center, double radius, int age) {
        if (age % 5 != 0) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        RandomSource random = level.random;
        int particles = 3 + random.nextInt(3);

        for (int i = 0; i < particles; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = radius * (0.8 + random.nextDouble() * 0.2);

            double x = center.x + Math.cos(angle) * distance;
            double z = center.z + Math.sin(angle) * distance;
            double y = center.y + 0.1;

            double dx = (center.x - x) * 0.02;
            double dy = 0.01 + random.nextDouble() * 0.02;
            double dz = (center.z - z) * 0.02;

            level.addParticle(ParticleTypes.FLAME, x, y, z, dx, dy, dz);
        }
    }
}