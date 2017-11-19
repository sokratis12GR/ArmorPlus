/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import net.thedragonteam.armorplus.compat.crafttweaker.lavainfuser.LavaInfuser;

import java.util.ArrayList;

import static net.thedragonteam.armorplus.compat.crafttweaker.InputHelper.toObject;


public class CTArmorPlusPlugin {

    public static void init() {
        CraftTweakerAPI.registerClass(Workbench.class);
        CraftTweakerAPI.registerClass(HighTechBench.class);
        CraftTweakerAPI.registerClass(UltiTechBench.class);
        CraftTweakerAPI.registerClass(ChampionBench.class);
        CraftTweakerAPI.registerClass(LavaInfuser.class);
    }

    public static Object[] toWorkbenchShapedObjects(IIngredient[][] ingredients) {
        if (ingredients == null) return null;
        ArrayList<Object> prep = new ArrayList<>();
        prep.add("abc");
        prep.add("def");
        prep.add("ghi");
        char[][] map = new char[][]{
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };
        toShapedObjects(ingredients, map, prep);
        return prep.toArray();
    }

    public static Object[] toHighTechShapedObjects(IIngredient[][] ingredients) {
        if (ingredients == null) return null;
        ArrayList<Object> prep = new ArrayList<>();
        prep.add("abcde");
        prep.add("fghij");
        prep.add("klmno");
        prep.add("pqrst");
        prep.add("uvwxy");
        char[][] map = new char[][]{
            {'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o'},
            {'p', 'q', 'r', 's', 't'},
            {'u', 'v', 'w', 'x', 'y'}
        };
        toShapedObjects(ingredients, map, prep);
        return prep.toArray();
    }

    public static Object[] toUltiTechShapedObjects(IIngredient[][] ingredients) {
        if (ingredients == null) return null;
        ArrayList<Object> prep = new ArrayList<>();
        prep.add("abcdefg");
        prep.add("hijklmn");
        prep.add("opqrstu");
        prep.add("vwxyzAB");
        prep.add("CDEFGHI");
        prep.add("JKLMNOP");
        prep.add("QRSTUVW");
        char[][] map = new char[][]{
            {'a', 'b', 'c', 'd', 'e', 'f', 'g'},
            {'h', 'i', 'j', 'k', 'l', 'm', 'n'},
            {'o', 'p', 'q', 'r', 's', 't', 'u'},
            {'v', 'w', 'x', 'y', 'z', 'A', 'B'},
            {'C', 'D', 'E', 'F', 'G', 'H', 'I'},
            {'J', 'K', 'L', 'M', 'N', 'O', 'P'},
            {'Q', 'R', 'S', 'T', 'U', 'V', 'W'}
        };
        toShapedObjects(ingredients, map, prep);
        return prep.toArray();
    }

    public static Object[] toChampionShapedObjects(IIngredient[][] ingredients) {
        if (ingredients == null) return null;
        ArrayList<Object> prep = new ArrayList<>();
        prep.add("abcdefghi");
        prep.add("jklmnopqr");
        prep.add("stuvwxyzA");
        prep.add("BCDEFGHIJ");
        prep.add("KLMNOPQRS");
        prep.add("TUVWXYZ12");
        prep.add("34567890!");
        prep.add("@#$%^&*()");
        prep.add("-=_+`~|<>");
        char[][] map = new char[][]{
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'},
            {'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r'},
            {'s', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A'},
            {'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'},
            {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S'},
            {'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2'},
            {'3', '4', '5', '6', '7', '8', '9', '0', '!'},
            {'@', '#', '$', '%', '^', '&', '*', '(', ')'},
            {'-', '=', '_', '+', '`', '~', '|', '<', '>'}
        };
        toShapedObjects(ingredients, map, prep);
        return prep.toArray();
    }

    public static Object[] toShapedObjects(IIngredient[][] ingredients, char[][] map, ArrayList<Object> prep) {
        if (ingredients == null) return new Object[0];
        for (int x = 0; x < ingredients.length; x++) {
            if (ingredients[x] != null) {
                for (int y = 0; y < ingredients[x].length; y++) {
                    if (ingredients[x][y] != null && x < map.length && y < map[x].length) {
                        prep.add(map[x][y]);
                        prep.add(toObject(ingredients[x][y]));
                    }
                }
            }
        }
        return prep.toArray();
    }
}
