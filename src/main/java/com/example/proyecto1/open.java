package com.example.proyecto1;

import javax. swing.*;




public class open {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new Mineswweeper();
                JFrame frame1 = new Cronometro();
                frame1.setSize(100,100);
                frame1.setVisible(true);
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });

        }
    }


