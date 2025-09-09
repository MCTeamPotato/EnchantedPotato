package me.kall.enchantedpotato.common.enchantment.digger;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import me.kall.enchantedpotato.common.config.UniteStonesOfAllConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class UniteStonesOfAll extends BaseEnchantment {
    private static final Set<Block> UNITED_STONES = new ObjectOpenHashSet<>();
    private static final Set<Block> QUARTZ_STONES = new ObjectOpenHashSet<>();

    @Override
    public boolean isDisabled() {
        return DisableConfig.UNITE_STONES_OF_ALL.get();
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

    public static boolean isTargetBlock(@NotNull BlockState state) {
        return UNITED_STONES.contains(state.getBlock());
    }

    public static void onServerStarted(@NotNull ServerStartedEvent event) {
        event.getServer().execute(() -> {
            for (String string : UniteStonesOfAllConfig.UNITED_STONES.get()) {
                ResourceLocation id = ResourceLocation.parse(string);
                Block block = BuiltInRegistries.BLOCK.get(id);
                UNITED_STONES.add(block);
            }
            for (String string : UniteStonesOfAllConfig.QUARTZ_STONES.get()) {
                ResourceLocation id = ResourceLocation.parse(string);
                Block block = BuiltInRegistries.BLOCK.get(id);
                QUARTZ_STONES.add(block);
            }
        });
    }

    public static void handleBlockDrops(Level level, BlockPos pos, BlockState state, boolean hasSilkTouch) {
        if (level instanceof ServerLevel serverLevel) {
            Item mainDrop = hasSilkTouch ? Items.STONE : Items.COBBLESTONE;

            ItemEntity drop = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(mainDrop, 1));
            level.addFreshEntity(drop);

            Block block = state.getBlock();
            if (QUARTZ_STONES.contains(block) && serverLevel.random.nextFloat() < UniteStonesOfAllConfig.QUARTZ_DROP_CHANCE.get().floatValue() && !hasSilkTouch) {
                ItemEntity quartzDrop = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.QUARTZ, 1));
                level.addFreshEntity(quartzDrop);
            }

            state.getBlock().popExperience(serverLevel, pos, 0);
        }
    }

    public static void onBlockBreak(@NotNull BlockDropsEvent event) {
        if (!event.isCanceled() && isTargetBlock(event.getState())) event.setDroppedExperience(0);
    }
}
