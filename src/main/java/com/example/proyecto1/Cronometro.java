package com.example.proyecto1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro extends JFrame {
    private JLabel labelTiempo;
    private Timer timer;
    private int segundos;

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

    public static void main(String[] args) {
        Cronometro cronometro = new Cronometro();
        cronometro.setVisible(true);
    }
}
