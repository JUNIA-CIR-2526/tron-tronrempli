package com.jad;

import com.jad.textwindow.TextWindow;
import com.jad.textwindow.TextWindowSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

public enum Main {
    ;

    public static void main(String[] args) {
        TextWindowSettings settings = new TextWindowSettings();
        settings.addKeyboardListener(KeyEvent.VK_ESCAPE, "exit");
        TextWindow textWindow = new TextWindow(settings);
        textWindow.setVisible(true);

        Point lastMousePosition;
        while (textWindow.isOff("exit")) {
            lastMousePosition = textWindow.getMousePosition();
            StringBuilder message = new StringBuilder("Press ESC to exit.\n");
            textWindow.display(message.toString());
        }
        textWindow.close();
    }
}