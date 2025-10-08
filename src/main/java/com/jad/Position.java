package com.jad;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void wrap(int width, int height) {
        if (x < 0) x = width - 1;
        if (x >= width) x = 0;
        if (y < 0) y = height - 1;
        if (y >= height) y = 0;
    }

    public boolean equals(Position p) {
        return this.x == p.x && this.y == p.y;
    }
}