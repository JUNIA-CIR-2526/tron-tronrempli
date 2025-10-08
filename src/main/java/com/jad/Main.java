package com.jad;

import com.jad.textwindow.TextWindow;
import com.jad.textwindow.TextWindowSettings;
import java.awt.event.KeyEvent;

public enum Main {
    ;

    public static void main(String[] args) throws InterruptedException {
        TextWindowSettings settings = new TextWindowSettings();

        settings.addKeyboardListener(KeyEvent.VK_ESCAPE, "exit");
        settings.addKeyboardListener(KeyEvent.VK_R, "restart");

        // Joueur 1 : ZQSD
        settings.addKeyboardListener(KeyEvent.VK_Q, "p1_left");
        settings.addKeyboardListener(KeyEvent.VK_D, "p1_right");

        // Joueur 2 : OKLM
        settings.addKeyboardListener(KeyEvent.VK_K, "p2_left");
        settings.addKeyboardListener(KeyEvent.VK_M, "p2_right");

        TextWindow tw = new TextWindow(settings);
        tw.setVisible(true);

        Game game = new Game(60, 25);

        while (tw.isOff("exit")) {
            if (!game.isRunning()) {
                game.draw(tw);
                if (tw.isOn("restart")) {
                    game = new Game(60, 25);
                }
                Thread.sleep(50);
                continue;
            }

            // Joueur 1 - ZQSD
            if (tw.isOn("p1_left"))  game.getP1().turnLeft();
            if (tw.isOn("p1_right")) game.getP1().turnRight();

            // Joueur 2 - OKLM
            if (tw.isOn("p2_left"))  game.getP2().turnLeft();
            if (tw.isOn("p2_right")) game.getP2().turnRight();

            game.update();
            game.draw(tw);

            Thread.sleep(100);
        }

        tw.close();
    }
}