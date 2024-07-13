package com.example;

public class Movimiento {
    protected String nombre;
    protected int potencia;
    protected int precision;
    protected String tipo;
    
    //Características propias de un movimiento. También se podría considerar la probabilidad de crítico, el elemento, y el objetivo (uno mismo, pokémon adyacente o a todos en el campo).
    //Acá con tipo se refiere a si es físico, especial o de estado (estado engloba estados alterados y buffs/debuffs, pero se podrían separar para mayor orden).
    public Movimiento(String nombre, int potencia, int precision, String tipo) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.precision = precision;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Un print
    public void mostrarDetalles() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Potencia: " + potencia);
        System.out.println("Precisión: " + precision);
        System.out.println("Tipo: " + tipo);
    }
}
