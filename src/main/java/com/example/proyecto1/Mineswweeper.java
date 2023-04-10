package com.example.proyecto1;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author 0YEYEY0
 */
public class Mineswweeper extends javax.swing.JFrame {

    int numFilas = 8;
    int numColumnas = 8;
    int numMinas = 10;

    JButton[][] botonesTablero;


    /**
     *
     */
    public Mineswweeper() {
        cargarControles();


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
        JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);


    }

}









