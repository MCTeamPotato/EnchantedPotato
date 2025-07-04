package me.kall.enchantedpotato.common.data;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ArmorBreakingEntitiesData extends SavedData {
    public final Object2IntMap<UUID> entities = new Object2IntOpenHashMap<>();

    public ArmorBreakingEntitiesData() {
        super();
    }

    public static ArmorBreakingEntitiesData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(ArmorBreakingEntitiesData::load, ArmorBreakingEntitiesData::new, "armor_breaking_data");
    }

    public static ArmorBreakingEntitiesData load(CompoundTag tag) {
        ArmorBreakingEntitiesData data = new ArmorBreakingEntitiesData();
        data.loadData(tag);
        return data;
    }

    private void loadData(CompoundTag tag) {
        entities.clear();
        ListTag entries = tag.getList("entries", Tag.TAG_COMPOUND);
        for (Tag entry : entries) {
            CompoundTag entryNBT = (CompoundTag) entry;
            UUID uuid = entryNBT.getUUID("entity");
            int level = entryNBT.getInt("level");
            entities.put(uuid, level);
        }
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        ListTag entries = new ListTag();
        entities.forEach((uuid, level) -> {
            CompoundTag entryNBT = new CompoundTag();
            entryNBT.putUUID("entity", uuid);
            entryNBT.putInt("level", level);
            entries.add(entryNBT);
        });
        compoundTag.put("entries", entries);
        return compoundTag;
    }

    public void add(UUID entity, int level) {
        if (entities.containsKey(entity)) entities.removeInt(entity);
        entities.put(entity, level);
        setDirty();
    }

    public int get(UUID entity) {
        return entities.getOrDefault(entity, 0);
    }

    public void remove(UUID entity) {
        entities.removeInt(entity);
        setDirty();
    }
}