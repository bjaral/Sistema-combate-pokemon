package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {    public static Random rand = new Random();
    
    public static void main(String[] args) {
        //Crear ambos pokemon con sus estadísticas
        Pokemon pikachu = new Pokemon("Pikachu", 35, 55, 50, 40, 50, 90, 50, "Sin estado");
        pikachu.mostrarEstadisticas();

        Pokemon vulpix = new Pokemon("Vulpix", 38, 41, 40, 50, 65, 65, 55, "Sin estado");
        System.out.println("");
        vulpix.mostrarEstadisticas();
        
        //Crear movimientos
        Movimiento impactrueno = new MovimientoAtaqueConEfecto("Paralizar",10, "Impactrueno", 40, 100, "Especial");
        Movimiento fuegoFatuo = new MovimientoDeEfecto("Quemadura", "Fuego fatuo", 0, 55, "Estado");
        Movimiento latigo = new MovimientoDeEfecto("debuffDef", "Latigo", 0, 100, "Estado");
        Movimiento placaje = new Movimiento("Placaje", 35, 95, "Fisico");
        Movimiento ascuas = new MovimientoAtaqueConEfecto("Quemadura", 10, "Ascuas", 40, 100, "Especial");
        Movimiento colaDeAcero = new MovimientoAtaqueConEfecto("debuffDef",30,"Cola de acero", 100, 75, "Fisico");
        
        //Crear los movesets de cada pokémon
        List<Movimiento> movesetPikachu = new ArrayList();
        movesetPikachu.add(latigo);
        movesetPikachu.add(impactrueno);
        movesetPikachu.add(placaje);
        movesetPikachu.add(colaDeAcero);
        
        List<Movimiento> movesetVulpix = new ArrayList();
        movesetVulpix.add(latigo);
        movesetVulpix.add(fuegoFatuo);
        movesetVulpix.add(placaje);
        movesetVulpix.add(ascuas);
        
        pikachu.setMovimientos(movesetPikachu);
        vulpix.setMovimientos(movesetVulpix);
        
        Duelo(pikachu, vulpix);
    }
    
    //Se inicia el duelo y llama a Turno. Cuando termina de ejecutarse Turno, se dicta qué pokémon se debilitó
    public static void Duelo(Pokemon poke1, Pokemon poke2){
        int i = 1;
        Turno(poke1, poke2, i);
        
        String debilitado = (poke1.getPs()>0) ? poke2.getNombre() : poke1.getNombre();
        System.out.println(debilitado+" se ha debilitado");
    }
    
    //Método recursivo para hacer los turnos
    public static int Turno(Pokemon poke1, Pokemon poke2, int i){
        System.out.println("\n--------------------");
        System.out.println("----- TURNO "+i+" -----" );
        
        //Se establece que poke1 debe ser el pokémon más rápido, porque este siempre ataca primero
        //Si poke2 es más rápido, se crea una copia de poke1 para cambiar los valores de poke1 y poke2
        if (poke2.getVelocidad() > poke1.getVelocidad()){
            Pokemon temp = poke1;
            poke1 = poke2;
            poke2 = temp;
        }
        
        //Se escogen los movimientos de manera aleatoria y cada pokémon ataca a su contrincante
        int move1 = rand.nextInt(4);
        Atacar(poke1, poke2, poke1.getMovimientos().get(move1));
        
        //Poke2 sólo puede atacar si sigue vivo
        if (poke2.getPs() > 0){
            int move2 = rand.nextInt(4);
            Atacar(poke2, poke1, poke2.getMovimientos().get(move2));
        }
        
        //Con este return se evita que poke1 se queme si poke2 ya está muerto, porque si se quema podría llegar a morir y perder
        else{
            return 1;
        }
        
        //Si se llega a este if es porque ambos están vivos, entonces se verifica el daño por quemadura
        if (poke1.getPs()> 0){
            VerificarQuemadura(poke1);
            VerificarQuemadura(poke2);
        }
        
        //Si ambos siguen vivos, se muestran las stats y comienza el siguiente turno. Si no, se termina el combate
        if (poke1.getPs()>0 && poke2.getPs()>0){
            System.out.println("\nStats actuales: ");
            System.out.println("");
            poke1.mostrarEstadisticas();
            System.out.println("");
            poke2.mostrarEstadisticas();
            
            return Turno(poke1, poke2, i+1);
        }
        else{
            return 1;
        }
    }
    
    //Acción de atacar
    public static void Atacar(Pokemon atacante, Pokemon objetivo, Movimiento movimiento){
        System.out.println(atacante.getNombre()+" ha usado "+movimiento.getNombre());
        
        //Si el movimiento únicamente es un estado alterado o debuff, se llama a la función que aplica el efecto
        if (movimiento instanceof MovimientoDeEfecto movimientoDeEfecto){
            movimientoDeEfecto.aplicarEfecto(objetivo);
        }
        
        //Si no (es decir, es un ataque que hace daño), se ejecuta lo siguiente
        else{
            //Primero se ve si el ataque falla o no, si falla no tiene sentido seguir el turno
            if (Acierto(movimiento) == false){
                System.out.println("Pero falló");
            }
            
            //Si no falla, se determina si el ataque es físico o especial para saber con qué estadísticas va a funcionar el daño
            else{
                int A = ("Fisico".equals(movimiento.tipo)) ? atacante.getAtaque() : atacante.getAtaqueEspecial();
                int D = ("Fisico".equals(movimiento.tipo)) ? atacante.getDefensa() : atacante.getDefensaEspecial();

                //Se determina el daño infligido y se reduce la vida del oponente según ese daño
                int dano = Dano(A, D, atacante.getNivel(), movimiento);
                objetivo.setPs((int) (objetivo.getPs()-dano));
                System.out.println(atacante.getNombre()+" ha infligido "+dano+" de dano");
                
                //Si el ataque tenía algún efecto adicional (probabilidad de inmovilizar, debuff, etc.) se aplica.
                if (movimiento instanceof MovimientoAtaqueConEfecto movimientoAtaqueConEfecto){
                    movimientoAtaqueConEfecto.aplicarEfecto(objetivo);
                }
            }
        }
    }
    
    //Si el usuario está quemado, pierde 5 de vida
    public static void VerificarQuemadura(Pokemon pokemon){
        if (pokemon.getEstado().equals("Quemado")){
            pokemon.setPs(pokemon.getPs()-5);
            System.out.println(pokemon.getNombre()+" ha recibido daño por quemaduras");
        }
    }
    

    //Retorna si acierta o no el ataque
    public static boolean Acierto(Movimiento movimiento){
        int resultado = rand.nextInt(100);
        boolean acierto = !(resultado>=movimiento.getPrecision());
        return acierto;
    }

    //Retorna el daño infligido por un movimiento
    public static int Dano(int A, int D, int N, Movimiento movimiento){
        // A: ataque del atacante
        // D: defensa del objetivo
        // V: variación del daño del 85 al 100% de daño total
        // C: multiplicador por daño crítico
        // N: nivel del atacante
        
        int dano;
        int V = rand.nextInt(16) + 85;
        int C = 1;
        int P = movimiento.potencia;
        
        //Se considera 5% de crítico de manera totalmente arbitraria, en realidad esta depende del movimiento y si el pokémon tiene algún estado alterado
        //Si consideramos eso, lo que se me ocurre es que el movimiento tenga una variable probCrit y un método propio que determine si es o no crítico
        //Los estados alterados se pueden considerar por separado, como hice con la clase ModificadorDeStats y los getter en la clase Pokemon
        int critProb = rand.nextInt(100);
        if (critProb < 5){
            System.out.println("¡Golpe crítico!");
            C = 2;
        }

        dano = (int) Math.round(0.01*V*((0.2*N+1)*A*P/(25*D)+2)*C);        //Cálculo final del daño
        return dano;
    }
}