package me.kall.enchantedpotato.common.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.kall.enchantedpotato.client.gui.AbstractOverlay;
import me.kall.enchantedpotato.client.registry.ClientRegistries;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeIngameGui.class)
public abstract class ForgeIngameGuiMixin {
    @Inject(method = "render", at = @At("RETURN"))
    private void renderText(PoseStack stack, float partialTicks, CallbackInfo ci) {
        for (AbstractOverlay text : ClientRegistries.TEXTS) {
            text.render(stack);
        }
    }
}
