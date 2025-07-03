package me.kall.enchantedpotato.client.gui;

import me.kall.enchantedpotato.client.config.ClientConfig;
import me.kall.enchantedpotato.common.api.ExtendedPlayer;

public class OceanHuedOverlay {
    public static final class Counting extends AbstractOverlay {

        @Override
        public String getTranslateKey() {
            return "info.enchantedpotato.oceanhued.counting";
        }

        @Override
        public int getX() {
            return ClientConfig.OCEAN_HUED_COUNTING_POS_X.get();
        }

        @Override
        public int getYOffSet() {
            return ClientConfig.OCEAN_HUED_COUNTING_POS_Y_OFFSET.get();
        }

        @Override
        public double getValue(ExtendedPlayer player) {
            return player.oceanHued$isReady() ? 0.00 : player.oceanHued$getCountingTicks();
        }

        @Override
        public boolean isTicks() {
            return true;
        }
    }

    public static final class Cooldown extends AbstractOverlay {

        @Override
        public String getTranslateKey() {
            return "info.enchantedpotato.oceanhued.cooldown";
        }

        @Override
        public int getX() {
            return ClientConfig.OCEAN_HUED_COOLDOWN_POS_X.get();
        }

        @Override
        public int getYOffSet() {
            return ClientConfig.OCEAN_HUED_COOLDOWN_POS_Y_OFFSET.get();
        }

        @Override
        public double getValue(ExtendedPlayer player) {
            return player.oceanHued$getCoolDown();
        }

        @Override
        public boolean isTicks() {
            return true;
        }
    }

    public static final class HealingAmount extends AbstractOverlay {

        @Override
        public String getTranslateKey() {
            return "info.enchantedpotato.oceanhued.healing_amount";
        }

        @Override
        public int getX() {
            return ClientConfig.OCEAN_HUED_HEALING_AMOUNT_POS_X.get();
        }

        @Override
        public int getYOffSet() {
            return ClientConfig.OCEAN_HUED_HEALING_AMOUNT_POS_Y_OFFSET.get();
        }

        @Override
        public double getValue(ExtendedPlayer player) {
            return player.oceanHued$isReady() ? player.oceanHued$getHealingAmount() : 0.00;
        }

        @Override
        public boolean isTicks() {
            return false;
        }
    }
}
