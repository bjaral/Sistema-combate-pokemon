package com.example;

import java.util.Random;

public class MovimientoAtaqueConEfecto extends Movimiento implements EfectoDeEstado{
    
    private String nombreEfecto;
    private int probabilidadAplicarEfecto;

    public MovimientoAtaqueConEfecto(String nombreEfecto, int probabilidadAplicarEfecto, String nombre, int potencia, int precision, String tipo) {
        super(nombre, potencia, precision, tipo);
        this.nombreEfecto = nombreEfecto;
        this.probabilidadAplicarEfecto = probabilidadAplicarEfecto;
    }
    
    //Aplicar efecto considera todos los casos según el nombre del efecto para todos los efectos en el juego
    //Al ser estos efectos partes de un ataque que tiene la posibilidad de aplicar un efecto, da igual que falle, por lo que no tiene un mensaje de fracaso
    @Override
    public void aplicarEfecto(Pokemon objetivo) {
        Random rand = new Random();
        int resultado = rand.nextInt(100);
        
        switch (this.nombreEfecto) {
            case "debuffAtaq" -> {
                if (this.probabilidadAplicarEfecto >= resultado){
                    if (objetivo.getModificadorDeStats().getModAtaque() > -6){ //El modificador no puede ser mayor a 6 ni menor que -6
                        objetivo.getModificadorDeStats().setModAtaque(objetivo.getModificadorDeStats().getModAtaque() - 1);
                        objetivo.setAtaque(ModificadorDeStats.calcularModificador(objetivo.getAtaqueOriginal(), objetivo.getModificadorDeStats().getModAtaque()));
                        System.out.println("El ataque de " + objetivo.getNombre() + " ha disminuido");
                    } else{
                        System.out.println("Las estadísticas de "+ objetivo.getNombre()+" no pueden disminuir más");
                    }
                }
            }
                
            case "debuffDef" -> {
                if (this.probabilidadAplicarEfecto >= resultado){
                    if (objetivo.getModificadorDeStats().getModDefensa() >-6){
                        objetivo.getModificadorDeStats().setModDefensa(objetivo.getModificadorDeStats().getModDefensa() - 1);
                        objetivo.setDefensa(ModificadorDeStats.calcularModificador(objetivo.getDefensaOriginal(), objetivo.getModificadorDeStats().getModDefensa()));
                        System.out.println("La defensa de " + objetivo.getNombre() + " ha disminuido");
                    } else{
                        System.out.println("Las estadísticas de "+ objetivo.getNombre()+" no pueden disminuir más");
                    }
                }
            }
                
            case "Paralizar" -> {
                if (this.probabilidadAplicarEfecto >= resultado){
                    if ("Sin estado".equals(objetivo.getEstado())){
                        objetivo.setVelocidad((int) Math.round(objetivo.getVelocidad() / 2));
                        objetivo.setEstado("Paralizado");
                        System.out.println(objetivo.getNombre() + " se ha paralizado");
                    }
                    else{
                        System.out.println(objetivo.getNombre()+" ya tiene un efecto de estado");
                    }
                }
            }
                
            case "Quemadura" -> {
                if (this.probabilidadAplicarEfecto >= resultado){
                    if ("Sin estado".equals(objetivo.getEstado())){
                        objetivo.setAtaque((int) Math.round(objetivo.getAtaque() / 2));
                        objetivo.setEstado("Quemado");
                        System.out.println(objetivo.getNombre() + " se ha quemado");
                    }
                    else{
                        System.out.println(objetivo.getNombre()+" ya tiene un efecto de estado");
                    }
                } 
            }
                
            default -> System.out.println("No se ha agregado el efecto todavía, vuelva pronto.");
        }
    }

    public String getNombreEfecto() {
        return nombreEfecto;
    }

    public void setNombreEfecto(String nombreEfecto) {
        this.nombreEfecto = nombreEfecto;
    }

    public int getProbabilidadAplicarEfecto() {
        return probabilidadAplicarEfecto;
    }

    public void setProbabilidadAplicarEfecto(int probabilidadAplicarEfecto) {
        this.probabilidadAplicarEfecto = probabilidadAplicarEfecto;
    }
}
