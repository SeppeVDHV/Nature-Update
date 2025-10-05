package net.seppevdh.natureupdate.compat.jei.animation;

public class IntGenerator {
    public static class WaterBoiler {
        private static int anInt = 0;
        public static final int intLimit = 1200;

        public static int nextInt () {
            anInt++;
            if (anInt > intLimit) {
                anInt = 0;
            }
            return anInt;
        }
    }
    public static class Fuel {
        private static int anInt = 0;
        public static final int intLimit = 600;

        public static int nextInt () {
            anInt++;
            if (anInt > intLimit) {
                anInt = 0;
            }
            return anInt;
        }
    }
    public static class BreezingStand {
        private static int anInt = 0;
        public static final int intLimit = 90;

        public static int nextInt () {
            anInt++;
            if (anInt > intLimit) {
                anInt = 0;
            }
            return anInt;
        }
    }
}
