package com.example.proyecto1;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author 0YEYEY0
 * Clase bandera
 */
public class Bandera {

    /**
     * Main de bandera
     */
    public static void main() {
        JFrame frame = new JFrame("Bandera");
        JPanel panel = new JPanel();
        JButton boton = new JButton();

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    boton.setBackground(Color.RED);
                }
            }
        });

        panel.add(boton);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
