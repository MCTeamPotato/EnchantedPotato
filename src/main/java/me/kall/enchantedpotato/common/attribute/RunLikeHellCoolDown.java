package me.kall.enchantedpotato.common.attribute;

import me.kall.enchantedpotato.common.registry.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RunLikeHellCoolDown extends RangedAttribute {
    public RunLikeHellCoolDown() {
        super("run_like_hell_cooldown", 0.00, 0.00, Double.MAX_VALUE);
        setSyncable(true);
    }

    @SubscribeEvent
    public static void registerAttribute(@NotNull EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.RUN_LIKE_HELL_COOLDOWN.get());
    }
}
