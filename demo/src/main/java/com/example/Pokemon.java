package com.example;

import java.util.List;

public class Pokemon {
    private String nombre;
    private int nivel;
    //Cada pokémon tiene stats base. Estas son inmutables.
    private final int vidaBase;
    private final int ataqBase;
    private final int defBase;
    private final int ataqEspBase;
    private final int defEspBase;
    private final int velBase;
    //Las stats finales se calculan (ver método "calcularEstadísticas")
    private int ps;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    //Se guardan las stats originales aparte. Son privadas y no tienen setter, por lo que para las otras clases son pseudoconstantes (no escrictamente constantes porque no están declaradas con Final)
    private int ataqueOriginal;
    private int defensaOriginal;
    private int ataqueEspecialOriginal;
    private int defensaEspecialOriginal;
    private int velocidadOriginal;


    private ModificadorDeStats modificadorDeStats;    //Cada pokémon lleva sus propias modificaciones encima

    private List<Movimiento> movimientos;   //Moveset
    private String estado;  //Se refiere a estado alterado

    //Métodos constructores. Se puede crear un un pokémon con stats pero sin moveset y uno con todo.
    //Estrictamente se debería poder crear un pokémon vacío, pero manualmente habría que llamar al método calcularEstadisticas() después de asegurarse de colocar cada stat base.
    //Si bien también existen setters para las stats directamente, si esto fuera pokémon, se podría subir de nivel en medio de un combate y no se podrían recalcular las estadísticas.

    public Pokemon(String nombre, int vidaBase, int ataqBase, int defBase, int ataqEspBase, int defEspBase, int velBase, int nivel, String estado) {
        this.nombre = nombre;
        this.vidaBase = vidaBase;
        this.ataqBase = ataqBase;
        this.defBase = defBase;
        this.ataqEspBase = ataqEspBase;
        this.defEspBase = defEspBase;
        this.velBase = velBase;
        this.nivel = nivel;
        this.estado = estado;
        this.modificadorDeStats = new ModificadorDeStats();
        calcularEstadisticas();
    }

    public Pokemon(String nombre, int vidaBase, int ataqBase, int defBase, int ataqEspBase, int defEspBase, int velBase, int nivel, List<Movimiento> movimientos, String estado) {
        this.nombre = nombre;
        this.vidaBase = vidaBase;
        this.ataqBase = ataqBase;
        this.defBase = defBase;
        this.ataqEspBase = ataqEspBase;
        this.defEspBase = defEspBase;
        this.velBase = velBase;
        this.nivel = nivel;
        this.movimientos = movimientos;
        this.estado = estado;
        this.modificadorDeStats = new ModificadorDeStats();
        calcularEstadisticas();
    }

    //Stats originales. Se determinan una vez ya que el método sólo es llamado desde el constructor
    public void calcularEstadisticasPrimitivas(){
        this.ataqueOriginal = this.ataque;
        this.defensaOriginal = this.defensa;
        this.ataqueEspecialOriginal = this.ataqueEspecial;
        this.defensaEspecialOriginal = this.defensaEspecial;
        this.velocidadOriginal = this.velocidad;
    }
    
    //Stats actuales del pokémon. Si el pokémon subiera de nivel, se llamaría de nuevo a esta función
    public void calcularEstadisticas() {
        this.ps = 10 + (int)((nivel / 100.0) * (vidaBase * 2)) + nivel;
        this.ataque = 5 + (int)((nivel / 100.0) * (ataqBase * 2));
        this.ataqueEspecial = 5 + (int)((nivel / 100.0) * (ataqEspBase * 2));
        this.defensa = 5 + (int)((nivel / 100.0) * (defBase * 2));
        this.defensaEspecial = 5 + (int)((nivel / 100.0) * (defEspBase * 2));
        this.velocidad = 5 + (int)((nivel / 100.0) * (velBase * 2));
        calcularEstadisticasPrimitivas();
    }

    //Un print
    public void mostrarEstadisticas() {
        System.out.println(nombre + " Estadísticas:");
        System.out.println("PS: " + ps);
        System.out.println("Ataque: " + ataque);
        System.out.println("Ataque Especial: " + ataqueEspecial);
        System.out.println("Defensa: " + defensa);
        System.out.println("Defensa Especial: " + defensaEspecial);
        System.out.println("Velocidad: " + velocidad);
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAtaque() {
        return ModificadorDeStats.calcularModificador(ataqueOriginal, modificadorDeStats.getModAtaque());   //para obtener una stat se considera el modificador de stats
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaqueEspecial() {
        return ModificadorDeStats.calcularModificador(ataqueEspecialOriginal, modificadorDeStats.getModAtaqueEspecial());
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensa() {
        return ModificadorDeStats.calcularModificador(defensaOriginal, modificadorDeStats.getModDefensa());
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getDefensaEspecial() {
        return ModificadorDeStats.calcularModificador(defensaEspecialOriginal, modificadorDeStats.getModDefensaEspecial());
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public int getVelocidad() {
        return ModificadorDeStats.calcularModificador(velocidadOriginal, modificadorDeStats.getModVelocidad());
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAtaqueOriginal() {
        return ataqueOriginal;
    }

    public int getDefensaOriginal() {
        return defensaOriginal;
    }

    public int getAtaqueEspecialOriginal() {
        return ataqueEspecialOriginal;
    }

    public int getDefensaEspecialOriginal() {
        return defensaEspecialOriginal;
    }

    public int getVelocidadOriginal() {
        return velocidadOriginal;
    }

    public ModificadorDeStats getModificadorDeStats() {
        return modificadorDeStats;
    }
}