package com.jad;

import com.jad.textwindow.TextWindow;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int width;
    private int height;
    private List<Position> walls = new ArrayList<>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void addWall(Position p) {
        walls.add(new Position(p.getX(), p.getY()));
    }

    public List<Position> getWalls() { return walls; }

    public void display(TextWindow tw, LightCycle c1, LightCycle c2) {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position p = new Position(x, y);

                if (c1.getPosition().equals(p))
                    sb.append(c1.getSymbol());
                else if (c2.getPosition().equals(p))
                    sb.append(c2.getSymbol());
                else if (contains(p))
                    sb.append('#');
                else
                    sb.append('.');
            }
            sb.append("\n");
        }

        tw.display(sb.toString());
    }

    private boolean contains(Position p) {
        for (Position wall : walls) {
            if (wall.equals(p)) return true;
        }
        return false;
    }
}
