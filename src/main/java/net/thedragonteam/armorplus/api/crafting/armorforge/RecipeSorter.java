/*
 * Minecraft Forge
 * Copyright (c) 2016.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.thedragonteam.armorplus.api.crafting.armorforge;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.toposort.TopologicalSort;
import net.minecraftforge.fml.common.toposort.TopologicalSort.DirectedGraph;

import java.util.*;

public class RecipeSorter implements Comparator<IRecipe> {
    public enum Category {
        /**
         * Do not use UNKNOWN - it is for recipe types with no clear driver
         */
        UNKNOWN,
        SHAPELESS,
        SHAPED
    }

    private static class SortEntry {
        private String name;
        private Class<?> cls;
        private Category cat;
        List<String> before = Lists.newArrayList();
        List<String> after = Lists.newArrayList();

        private SortEntry(String name, Class<?> cls, Category cat, String deps) {
            this.name = name;
            this.cls = cls;
            this.cat = cat;
            parseDepends(deps);
        }

        private void parseDepends(String deps) {
            if (deps.isEmpty()) return;
            for (String dep : deps.split(" ")) {
                if (dep.startsWith("before:")) {
                    before.add(dep.substring(7));
                } else if (dep.startsWith("after:")) {
                    after.add(dep.substring(6));
                } else {
                    throw new IllegalArgumentException("Invalid dependency: " + dep);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append("RecipeEntry(\"").append(name).append("\", ");
            buf.append(cat.name()).append(", ");
            buf.append(cls == null ? "" : cls.getName()).append(")");

            if (before.size() > 0) {
                buf.append(" Before: ").append(Joiner.on(", ").join(before));
            }

            if (after.size() > 0) {
                buf.append(" After: ").append(Joiner.on(", ").join(after));
            }

            return buf.toString();
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    ;

    private static Map<Class<?>, Category> categories = Maps.newHashMap();
    //private static Map<String, Class<?>>       types = Maps.newHashMap();
    private static Map<String, SortEntry> entries = Maps.newHashMap();
    private static Map<Class<?>, Integer> priorities = Maps.newHashMap();

    public static final RecipeSorter INSTANCE = new RecipeSorter();
    private static boolean isDirty = true;

    private static SortEntry before = new SortEntry("Before", null, RecipeSorter.Category.UNKNOWN, "");
    private static SortEntry after = new SortEntry("After", null, RecipeSorter.Category.UNKNOWN, "");

    private RecipeSorter() {
        register("minecraft:shaped", ShapedRecipes.class, RecipeSorter.Category.SHAPED, "before:minecraft:shapeless");
        register("minecraft:shapeless", ShapelessRecipes.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shaped");
        register("forge:shapedore", ShapedOreRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
        register("forge:shapelessore", ShapelessOreRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    }

    @Override
    public int compare(IRecipe r1, IRecipe r2) {
        Category c1 = getCategory(r1);
        Category c2 = getCategory(r2);
        int categoryComparison = -c1.compareTo(c2);
        if (categoryComparison != 0) {
            return categoryComparison;
        } else {
            if (r2.getRecipeSize() < r1.getRecipeSize()) return -1;
            if (r2.getRecipeSize() > r1.getRecipeSize()) return 1;
            return getPriority(r2) - getPriority(r1); // high priority value first!
        }
    }

    private static Set<Class<?>> warned = Sets.newHashSet();

    public static void sortCraftManager() {
        bake();
        FMLLog.fine("Sorting recipes");
        warned.clear();
        Collections.sort(ArmorForgeCraftingManager.getInstance().getRecipeList(), INSTANCE);
    }

    public static void register(String name, Class<?> recipe, Category category, String dependencies) {
        assert (category != RecipeSorter.Category.UNKNOWN) : "Category must not be unknown!";
        isDirty = true;

        SortEntry entry = new SortEntry(name, recipe, category, dependencies);
        entries.put(name, entry);
        setCategory(recipe, category);
    }

    public static void setCategory(Class<?> recipe, Category category) {
        assert (category != RecipeSorter.Category.UNKNOWN) : "Category must not be unknown!";
        categories.put(recipe, category);
    }

    public static Category getCategory(IRecipe recipe) {
        return getCategory(recipe.getClass());
    }

    public static Category getCategory(Class<?> recipe) {
        Class<?> cls = recipe;
        Category ret = categories.get(cls);

        if (ret == null) {
            while (cls != Object.class) {
                cls = cls.getSuperclass();
                ret = categories.get(cls);
                if (ret != null) {
                    categories.put(recipe, ret);
                    return ret;
                }
            }
        }

        return ret == null ? RecipeSorter.Category.UNKNOWN : ret;
    }

    private static int getPriority(IRecipe recipe) {
        Class<?> cls = recipe.getClass();
        Integer ret = priorities.get(cls);

        if (ret == null) {
            if (!warned.contains(cls)) {
                FMLLog.bigWarning("Unknown recipe class! %s Modders need to register their recipe types with %s", cls.getName(), RecipeSorter.class.getName());
                warned.add(cls);
            }
            cls = cls.getSuperclass();
            while (cls != Object.class) {
                ret = priorities.get(cls);
                if (ret != null) {
                    priorities.put(recipe.getClass(), ret);
                    FMLLog.fine("    Parent Found: %d - %s", ret, cls.getName());
                    return ret;
                }
            }
        }

        return ret == null ? 0 : ret;
    }

    private static void bake() {
        if (!isDirty) return;
        FMLLog.fine("Forge RecipeSorter Baking:");
        DirectedGraph<SortEntry> sorter = new DirectedGraph<SortEntry>();
        sorter.addNode(before);
        sorter.addNode(after);
        sorter.addEdge(before, after);

        for (Map.Entry<String, SortEntry> entry : entries.entrySet()) {
            sorter.addNode(entry.getValue());
        }

        for (Map.Entry<String, SortEntry> e : entries.entrySet()) {
            SortEntry entry = e.getValue();
            boolean postAdded = false;

            sorter.addEdge(before, entry);
            for (String dep : entry.after) {
                if (entries.containsKey(dep)) {
                    sorter.addEdge(entries.get(dep), entry);
                }
            }

            for (String dep : entry.before) {
                postAdded = true;
                sorter.addEdge(entry, after);
                if (entries.containsKey(dep)) {
                    sorter.addEdge(entry, entries.get(dep));
                }
            }

            if (!postAdded) {
                sorter.addEdge(entry, after);
            }
        }


        List<SortEntry> sorted = TopologicalSort.topologicalSort(sorter);
        int x = sorted.size();
        for (SortEntry entry : sorted) {
            FMLLog.fine("  %d: %s", x, entry);
            priorities.put(entry.cls, x--);
        }
    }
}