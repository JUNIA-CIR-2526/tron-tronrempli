package com.jad;

public class LightCycle {
    private int x;
    private int y;
    private Direction direction;
    private final char symbol;
    private boolean alive;

    public LightCycle(int x, int y, Direction direction, char symbol) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.symbol = symbol;
        this.alive = true;
    }

    public void move() {
        if (!alive) return;

        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }

    public void turnLeft() {
        if (!alive) return;

        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
        }
    }

    public void turnRight() {
        if (!alive) return;

        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.UP;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}