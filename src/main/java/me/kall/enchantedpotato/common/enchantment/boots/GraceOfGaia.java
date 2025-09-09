package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.GraceOfGaiaConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class GraceOfGaia extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new GraceOfGaia();

    @Override
    public boolean isDisabled() {
        return DisableConfig.GRACE_OF_GAIA.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(10, 5);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(8, 4);
    }

    @Override
    public int anvilCost() {
        return 1;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.FEET;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.GRACE_OF_GAIA;
    }

    public static void onPlayerHurt(@NotNull LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel && !event.isCanceled()) {
            int level = getLevel(player.getItemBySlot(EquipmentSlot.FEET), serverLevel, ModEnchantments.GRACE_OF_GAIA);
            if (level == 0) return;
            int baseValidY = GraceOfGaiaConfig.BASE_VALID_Y.get();
            int gainedYPerLevel = GraceOfGaiaConfig.GAINED_Y_PER_LEVEL.get();
            int baseY = baseValidY + (level - 1) * gainedYPerLevel;
            int currentY = player.getOnPos().getY();
            if (currentY >= baseY) return;
            float absorbed = (float) (baseY - currentY) / 100F;
            if (absorbed > GraceOfGaiaConfig.getMaxDamageReduction()) absorbed = GraceOfGaiaConfig.getMaxDamageReduction();
            event.setAmount(event.getAmount() * (1.0F - absorbed));
        }
    }

}
