package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.MendingMirrorConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.data.MendingMirrorData;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MendingMirror extends BaseEnchantment {
    public static void recordBrokenItem(@NotNull Entity entity, @NotNull ItemStack stack) {
        if (!(entity.level() instanceof ServerLevel level)) return;
        MendingMirrorData data = MendingMirrorData.get(level);

        UUID playerId = entity.getUUID();
        ItemStack copy = stack.copy();

        BaseEnchantment.removeEnchantment(copy, BaseEnchantment.get(ModEnchantments.MENDING_MIRROR, entity.level()));

        int maxDurability = copy.getMaxDamage();
        copy.setDamageValue((int) ((double)maxDurability * (1.00D - MendingMirrorConfig.REMAINING_DURABILITY.get())));

        Tag itemNbt = copy.save(entity.registryAccess());
        CompoundTag tag = new CompoundTag();
        tag.put("ItemStackTag", itemNbt);
        data.addData(playerId, tag);
    }

    public static void onPlayerWakeUp(@NotNull PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        UUID playerId = player.getUUID();

        if (!(player.level() instanceof ServerLevel level)) return;
        MendingMirrorData data = MendingMirrorData.get(level);

        Set<CompoundTag> recovered = data.removeData(playerId);
        if (recovered != null && !recovered.isEmpty()) {
            recovered.forEach(compoundTag -> {
                ItemStack stack = ItemStack.parse(level.registryAccess(), Optional.ofNullable(compoundTag.get("ItemStackTag")).orElseThrow()).orElseThrow();
                if (!player.addItem(stack)) player.drop(stack, true);
            });
            if (MendingMirrorConfig.PLAY_SOUND.get()) player.playSound(SoundEvents.PLAYER_LEVELUP, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.MENDING_MIRROR.get();
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
