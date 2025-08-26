package me.kall.enchantedpotato.common.enchantment.digger;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import me.kall.enchantedpotato.common.config.UniteStonesOfAllConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class UniteStonesOfAll extends BaseEnchantment {
    private static final Set<Block> UNITED_STONES = new ObjectOpenHashSet<>();
    private static final Set<Block> QUARTZ_STONES = new ObjectOpenHashSet<>();

    public UniteStonesOfAll() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.UNITE_STONES_OF_ALL.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem && super.canEnchant(stack);
    }

    public static boolean isTargetBlock(@NotNull BlockState state) {
        return UNITED_STONES.contains(state.getBlock());
    }

    public static void onServerStarted(ServerStartedEvent ignored) {
        for (String string : UniteStonesOfAllConfig.UNITED_STONES.get()) {
            ResourceLocation id = ResourceLocation.parse(string);
            Block block = ForgeRegistries.BLOCKS.getValue(id);
            if (block == null) continue;
            UNITED_STONES.add(block);
        }
        for (String string : UniteStonesOfAllConfig.QUARTZ_STONES.get()) {
            ResourceLocation id = ResourceLocation.parse(string);
            Block block = ForgeRegistries.BLOCKS.getValue(id);
            if (block == null) continue;
            QUARTZ_STONES.add(block);
        }
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

    public static void onBlockBreak(BlockEvent.@NotNull BreakEvent event) {
        if (!event.isCanceled() && isTargetBlock(event.getState())) event.setExpToDrop(0);
    }
}
