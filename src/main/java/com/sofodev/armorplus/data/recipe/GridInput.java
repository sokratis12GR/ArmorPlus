package com.sofodev.armorplus.data.recipe;

public class GridInput {
    private final Grid grid;
    private final Input input;

    public GridInput(Grid grid, Input input) {
        this.grid = grid;
        this.input = input;
    }

    public static GridInput build(Grid grid, Input input) {
        return new GridInput(grid, input);
    }

    public static GridInput build(String rowA, Input input) {
        return new GridInput(Grid.build(rowA), input);
    }

    public static GridInput build(String rowA, String rowB, Input input) {
        return new GridInput(Grid.build(rowA, rowB), input);
    }

    public static GridInput build(String rowA, String rowB, String rowC, Input input) {
        return new GridInput(Grid.build(rowA, rowB, rowC), input);
    }

    public static GridInput build(String rowA, char... chars) {
        if (rowA.length() != 3) {
            return new GridInput(Grid.buildFull(rowA), Input.build(chars));
        }
        return new GridInput(Grid.build(rowA), Input.build(chars));
    }

    public static GridInput build(String rowA, String rowB, char... chars) {
        return new GridInput(Grid.build(rowA, rowB), Input.build(chars));
    }

    public static GridInput build(String rowA, String rowB, String rowC, char... chars) {
        return new GridInput(Grid.build(rowA, rowB, rowC), Input.build(chars));
    }

    public static GridInput build(Grid grid, char... chars) {
        return new GridInput(grid, Input.build(chars));
    }

    public static GridInput buildFull(String full, Input input) {
        return new GridInput(Grid.buildFull(full), input);
    }

    public static GridInput buildFull(String full, char... chars) {
        return new GridInput(Grid.buildFull(full), Input.build(chars));
    }

    public Grid getGrid() {
        return grid;
    }

    public Input getSimpleInput() {
        return input;
    }


    /**
     * Creates the following shaped <b>3x3</b> template:
     *
     * <ul>
     * <li><b>BIG</b>:
     * <blockquote>
     * <b>XXX</b> <br>
     * <b>XXX</b> <br>
     * <b>XXX</b>
     * </blockquote></li>
     *
     * <li><b>SMALL</b>:
     * <blockquote>
     * <b>XXO</b> <br>
     * <b>XXO</b> <br>
     * <b>OOO</b>
     * </blockquote></li></ul>
     * <p>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Hollow Square</b> template.
     * @since 16.2.0
     */
    public static GridInput buildSmallSquare(char filler) {
        String small = replace("XX ", filler);
        return build(Grid.build(small, small), filler);
    }

    /**
     * Creates a grid for a 3x3 square, filled by the same ingredient.
     *
     * @see #buildSmallSquare(char);
     */
    public static GridInput buildBigSquare(char filler) {
        String big = replace("XXX", filler);
        return build(Grid.build(big, big, big), filler);
    }

    public static GridInput buildStairs(char filler) {
        String top = replace("X  ", filler);
        String mid = replace("XX ", filler);
        String bot = replace("XXX", filler);
        return build(Grid.build(top, mid, bot), filler);
    }

    public static GridInput buildSlab(char filler) {
        String big = replace("XXX", filler);
        return build(Grid.build(big), filler);
    }

    /**
     * Creates the following shaped <b>3x3</b> template:
     * <blockquote>
     * <b>XXX</b> <br>
     * <b>X</b>O<b>X</b> <br>
     * <b>XXX</b>
     * </blockquote>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Hollow Square</b> template.
     * @since 16.2.0
     */
    public static GridInput buildHollowSquare(char filler) {
        String hollow = replace("X X", filler);
        String full = replace("XXX", filler);
        return build(Grid.build(full, hollow, full), filler);
    }

    /**
     * Creates the following shaped <b>3x3</b> template:
     * <blockquote>
     * <b>XXX</b> <br>
     * <b>X</b>O<b>X</b> <br>
     * OOO
     * </blockquote>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Helmet</b> template.
     * @since 16.2.0
     */
    public static GridInput buildHelmet(char filler) {
        String hollow = replace("X X", filler);
        String full = replace("XXX", filler);
        return build(Grid.build(full, hollow), filler);
    }

    /**
     * Creates the following shaped <b>3x3</b> template:
     * <blockquote>
     * <b>X</b>O<b>X</b> <br>
     * <b>XXX</b> <br>
     * <b>XXX</b>
     * </blockquote>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Chestplate</b> template.
     * @since 16.2.0
     */
    public static GridInput buildChestplate(char filler) {
        String hollow = replace("X X", filler);
        String full = replace("XXX", filler);
        return build(Grid.build(hollow, full, full), filler);
    }

    /**
     * Creates the following shaped <b>3x3</b> template:
     * <blockquote>
     * <b>XXX</b> <br>
     * <b>X</b>O<b>X</b> <br>
     * <b>X</b>O<b>X</b>
     * </blockquote>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Leggings</b> template.
     * @since 16.2.0
     */
    public static GridInput buildLeggings(char filler) {
        String hollow = replace("X X", filler);
        String full = replace("XXX", filler);
        return build(Grid.build(full, hollow, hollow), filler);
    }

    /**
     * Creates the following shaped <b>3x3</b> template:
     * <blockquote>
     * <b>X</b>O<b>X</b> <br>
     * <b>X</b>O<b>X</b> <br>
     * OOO
     * </blockquote>
     * Where <b>O</b> is <b>EMPTY</b> and <b>X</b> is replaced with the specified char.
     *
     * @param filler The char that will be used for the recipe input.
     * @return The Grid with the params that will create a <b>Boots</b> template.
     * @since 16.2.0
     */
    public static GridInput buildBoots(char filler) {
        String hollow = replace("X X", filler);
        return build(Grid.build(hollow, hollow), filler);
    }

    public static String replace(String txt, char replacement) {
        return txt.replace('X', replacement);
    }

}