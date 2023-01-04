//Baraja.java
package recursos;

import java.util.Random;

/**
 * Crea Baraja española de 40 cartas. No hay 8 y 9.
 */
public class Baraja {
    // Cantidad total cartas en baraja
    private final int NUM_CARTAS = 40;
    // lista cartas
    private Carta[] cartas = new Carta[NUM_CARTAS];
    // el indice de la primera carta sin dar. A las cartas sin dar le llamo mazo.
    int primeraMazo;

    /*
     * Contructor para el objeto Baraja
     */
    public Baraja() {
        // crea una baraja ordenada por palos y números
        int ultimaCarta = 0;
        // Por palo
        for (Palo p : Palo.values()) {
            // Por números en cada palo
            for (int j = 0; j < 12; j++) {
                if ((j == 7 || j == 8)) {
                    continue;
                }
                cartas[ultimaCarta] = new Carta(p, j + 1);
                ultimaCarta++;
            }
        }
    }

    public void barajar() {
        // baraja el mazo, es decir, la cartas sin dar
        Random r = new Random();
        for (int i = primeraMazo; i < cartas.length; i++) {
            int posicionAzar = r.nextInt(cartas.length - primeraMazo) + primeraMazo;
            Carta temp = cartas[i];
            cartas[i] = cartas[posicionAzar];
            cartas[posicionAzar] = temp;
        }
    }

    /**
     * Coge cartas del mazo para dar
     * si no hay suficientes cartas o el mazo está vacío se devuelve array vacio
     * 
     * @param numCartasDar Número de cartas a repartir
     * @return
     */
    public Carta[] darCartas(int numCartasDar) {

        Carta[] cartasParaDar;
        int cartasEnMazo = cartas.length - primeraMazo;

        // Si no hay cartas suficientes
        if (cartasEnMazo < numCartasDar) {
            cartasParaDar = new Carta[0];

        } else { // Si hay cartas
            cartasParaDar = new Carta[numCartasDar];
            int i;
            for (i = 0; i < cartasParaDar.length; i++) {
                cartasParaDar[i] = cartas[i + primeraMazo];
            }
            primeraMazo = i + primeraMazo;
        }
        return cartasParaDar;
    }
}
