package net.seppevdh.natureupdate.screen.animation;

public class IntGenerator {
    public static class WaterBoiler {
        private static int anInt = 0;
        public static final int intLimit = 72;

        public static int nextInt () {
            anInt++;
            if (anInt > intLimit) {
                anInt = 0;
            }
            return Math.floorDiv(anInt, 3);
        }
    }
    public static class BreezingStand {
        private static int anInt = 0;
        public static final int intLimit = 56;

        public static int nextInt () {
            anInt++;
            if (anInt > intLimit) {
                anInt = 0;
            }
            return Math.floorDiv(anInt, 2);
        }
    }
}
