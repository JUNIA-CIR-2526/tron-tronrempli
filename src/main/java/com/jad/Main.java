package com.jad;

import com.jad.textwindow.TextWindow;
import com.jad.textwindow.TextWindowSettings;
import java.awt.event.KeyEvent;

public enum Main {
    ;

    public static void main(String[] args) throws InterruptedException {
        TextWindowSettings settings = new TextWindowSettings();

        settings.addKeyboardListener(KeyEvent.VK_ESCAPE, "exit");

        // Joueur 1 : ZQSD
        settings.addKeyboardListener(KeyEvent.VK_Q, "p1_left");
        settings.addKeyboardListener(KeyEvent.VK_D, "p1_right");
        settings.addKeyboardListener(KeyEvent.VK_Z, "p1_up");
        settings.addKeyboardListener(KeyEvent.VK_S, "p1_down");

        // Joueur 2 : Flèches
        settings.addKeyboardListener(KeyEvent.VK_K, "p2_left");
        settings.addKeyboardListener(KeyEvent.VK_M, "p2_right");
        settings.addKeyboardListener(KeyEvent.VK_O, "p2_up");
        settings.addKeyboardListener(KeyEvent.VK_L, "p2_down");

        TextWindow tw = new TextWindow(settings);
        tw.setVisible(true);

        Game game = new Game(60, 25);

        while (game.isRunning() && tw.isOff("exit")) {
            // Joueur 1
            if (tw.isOn("p1_left"))  game.getP1().turnLeft();
            if (tw.isOn("p1_right")) game.getP1().turnRight();
            if (tw.isOn("p1_up"))    game.getP1().turnLeft();
            if (tw.isOn("p1_down"))  game.getP1().turnRight();

            // Joueur 2
            if (tw.isOn("p2_left"))  game.getP2().turnLeft();
            if (tw.isOn("p2_right")) game.getP2().turnRight();
            if (tw.isOn("p2_up"))    game.getP2().turnLeft();
            if (tw.isOn("p2_down"))  game.getP2().turnRight();

            game.update();
            game.draw(tw);

            Thread.sleep(120);
        }

        tw.display("GAME OVER !");
        Thread.sleep(2000);
        tw.close();
    }
}
