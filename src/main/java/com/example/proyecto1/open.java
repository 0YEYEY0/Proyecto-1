package com.example.proyecto1;

import javax. swing.*;



public class open {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new Mineswweeper();
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });

        }
    }


