package com.jad;

import com.jad.textwindow.TextWindow;

public class Game {
    private final int width;
    private final int height;
    private final LightCycle player1;
    private final LightCycle player2;
    private final char[][] grid;
    private boolean running;
    private String winner;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];

        // Initialiser la grille vide
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }

        // Joueur 1 (Bleu) commence à gauche
        player1 = new LightCycle(width / 4, height / 2, Direction.RIGHT, '1');

        // Joueur 2 (Orange) commence à droite
        player2 = new LightCycle(3 * width / 4, height / 2, Direction.LEFT, '2');

        this.running = true;
        this.winner = "";
    }

    public void update() {
        if (!running) return;

        // Marquer les positions actuelles sur la grille AVANT de bouger
        if (isInBounds(player1.getX(), player1.getY())) {
            grid[player1.getY()][player1.getX()] = player1.getSymbol();
        }
        if (isInBounds(player2.getX(), player2.getY())) {
            grid[player2.getY()][player2.getX()] = player2.getSymbol();
        }

        // Déplacer les joueurs
        player1.move();
        player2.move();

        // Vérifier les collisions
        boolean p1Collision = checkCollision(player1);
        boolean p2Collision = checkCollision(player2);

        if (p1Collision && p2Collision) {
            running = false;
            winner = "EGALITE !";
        } else if (p1Collision) {
            running = false;
            winner = "JOUEUR 2 (OKLM) GAGNE !";
        } else if (p2Collision) {
            running = false;
            winner = "JOUEUR 1 (ZQSD) GAGNE !";
        }
    }

    private boolean checkCollision(LightCycle cycle) {
        int x = cycle.getX();
        int y = cycle.getY();

        // Collision avec les murs
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return true;
        }

        // Collision avec une traînée (la sienne ou celle de l'autre)
        if (grid[y][x] != ' ') {
            return true;
        }

        return false;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void draw(TextWindow tw) {
        // Construire l'affichage ligne par ligne
        StringBuilder display = new StringBuilder();

        // Dessiner chaque ligne
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Bordures
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    display.append('#');
                }
                // Position du joueur 1
                else if (player1.isAlive() && x == player1.getX() && y == player1.getY()) {
                    display.append('@');
                }
                // Position du joueur 2
                else if (player2.isAlive() && x == player2.getX() && y == player2.getY()) {
                    display.append('O');
                }
                // Traînées
                else if (grid[y][x] != ' ') {
                    display.append(grid[y][x]);
                }
                // Espace vide
                else {
                    display.append(' ');
                }
            }
            display.append('\n');
        }

        // Afficher les instructions
        display.append("\nJoueur 1 (Bleu) @ : Q/D pour tourner | Joueur 2 (Orange) O : K/M pour tourner\n");

        // Afficher le game over
        if (!running) {
            display.append("\n").append(winner).append("\n");
            display.append("Appuyez sur R pour rejouer\n");
        }

        tw.display(display.toString());
    }

    public boolean isRunning() {
        return running;
    }

    public LightCycle getP1() {
        return player1;
    }

    public LightCycle getP2() {
        return player2;
    }
}