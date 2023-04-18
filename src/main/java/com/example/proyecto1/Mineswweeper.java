package com.example.proyecto1;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;


/**
 *
 * @author 0YEYEY0
 */
public class Mineswweeper extends javax.swing.JFrame {

    int numFilas = 8;
    int numColumnas = 8;
    int numMinas = 10;
    ListaSimple lista = new ListaSimple();

    int contador = 0;

    Random random = new Random();

    int turno = random.nextInt();




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
                    botonesTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setText("*");

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

    }

}









