package com.example.proyecto1;


/**
 *
 * @author 0YEYEY0
 */
public class Tablero {
    Matriz[][] matriz;

    int numFilas;
    int numColumnas;
    int numMinas;


    public Tablero(int numFilas, int numColumnas, int numMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = numMinas;
        this.inicializarCasillas();
    }

    public void inicializarCasillas() {
        matriz = new Matriz[this.numFilas][this.numColumnas];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = new Matriz(i, j);
            }
        }
        generarMinas();
    }

    private void generarMinas() {
        int minasGeneradas = 0;
        while (minasGeneradas != numMinas) {
            int posTmpFila = (int) (Math.random() * matriz.length);
            int posTmpColumna = (int) (Math.random() * matriz[0].length);
            if (!matriz[posTmpFila][posTmpColumna].isMina()) {
                matriz[posTmpFila][posTmpColumna].setMina(true);
                minasGeneradas++;
            }
        }

    }

    public void imprimirTablero() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j].isMina() ? "*" : "0");
            }
            System.out.println("");
        }
    }
    public static void main(String[] arg){
        Tablero tablero = new Tablero(8,8,7);
        tablero.imprimirTablero();
    }
}


