package com.example.proyecto1;

public class Matriz {
    private int posFila;
    private int posColumna;
    private boolean mina;
    private int numMinasa;

    private boolean open;


    public Matriz(int posFila, int posColumna) {
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

    public int getNumMinasa() {
        return numMinasa;
    }

    public void setNumMinasa(int numMinasa) {
        this.numMinasa = numMinasa;
    }


    public void incNumMinasAround(){
        this.numMinasa++;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }






}

