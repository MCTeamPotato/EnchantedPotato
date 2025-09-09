package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.jetbrains.annotations.NotNull;

public class LawOfInertia extends BaseEnchantment {
    public static final String MARK = "InertiaKnockbackDamage";

    @Override
    public boolean isDisabled() {
        return DisableConfig.LAW_OF_INERTIA.get();
    }

    public static void onLivingTick(@NotNull EntityTickEvent.Pre event) {
        if (event.isCanceled()) return;
        if (event.getEntity() instanceof LivingEntity entity) {
            if (!(entity.level() instanceof ServerLevel)) return;
            float damage = entity.getPersistentData().getFloat(MARK);
            if (damage == 0.0F) return;

            Vec3 motion = entity.getDeltaMovement();
            if (Math.sqrt(motion.x * motion.x + motion.z * motion.z) < 0.01) {
                entity.getPersistentData().remove(MARK);
            }
        }
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    

    

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
