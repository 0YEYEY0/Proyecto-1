package com.example.proyecto1;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.firmata4j.IODevice;
import org.firmata4j.IOEvent;

import org.firmata4j.PinEventListener;
import org.firmata4j.firmata.FirmataDevice;




/**
 *
 * @author 0YEYEY0
 */
public class Mineswweeper extends javax.swing.JFrame {



    int numFilas = 8;
    int numColumnas = 8;
    int numMinas = 10;
    Lista lista = new Lista();

    static int contador = 0;

    static Random random = new Random();

    static int turno = random.nextInt();






    JButton[][] botonesTablero;

    Tablero tablero;




    /**
     *
     */
    public Mineswweeper() {
        cargarControles();
        crearTablero();
        new Cronometro();
        lista.primerElemento();
        dummy_level();
        Bandera.main();
        /**

        try{
            arduino.start();
            arduino.ensureInitializationIsDone();
            System.out.print("Iniciado");


        } catch (Exception e) {
            System.out.println("Error");

        }

        try{
            var boton12 = arduino.getPin(12);
            boton12.setMode(Pin.Mode.INPUT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         **/


    }





    public static void mensajego() {
        JOptionPane.showMessageDialog(null,
                "█▀▀ ▄▀█ █▀▄▀█ █▀▀   █▀█ █░█ █▀▀ █▀█\n" +
                        "█▄█ █▀█ █░▀░█ ██▄   █▄█ ▀▄▀ ██▄ █▀▄",
                "PopUp Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensajecongra() {
        JOptionPane.showMessageDialog(null,
                "█▄█ █▀█ █░█   █░█░█ █ █▄░█\n" +
                        "░█░ █▄█ █▄█   ▀▄▀▄▀ █ █░▀█",
                "PopUp Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void crearTablero(){
        tablero=new Tablero(numFilas, numColumnas, numMinas);



        tablero.setEventoPartidaPerdida(new Consumer<List<Matriz>>() {
            @Override
            public void accept(List<Matriz> t) {
                for(Matriz casillaConMina: t){
                    /**
                    var buzzer = arduino.getPin(10);
                    try {
                        buzzer.setMode(Pin.Mode.OUTPUT);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                     **/
                    botonesTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setText("*");
                    /**
                    try {
                        buzzer.setValue(1);
                        Thread.sleep(1000);
                        buzzer.setValue(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                     **/

                }
                mensajego();
            }
        });



        tablero.setEventoPartidawin(new Consumer<List<Matriz>>() {
            @Override
            public void accept(List<Matriz> t) {
                for(Matriz casillaConMina: t){
                    botonesTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setText("x");
                }
                mensajecongra();
            }
        });

        tablero.setEventoCasillaOpen(new Consumer<Matriz>() {
            @Override
            public void accept(Matriz matriz) {

                botonesTablero[matriz.getPosFila()][matriz.getPosColumna()].setEnabled(false);
                botonesTablero[matriz.getPosFila()][matriz.getPosColumna()].setText(matriz.getNumMinasa()==0?"":matriz.getNumMinasa()+"");
                /**
                try {
                    buzzer.setValue(1);
                    Thread.sleep(300);
                    buzzer.setValue(0);
                    Thread.sleep(300);
                    buzzer.setValue(1);
                    buzzer.setValue(0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                **/
            }

        });
        tablero.printTablero();
        pistas();


    }
    private int dummy_level(){
        if (turno%2 ==0) {
            new Matriz(random.nextInt(7), random.nextInt(7)).isOpen();
            return ++turno;
        }else {
            return ++turno;
        }

    }


    private void pistas(){
        if (contador%5==0) {
            lista.primerElemento();
            lista.eliminarPrimerElemento();
        }else{
            ++contador;
        }



    }

    private void cargarControles() {

        int posXReferencia = 25;
        int posYReferencia = 25;
        int anchoControl = 30;
        int altoControl = 30;

        botonesTablero = new JButton[numFilas][numColumnas];
        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                lista.agregarElemento((int) (i+(0.1*j)));
                if (i == 0 && j == 0) {
                    botonesTablero[i][j].setBounds(posXReferencia,
                            posYReferencia, anchoControl, altoControl);


                }else if (i==0 && j!=0){
                botonesTablero[i][j].setBounds(
                        botonesTablero[i][j-1].getX()+botonesTablero[i][j-1].getWidth(),
                        posYReferencia, anchoControl, altoControl);
                }else{
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i-1][j].getX(),
                            botonesTablero[i-1][j].getY()+botonesTablero[i-1][j].getHeight(),
                            anchoControl, altoControl);
            }
            botonesTablero[i][j].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnClick(e);
                }
            });

                getContentPane().add(botonesTablero[i][j]);
            }
        }

    }

    private void btnClick(ActionEvent e) {
        JButton btn=(JButton)e.getSource();
        String[] coordenada=btn.getName().split(",");
        int posFila=Integer.parseInt(coordenada[0]);
        int posColumna=Integer.parseInt(coordenada[1]);
        //JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);
        tablero.seleccionarCasilla(posFila,posColumna);

        //arduino.getPin(12).addEventListener(tablero);

    }




    }










