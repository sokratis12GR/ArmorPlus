package com.sofodev.armorplus.data.recipe;

public class Grid {

    //Creates an empty 3x3 grid
    public static final Grid EMPTY = new Grid("   ", "   ", "   ");

    private final String rowA;
    private final String rowB;
    private final String rowC;

    public Grid(String rowA, String rowB, String rowC) {
        this.rowA = rowA;
        this.rowB = rowB;
        this.rowC = rowC;
    }

    public Grid(String full) {
        this.rowA = full.substring(0, 2);
        this.rowB = full.substring(3, 5);
        this.rowC = full.substring(6, 8);
    }

    public static Grid build(String rowA, String rowB, String rowC) {
        return new Grid(rowA, rowB, rowC);
    }

    public static Grid build(String rowA, String rowB) {
        return new Grid(rowA, rowB, "   ");
    }

    public static Grid build(String rowA) {
        return new Grid(rowA, "   ", "   ");
    }

    public static Grid buildFull(String full) {
        switch (full.length()) {
            case 3:
                return new Grid(full, "   ", "   ");
            case 6:
                return new Grid(full.substring(0, 2), full.substring(3, 5), "   ");
            case 9:
                return new Grid(full);
            default:
                return EMPTY;
        }
    }

    public String getFirstRow() {
        return rowA;
    }

    public String getSecondRow() {
        return rowB;
    }

    public String getThirdRow() {
        return rowC;
    }

    public enum Size {
        SMALL,
        BIG
    }
}