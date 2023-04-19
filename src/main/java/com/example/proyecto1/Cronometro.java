package com.example.proyecto1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 0YEYE0
 * Clase cronometro
 */

public class Cronometro extends JFrame {
    private JLabel labelTiempo;
    private Timer timer;
    private int segundos;

    /**
     * Clase cronometro
     */
    public Cronometro() {
        setTitle("Cronómetro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        labelTiempo = new JLabel("0");
        labelTiempo.setFont(new Font("Arial", Font.PLAIN, 24));
        add(labelTiempo);

        pack();
        setLocationRelativeTo(null);

        // Iniciar el cronómetro automáticamente
        iniciarCronometro();
    }

    /**
     * clase iniciar cronometro
     */
    private void iniciarCronometro() {
        segundos = 0;
        labelTiempo.setText("0");
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                labelTiempo.setText(String.valueOf(segundos));
            }
        });
        timer.start();
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        Cronometro cronometro = new Cronometro();
        cronometro.setVisible(true);
    }
}
