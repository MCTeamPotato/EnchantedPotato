package me.kall.enchantedpotato.common.attribute;

import me.kall.enchantedpotato.common.registry.ModAttributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorBreakingDuration extends RangedAttribute {
    public ArmorBreakingDuration() {
        super("armor_breaking_duration", 0.00, 0.00, Double.MAX_VALUE);
    }

    @SubscribeEvent
    public static void registerAttribute(@NotNull EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType -> event.add(entityType, ModAttributes.ARMOR_BREAKING_DURATION.get()));
    }
}
