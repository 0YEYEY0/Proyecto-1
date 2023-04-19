package com.example.proyecto1;

import javax. swing.*;


/**
 * Abre el juego
 */
public class open {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new Mineswweeper();
                JFrame frame1 = new Cronometro();
                frame1.setSize(100,100);
                frame1.setVisible(true);
                frame.setSize(300, 300);
                frame.setVisible(true);
            }
        });

        }
    }


