package com.teampotato.enchantedpotato.jei;

import com.google.common.collect.Maps;
import com.teampotato.enchantedpotato.EnchantedPotato;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@JeiPlugin
@SuppressWarnings("unused")
public class EnchantedInfoPlugin implements IModPlugin {
    private final ResourceLocation pluginUid = new ResourceLocation(EarlySetupInitializer.MOD_ID, "jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return this.pluginUid;
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        final String trueKey = I18n.get("jei.enchantedpotato.true");
        final String falseKey = I18n.get("jei.enchantedpotato.false");
        final ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultInstance();

        final Map<Enchantment, String> jeiInfoMap = Maps.newHashMap();
        for (DeferredHolder<Enchantment, ? extends Enchantment> enchantmentDeferredHolder : EnchantedPotato.EnchantedRegistries.ENCHANTMENTS.getEntries()) {
            jeiInfoMap.put(enchantmentDeferredHolder.get(), "jei." + enchantmentDeferredHolder.get().getDescriptionId().replace("enchantment.", "") + ".info");
        }

        final Map<String, String> rarityMap = Maps.newHashMap();
        for (Enchantment.Rarity rarity : Enchantment.Rarity.values()) {
            rarityMap.put(rarity.name(), I18n.get("jei." + EarlySetupInitializer.MOD_ID + ".rarity." + rarity.toString().toLowerCase()));
        }

        jeiInfoMap.forEach((enchantment, infoKey) -> {
            if (I18n.exists(infoKey)) {
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
                                    rarityMap.get(enchantment.getRarity().name())
                            ),
                            Component.translatable(infoKey)
                    );
                    enchantedBook.removeTagKey("Enchantments");
                }
            }
        });
    }
}
