package com.example;

import java.util.Random;

public class MovimientoDeEfecto extends Movimiento implements EfectoDeEstado{

    private final String nombreEfecto;


    public MovimientoDeEfecto(String nombreEfecto, String nombre, int potencia, int precision, String tipo) {
        super(nombre, potencia, precision, tipo);
        this.nombreEfecto = nombreEfecto;
    }

    //Aplicar efecto considera todos los casos según el nombre del efecto para todos los efectos en el juego
    //Al ser estos efectos parte de un movimiento de efecto, puede fallar
    @Override
    public void aplicarEfecto(Pokemon objetivo) {
        Random rand = new Random();
        int resultado = rand.nextInt(100);

        switch (this.nombreEfecto) {
            case "debuffAtaq" -> {
                if (this.precision >= resultado) {
                    if (objetivo.getModificadorDeStats().getModAtaque() > -6){ //El modificador no puede ser mayor a 6 ni menor que -6
                        objetivo.getModificadorDeStats().setModAtaque(objetivo.getModificadorDeStats().getModAtaque() - 1);
                        objetivo.setAtaque(ModificadorDeStats.calcularModificador(objetivo.getAtaqueOriginal(), objetivo.getModificadorDeStats().getModAtaque()));
                        System.out.println("El ataque de " + objetivo.getNombre() + " ha disminuido");
                    } else{
                        System.out.println("Las estadísticas de "+ objetivo.getNombre()+" no pueden disminuir más");
                    }
                } else {
                    System.out.println("Pero falló");
                }
            }
            
            case "debuffDef" -> {
                if (this.precision >= resultado) {
                    if (objetivo.getModificadorDeStats().getModDefensa() >-6){
                        objetivo.getModificadorDeStats().setModDefensa(objetivo.getModificadorDeStats().getModDefensa() - 1);
                        objetivo.setDefensa(ModificadorDeStats.calcularModificador(objetivo.getDefensaOriginal(), objetivo.getModificadorDeStats().getModDefensa()));
                        System.out.println("La defensa de " + objetivo.getNombre() + " ha disminuido");
                    } else{
                        System.out.println("Las estadísticas de "+ objetivo.getNombre()+" no pueden disminuir más");
                    }
                } else {
                    System.out.println("Pero falló");
                }
            }
            
            case "Paralizar" -> {
                if (this.precision >= resultado) {
                    if ("Sin estado".equals(objetivo.getEstado())){
                        objetivo.setVelocidad((int) Math.round(objetivo.getVelocidad() / 2));
                        objetivo.setEstado("Paralizado");
                        System.out.println(objetivo.getNombre() + " se ha paralizado");
                    }
                    else{
                        System.out.println(objetivo.getNombre()+" ya tiene un efecto de estado");
                    }
                } else {
                    System.out.println("Pero falló");
                }
            }
            
            case "Quemadura" -> {
                if (this.precision >= resultado) {
                    if ("Sin estado".equals(objetivo.getEstado())){
                        objetivo.setAtaque((int) Math.round(objetivo.getAtaque() / 2));
                        objetivo.setEstado("Quemado");
                        System.out.println(objetivo.getNombre() + " se ha quemado");
                    }
                    else{
                        System.out.println(objetivo.getNombre()+" ya tiene un efecto de estado");
                    }
                } 
                else {
                    System.out.println("Pero falló");
                }
            }

            default -> System.out.println("No se ha agregado el efecto, vuelva pronto.");
        }
    }
}
