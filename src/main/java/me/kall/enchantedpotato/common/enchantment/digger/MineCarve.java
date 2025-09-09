package me.kall.enchantedpotato.common.enchantment.digger;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.config.MineCarveConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class MineCarve extends BaseEnchantment {
    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel serverLevel) {
            int enchantmentLevel = getLevelInHands(ModEnchantments.MINE_CARVE, player, serverLevel);
            if (enchantmentLevel > 0) {
                AttributeInstance armor = event.getEntity().getAttribute(Attributes.ARMOR);
                if (armor == null) return;

                double amount = MineCarveConfig.BASE_ARMOR_REDUCTION.get() + MineCarveConfig.GAINED_ARMOR_REDUCTION_PER_LEVEL.get() * (double) (enchantmentLevel - 1);

                AttributeModifier attributeModifier = new AttributeModifier(EnchantedPotato.loc("mine_carve_modifier"), -amount, AttributeModifier.Operation.ADD_VALUE);

                for (AttributeModifier modifier : armor.getModifiers()) {
                    if (modifier.id().equals(attributeModifier.id())) armor.removeModifier(modifier);
                }

                armor.addPermanentModifier(attributeModifier);
            }
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.MINE_CARVE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.MINING_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 4;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(25, 15);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(20, 12);
    }

    @Override
    public int anvilCost() {
        return 4;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
