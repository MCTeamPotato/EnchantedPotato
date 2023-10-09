package com.teampotato.enchantedpotato.util;

import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.ArmorBreaking;
import com.teampotato.enchantedpotato.enchantment.ErrorSpore;
import com.teampotato.enchantedpotato.enchantment.MarkFromTheBeneath;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class Constants {
    public static double oceanHuedX;
    public static double oceanHuedY;
    public static double oceanHuedZ;
    public static double blessingOfTheNatureX;
    public static double blessingOfTheNatureY;
    public static double blessingOfTheNatureZ;

    public static double dyingOfLightX;
    public static double dyingOfLightY;
    public static double dyingOfLightZ;

    public static double pressurizedCollapseX;
    public static double pressurizedCollapseY;
    public static double pressurizedCollapseZ;

    public static double untouchableX;
    public static double untouchableY;
    public static double untouchableZ;

    public static double gurenNoYumiyaX;
    public static double gurenNoYumiyaY;
    public static double gurenNoYumiyaZ;

    public static double boneSuckalakaX;
    public static double boneSuckalakaY;
    public static double boneSuckalakaZ;

    public static double rippleOfDeathX;
    public static double rippleOfDeathY;
    public static double rippleOfDeathZ;

    public static void initConstants() {
        String[] oceanHuedRange = DetailsConfig.OCEAN_HUED_DETECTION_RANGE.get().split(";");
        oceanHuedX = Double.parseDouble(oceanHuedRange[0]);
        oceanHuedY = Double.parseDouble(oceanHuedRange[1]);
        oceanHuedZ = Double.parseDouble(oceanHuedRange[2]);

        String[] blessingOfTheNatureRange = DetailsConfig.BLESSING_OF_THE_NATURE_DETECTION_RANGE.get().split(";");
        blessingOfTheNatureX = Double.parseDouble(blessingOfTheNatureRange[0]);
        blessingOfTheNatureY = Double.parseDouble(blessingOfTheNatureRange[1]);
        blessingOfTheNatureZ = Double.parseDouble(blessingOfTheNatureRange[2]);

        String[] dyingOfLightRange = DetailsConfig.DYING_OF_LIGHT_ENTITIES_DETECTION_RANGE.get().split(";");
        dyingOfLightX = Double.parseDouble(dyingOfLightRange[0]);
        dyingOfLightY = Double.parseDouble(dyingOfLightRange[1]);
        dyingOfLightZ = Double.parseDouble(dyingOfLightRange[2]);

        String[] pressurizedCollapseRange = DetailsConfig.PRESSURIZED_COLLAPSE_BASIC_RANGE.get().split(";");
        pressurizedCollapseX = Double.parseDouble(pressurizedCollapseRange[0]);
        pressurizedCollapseY = Double.parseDouble(pressurizedCollapseRange[1]);
        pressurizedCollapseZ = Double.parseDouble(pressurizedCollapseRange[2]);

        String[] untouchableRange = DetailsConfig.UNTOUCHABLE_DETECTION_RANGE.get().split(";");
        untouchableX = Double.parseDouble(untouchableRange[0]);
        untouchableY = Double.parseDouble(untouchableRange[1]);
        untouchableZ = Double.parseDouble(untouchableRange[2]);

        String[] gurenNoYumiyaRange = DetailsConfig.GUREN_NO_YUMIYA_FIRE_ARROW_RADIUS.get().split(";");
        gurenNoYumiyaX = Double.parseDouble(gurenNoYumiyaRange[0]);
        gurenNoYumiyaY = Double.parseDouble(gurenNoYumiyaRange[1]);
        gurenNoYumiyaZ = Double.parseDouble(gurenNoYumiyaRange[2]);

        String[] boneSuckalakaRange = DetailsConfig.BONE_SUCKALAKA_DETECTION_RADIUS.get().split(";");
        boneSuckalakaX = Double.parseDouble(boneSuckalakaRange[0]);
        boneSuckalakaY = Double.parseDouble(boneSuckalakaRange[1]);
        boneSuckalakaZ = Double.parseDouble(boneSuckalakaRange[2]);

        String[] rippleOfDeathRange = DetailsConfig.RIPPLE_OF_DEATH_DETECTION_RADIUS.get().split(";");
        rippleOfDeathX = Double.parseDouble(rippleOfDeathRange[0]);
        rippleOfDeathY = Double.parseDouble(rippleOfDeathRange[1]);
        rippleOfDeathZ = Double.parseDouble(rippleOfDeathRange[2]);

        for (int i = 1; i <= ErrorSpore.ERROR_SPORE.getMaxLevel(); i++) {
            double damageReductionPercent = DetailsConfig.ERROR_SPORE_DAMAGE_REDUCTION_PER_LEVEL.get() * i;
            if (damageReductionPercent >= 1.00) damageReductionPercent = 1.00;
            ErrorSpore.ERROR_SPORE_LEVEL_MAP.put(i, (float) damageReductionPercent);
        }

        ErrorSpore.ERROR_SPORE_LEVEL_MAP.put(0, 0.0F);

        ErrorSpore.maxLevel = ErrorSpore.ERROR_SPORE.getMaxLevel();

        MarkFromTheBeneath.DIG_SPEED_MODIFIER = new AttributeModifier(MarkFromTheBeneath.DIG_SPEED_MODIFIER_UUID, "markFromTheBeneathDigSpeed", (DetailsConfig.MARK_FROM_THE_BENEATH_DIG_SPEED_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
        ArmorBreaking.ARMOR_VALUE_MODIFIER = new AttributeModifier(ArmorBreaking.ARMOR_VALUE_MODIFIER_UUID, "armorBreakingArmorValue", (DetailsConfig.ARMOR_BREAKING_ARMOR_VALUE_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
        ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER = new AttributeModifier(ArmorBreaking.ARMOR_TOUGHNESS_MODIFIER_UUID, "armorBreakingArmorToughness", (DetailsConfig.ARMOR_BREAKING_ARMOR_TOUGHNESS_MULTIPLIER.get() - 1.00), AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
