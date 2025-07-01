package me.kall.enchantedpotato.client.renderer.api;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;

public abstract class Renderer {
    public abstract int getColor();

    public abstract Object2ObjectArrayMap<BlockPos, RenderEffect> getActiveEffects();

    public abstract int getMaxAge();

    public abstract void spawnParticles(Vec3 center, double radius, int age);

    public void addEffect(@NotNull Vec3 position, double radius) {
        BlockPos pos = new BlockPos((int) position.x, (int) position.y, (int) position.z);
        getActiveEffects().put(pos, new RenderEffect(position, radius));
    }

    public void onClientTick(TickEvent.@NotNull ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            getActiveEffects().object2ObjectEntrySet().removeIf(entry -> {
                RenderEffect effect = entry.getValue();
                effect.bumpAge();
                spawnParticles(effect.getPosition(), effect.getRadius(), effect.getAge());
                return effect.getAge() >= getMaxAge();
            });
        }
    }

    public void onRenderLevelStage(@NotNull RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;

        PoseStack poseStack = event.getPoseStack();

        for (RenderEffect effect : getActiveEffects().values()) {
            renderCircle(poseStack, effect.getPosition(), effect.getRadius(), effect.getAge());
        }
    }

    public void renderCircle(@NotNull PoseStack poseStack, @NotNull Vec3 center, double radius, int age) {
        poseStack.pushPose();

        float alpha = 1.0f - (float) age / getMaxAge();
        int r = (getColor() >> 16) & 0xFF;
        int g = (getColor() >> 8) & 0xFF;
        int b = getColor() & 0xFF;
        int a = (int) (alpha * 255);

        VertexConsumer buffer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.LINES);
        Vec3 cameraPos = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition();
        poseStack.translate(center.x - cameraPos.x, center.y - cameraPos.y, center.z - cameraPos.z);

        double y = 0.1;
        int segments = 50;
        double angleIncrement = 2 * Math.PI / segments;

        for (int i = 0; i < segments; i++) {
            double angle1 = i * angleIncrement;
            double angle2 = (i + 1) * angleIncrement;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            double offset = age * 0.01;
            x1 += Math.sin(age * 0.2 + angle1) * offset;
            z1 += Math.cos(age * 0.2 + angle1) * offset;
            x2 += Math.sin(age * 0.2 + angle2) * offset;
            z2 += Math.cos(age * 0.2 + angle2) * offset;

            buffer.vertex(poseStack.last().pose(), (float) x1, (float) y, (float) z1).color(r, g, b, a).normal(0, 1, 0).endVertex();
            buffer.vertex(poseStack.last().pose(), (float) x2, (float) y, (float) z2).color(r, g, b, a).normal(0, 1, 0).endVertex();
        }

        poseStack.popPose();
    }

    public static class RenderEffect {
        final Vec3 position;
        final double radius;
        int age = 0;

        public RenderEffect(Vec3 position, double radius) {
            this.position = position;
            this.radius = radius;
        }

        public Vec3 getPosition() {
            return this.position;
        }

        public double getRadius() {
            return this.radius;
        }

        public int getAge() {
            return this.age;
        }

        public void bumpAge() {
            this.age++;
        }
    }
}
