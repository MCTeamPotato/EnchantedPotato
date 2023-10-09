package com.teampotato.enchantedpotato.jei;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.registry.ModEnchantments;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@JeiPlugin
@SuppressWarnings("unused")
public class EnchantedInfoPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = new ResourceLocation(EarlySetupInitializer.MOD_ID, "jei_plugin");
    private static final Map<String, String> RARITY_MAP = new Object2ObjectOpenHashMap<>();

    public static final Map<Enchantment, String> JEI_INFO_MAP = new Object2ObjectOpenHashMap<>();
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        if (JEI_INFO_MAP.isEmpty()) {
            for (RegistryObject<Enchantment> registryObject : ModEnchantments.ENCHANTMENTS.getEntries()) {
                JEI_INFO_MAP.put(registryObject.get(), "jei." + registryObject.get().getDescriptionId().replace("enchantment.", "") + ".info");
            }
        }
        if (RARITY_MAP.isEmpty()) {
            for (Enchantment.Rarity rarity : Enchantment.Rarity.values()) {
                RARITY_MAP.put(rarity.name(), I18n.get("jei." + EarlySetupInitializer.MOD_ID + ".rarity." + rarity.toString().toLowerCase()));
            }
        }
        final String trueKey = I18n.get("jei.enchantedpotato.true");
        final String falseKey = I18n.get("jei.enchantedpotato.false");
        JEI_INFO_MAP.forEach((enchantment, infoKey) -> {
            if (I18n.exists(infoKey)) {
                ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultInstance();
                for (int i = 1; i < enchantment.getMaxLevel(); i++) {
                    enchantedBook.enchant(enchantment, i);
                    registration.addIngredientInfo(
                            enchantedBook,
                            VanillaTypes.ITEM_STACK,
                            Component.translatable(
                                    "jei." + EarlySetupInitializer.MOD_ID + ".enchantments.info",
                                    enchantment.isAllowedOnBooks() ? trueKey : falseKey,
                                    enchantment.isCurse() ? trueKey : falseKey,
                                    enchantment.isDiscoverable() ? trueKey : falseKey,
                                    enchantment.isTradeable() ? trueKey : falseKey,
                                    enchantment.isTreasureOnly() ? trueKey : falseKey,
                                    enchantment.getMaxLevel(),
                                    RARITY_MAP.get(enchantment.getRarity().name())
                            ),
                            Component.translatable(infoKey)
                    );
                    enchantedBook.removeTagKey("Enchantments");
                }
            }
        });
    }
}
