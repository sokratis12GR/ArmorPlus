/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.minetweaker;

//public class MTArmorPlusPlugin {
//
//    public static void init() {
//        MineTweakerAPI.registerClass(Workbench.class);
//        MineTweakerAPI.registerClass(HighTechBench.class);
//        MineTweakerAPI.registerClass(UltiTechBench.class);
//        MineTweakerAPI.registerClass(LavaInfuser.class);
//    }
//
//    public static Object[] toHighTechShapedObjects(IIngredient[][] ingredients) {
//        if (ingredients == null)
//            return null;
//        ArrayList<Object> prep = new ArrayList<>();
//        prep.add("abcd");
//        prep.add("efgh");
//        prep.add("ijkl");
//        prep.add("mnop");
//        char[][] map = new char[][]{{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}, {'i', 'j', 'k', 'l'}, {'m', 'n', 'o', 'p'}};
//        toShapedObjects(ingredients, map, prep);
//        return prep.toArray();
//    }
//
//    public static Object[] toUltiTechShapedObjects(IIngredient[][] ingredients) {
//        if (ingredients == null)
//            return null;
//        ArrayList<Object> prep = new ArrayList<>();
//        prep.add("abcde");
//        prep.add("fghij");
//        prep.add("klmno");
//        prep.add("pqrst");
//        prep.add("uvwxy");
//        char[][] map = new char[][]{{'a', 'b', 'c', 'd', 'e'}, {'f', 'g', 'h', 'i', 'j'}, {'k', 'l', 'm', 'n', 'o',}, {'p', 'q', 'r', 's', 't'}, {'u', 'v', 'w', 'x', 'y'}};
//        toShapedObjects(ingredients, map, prep);
//        return prep.toArray();
//    }
//
//    private static void toShapedObjects(IIngredient[][] ingredients, char[][] map, ArrayList<Object> prep) {
//        for (int x = 0; x < ingredients.length; x++) {
//            if (ingredients[x] != null) {
//                for (int y = 0; y < ingredients[x].length; y++) {
//                    if (ingredients[x][y] != null && x < map.length && y < map[x].length) {
//                        prep.add(map[x][y]);
//                        prep.add(toObject(ingredients[x][y]));
//                    }
//                }
//            }
//        }
//    }
//}
//