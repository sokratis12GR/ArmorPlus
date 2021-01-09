package com.sofodev.armorplus.data.recipe;

import java.util.List;

import static com.google.common.primitives.Chars.asList;

public class Input {

    public static final char DEFAULT_A = '1';
    public static final char DEFAULT_B = '2';
    public static final char DEFAULT_C = '3';
    public static final char DEFAULT_D = '4';
    public static final char DEFAULT_E = '5';
    public static final char DEFAULT_F = '6';
    public static final char DEFAULT_G = '7';
    public static final char DEFAULT_H = '8';
    public static final char DEFAULT_I = '9';

    public static final Input EMPTY = build(DEFAULT_A);

    private int paramCount;
    private char a;
    private char b;
    private char c;
    private char d;
    private char e;
    private char f;
    private char g;
    private char h;
    private char i;

    public Input(int paramCount, char a, char b, char c, char d, char e, char f, char g, char h, char i) {
        this.paramCount = paramCount;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
    }

    public Input(char a, char b, char c, char d, char e, char f, char g, char h, char i) {
        this(9, a, b, c, d, e, f, g, h, i);
    }

    public Input(char a, char b, char c, char d, char e, char f, char g, char h) {
        this(8, a, b, c, d, e, f, g, h, DEFAULT_I);
    }

    public Input(char a, char b, char c, char d, char e, char f, char g) {
        this(7, a, b, c, d, e, f, g, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a, char b, char c, char d, char e, char f) {
        this(6, a, b, c, d, e, f, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a, char b, char c, char d, char e) {
        this(5, a, b, c, d, e, DEFAULT_F, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a, char b, char c, char d) {
        this(4, a, b, c, d, DEFAULT_E, DEFAULT_F, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a, char b, char c) {
        this(3, a, b, c, DEFAULT_D, DEFAULT_E, DEFAULT_F, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a, char b) {
        this(2, a, b, DEFAULT_C, DEFAULT_D, DEFAULT_E, DEFAULT_F, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public Input(char a) {
        this(1, a, DEFAULT_B, DEFAULT_C, DEFAULT_D, DEFAULT_E, DEFAULT_F, DEFAULT_G, DEFAULT_H, DEFAULT_I);
    }

    public static Input build(char a, char b, char c) {
        return new Input(a, b, c);
    }

    public static Input build(char a, char b) {
        return new Input(a, b);
    }

    public static Input build(char a) {
        return new Input(a);
    }

    public static Input build(char[] chars) {
        int length = chars.length;
        switch (length) {
            case 1:
                return new Input(chars[0]);
            case 2:
                return new Input(chars[0], chars[1]);
            case 3:
                return new Input(chars[0], chars[1], chars[2]);
            case 4:
                return new Input(chars[0], chars[1], chars[2], chars[3]);
            case 5:
                return new Input(chars[0], chars[1], chars[2], chars[3], chars[4]);
            case 6:
                return new Input(chars[0], chars[1], chars[2], chars[3], chars[4], chars[5]);
            case 7:
                return new Input(chars[0], chars[1], chars[2], chars[3], chars[4], chars[5], chars[6]);
            case 8:
                return new Input(chars[0], chars[1], chars[2], chars[3], chars[4], chars[5], chars[6], chars[7]);
            case 9:
                return new Input(chars[0], chars[1], chars[2], chars[3], chars[4], chars[5], chars[6], chars[7], chars[8]);
            default:
                return EMPTY;
        }
    }

    public int getCount() {
        return paramCount;
    }

    public char getA() {
        return a;
    }

    public char getB() {
        return b;
    }

    public char getC() {
        return c;
    }

    public char getD() {
        return d;
    }

    public char getE() {
        return e;
    }

    public char getF() {
        return f;
    }

    public char getG() {
        return g;
    }

    public char getH() {
        return h;
    }

    public char getI() {
        return i;
    }

    public List<Character> getCharList() {
        return asList(a, b, c, d, e, f, g, h, i);
    }

    public Input setParamCount(int paramCount) {
        this.paramCount = paramCount;
        return this;
    }

    public Input setA(char a) {
        this.a = a;
        return this;
    }

    public Input setB(char b) {
        this.b = b;
        return this;
    }

    public Input setC(char c) {
        this.c = c;
        return this;
    }

    public Input setD(char d) {
        this.d = d;
        return this;
    }

    public Input setE(char e) {
        this.e = e;
        return this;
    }

    public Input setF(char f) {
        this.f = f;
        return this;
    }

    public Input setG(char g) {
        this.g = g;
        return this;
    }

    public Input setH(char h) {
        this.h = h;
        return this;
    }

    public Input setI(char i) {
        this.i = i;
        return this;
    }
}