package com.example.proyecto1;

public class Casilla {
    private int posFila;
    private int posColumna;
    private boolean mina;


    public Casilla(int posFila, int posColumna) {
        this.posFila = posFila;
        this.posColumna = posColumna;
    }

    public int getPosFila() {
        return posFila;
    }

    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }




}

