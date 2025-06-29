package me.kall.enchantedpotato.common.mixin.log;

import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ServerLevel.class, priority = 1200)
public abstract class ServerLevelMixin {
    @Redirect(method = "addEntity", at = @At(value = "INVOKE", remap = false, target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"), require = 0)
    private void removeLog(Logger instance, String s, Object o) {}
}
