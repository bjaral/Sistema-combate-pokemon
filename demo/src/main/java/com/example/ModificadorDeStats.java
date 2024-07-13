package com.example;

public class ModificadorDeStats {
    
    //Cada stat de un pokémon tiene como base el nivel de bonificación 0, eso significa que tiene el 100% de su estadística.
    private int modAtaque = 0;
    private int modDefensa = 0;
    private int modAtaqueEspecial = 0;
    private int modDefensaEspecial = 0;
    private int modVelocidad = 0;

    public int getModAtaque() {
        return modAtaque;
    }

    public void setModAtaque(int modAtaque) {
        this.modAtaque = modAtaque;
    }

    public int getModDefensa() {
        return modDefensa;
    }

    public void setModDefensa(int modDefensa) {
        this.modDefensa = modDefensa;
    }

    public int getModAtaqueEspecial() {
        return modAtaqueEspecial;
    }

    public void setModAtaqueEspecial(int modAtaqueEspecial) {
        this.modAtaqueEspecial = modAtaqueEspecial;
    }

    public int getModDefensaEspecial() {
        return modDefensaEspecial;
    }

    public void setModDefensaEspecial(int modDefensaEspecial) {
        this.modDefensaEspecial = modDefensaEspecial;
    }

    public int getModVelocidad() {
        return modVelocidad;
    }

    public void setModVelocidad(int modVelocidad) {
        this.modVelocidad = modVelocidad;
    }

    //Cuando el nivel de bonificación es distinto de 0, el multiplicador cambia. Si es 0, se retorna la estadística primitiva (int stat debe ser la stat primitiva del pokémon)
    public static int calcularModificador(int stat, int mod) {
        return switch (mod) {
            case -1 -> Math.round(stat * 0.67f);
            case -2 -> Math.round(stat * 0.5f);
            case -3 -> Math.round(stat * 0.4f);
            case -4 -> Math.round(stat * 0.33f);
            case -5 -> Math.round(stat * 0.29f);
            case -6 -> Math.round(stat * 0.25f);
            case 1 -> Math.round(stat * 1.5f);
            case 2 -> Math.round(stat * 2.0f);
            case 3 -> Math.round(stat * 2.5f);
            case 4 -> Math.round(stat * 3.0f);
            case 5 -> Math.round(stat * 3.5f);
            case 6 -> Math.round(stat * 4.0f);
            default -> stat;
        };
    }
}
