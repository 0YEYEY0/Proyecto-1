package com.example.proyecto1;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author 0YEYEY0
 */
public class Tablero {
    Matriz[][] matriz;

    int numFilas;
    int numColumnas;
    int numMinas;

    int botonAbierto;

    boolean GameOver;

    Consumer<List<Matriz>> eventoPartidawin;
    Consumer<List<Matriz>> eventoPartidaPerdida;
    Consumer<Matriz> eventoCasillaOpen;


    public Tablero(int numFilas, int numColumnas, int numMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = numMinas;
        this.creaCasillas();
        System.out.println(matriz[0][0]);
    }

    /**
     * Metodo que genera la matriz que va en el tablero para el busca minas
     */
    public void creaCasillas() {
        matriz = new Matriz[this.numFilas][this.numColumnas];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = new Matriz(i, j);
            }
        }
        genMinas();

    }

    /**
     * Metodo para generar las minas en la matriz
     */
    private void genMinas() {
        int minasGeneradas = 0;
        while (minasGeneradas != numMinas) {
            int posTmpFila = (int) (Math.random() * matriz.length);
            int posTmpColumna = (int) (Math.random() * matriz[0].length);
            if (!matriz[posTmpFila][posTmpColumna].isMina()) {
                matriz[posTmpFila][posTmpColumna].setMina(true);
                minasGeneradas++;
            }
        }
        actNumMinasAround();
    }

    /**
     * Metodo para imprimir el tablero
     */
    public void printTablero() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                System.out.print(matriz[i][j].isMina() ? "*" : "0");
            }
            System.out.println("");
        }
    }






    /**
     * Metodo para imprimir las casillas que indican a que distancia hay minas
     */
    private void printClue() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j].getNumMinasa());
            }
            System.out.println("");
        }
    }

    /**
     * Metodo para actualizar las minas que se encuentran alrededor de ciertas casillas
     */
    private void actNumMinasAround() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].isMina()) {
                    List<Matriz> casillasAlrededor = obtCasillasAlr(i, j);
                    casillasAlrededor.forEach((c) -> c.incNumMinasAround());
                }
            }
        }
    }

    /**
     * metodo que da las casillas que estan alrededor
     *
     * @param posFila
     * @param posColumna
     * @return minas alrededor
     */
    private List<Matriz> obtCasillasAlr(int posFila, int posColumna) {
        List<Matriz> listaCasillas = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            int tmpPosFila = posFila;
            int tmpPosColumna = posColumna;
            switch (i) {
                case 0:
                    tmpPosFila--;
                    break;
                case 1:
                    tmpPosFila--;
                    tmpPosColumna++;
                    break;
                case 2:
                    tmpPosColumna++;
                    break;
                case 3:
                    tmpPosColumna++;
                    tmpPosFila++;
                    break;
                case 4:
                    tmpPosFila++;
                    break;
                case 5:
                    tmpPosFila++;
                    tmpPosColumna--;
                    break;
                case 6:
                    tmpPosColumna--;
                    break;
                case 7:
                    tmpPosFila--;
                    tmpPosColumna--;
                    break;
            }

            if (tmpPosFila >= 0 && tmpPosFila < this.matriz.length
                    && tmpPosColumna >= 0 && tmpPosColumna < this.matriz[0].length) {
                listaCasillas.add(this.matriz[tmpPosFila][tmpPosColumna]);
            }

        }
        return listaCasillas;
    }

    List<Matriz> BotonesMinas() {
        List<Matriz> BotonesMinas = new LinkedList<>();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].isMina()) {
                    BotonesMinas.add(matriz[i][j]);
                }
            }
        }
        return BotonesMinas;
    }

    public void seleccionarCasilla(int posFila, int posColumna) {
        eventoCasillaOpen.accept(this.matriz[posFila][posColumna]);
        if (this.matriz[posFila][posColumna].isMina()) {
            eventoPartidaPerdida.accept(BotonesMinas());
        }else if (this.matriz[posFila][posColumna].getNumMinasa()==0){
            marcarCasillaAbierta(posFila, posColumna);
            List<Matriz> casillasAlrededor=obtCasillasAlr(posFila, posColumna);
            for(Matriz casilla: casillasAlrededor){
                if (!casilla.isOpen()){
                    seleccionarCasilla(casilla.getPosFila(), casilla.getPosColumna());
                }
            }
        }else{
            marcarCasillaAbierta(posFila, posColumna);
        }
        if (partidaGanada()){
            eventoPartidawin.accept(BotonesMinas());
        }
    }

    void marcarCasillaAbierta(int posFila, int posColumna){
        if (!this.matriz[posFila][posColumna].isOpen()){
            botonAbierto++;
            this.matriz[posFila][posColumna].setOpen(true);
        }
    }

    boolean partidaGanada(){
        return botonAbierto>=(numFilas*numColumnas)-numMinas;
    }




    void marcarBotonAbierto(int posFila, int posColumna){
        if (!this.matriz[posFila][posColumna].isOpen()){
            botonAbierto++;
            this.matriz[posFila][posColumna].setOpen(true);
        }
    }


    boolean win(){
        return botonAbierto>=(numFilas*numColumnas)-numMinas;
    }









    public static void main(String[] args) {
        Tablero tablero = new Tablero(8,8,7);
        tablero.printTablero();
        System.out.println("////////////////////");
        tablero.printClue();
    }

    public void setEventoPartidaPerdida(Consumer<List<Matriz>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    public void setEventoCasillaOpen(Consumer<Matriz> eventoCasillaOpen) {
        this.eventoCasillaOpen = eventoCasillaOpen;
    }

    public void setEventoPartidawin(Consumer<List<Matriz>> eventoPartidaGanada) {
        this.eventoPartidawin = eventoPartidaGanada;
    }



}


