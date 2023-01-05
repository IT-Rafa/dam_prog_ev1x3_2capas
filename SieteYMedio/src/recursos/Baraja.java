//Baraja.java
package recursos;

import java.util.Random;

/**
 * Crea Baraja española de 40 cartas. No hay 8 y 9.
 */
public class Baraja {
    // ATTRIBUTES
    // Cantidad total cartas en baraja
    private final int NUM_CARTAS = 40;
    // lista cartas
    private Carta[] cartas = new Carta[NUM_CARTAS];
    // Índice de la primera carta no repartida. inicia en 0
    int primeraMazo;

    // CONSTRUCTORS
    /*
     * Contructor para el objeto Baraja.
     * Creamos las cartas y las añadimos, ordenadas por palo y número
     */
    public Baraja() {
        // variable de última carta incluida.
        int ultimaCarta = 0;

        // Por palo
        for (Palo p : Palo.values()) {
            // Por números en cada palo
            for (int j = 0; j < 12; j++) {
                if ((j == 7 || j == 8)) {
                    continue;
                }
                // Añadimos carta a la baraja
                cartas[ultimaCarta] = new Carta(p, j + 1);
                ultimaCarta++;
            }
        }
    }

    /**
     * barajamos las cartas del mazo
     */
    public void barajar() {
        // Creamos nº para aleatorio
        Random r = new Random();
        // Recorremos cartas, desde posición 0
        for (int i = primeraMazo; i < cartas.length; i++) {
            // Asignamos posición para cambiar la carta, Desde cartas ya barajadas
            int posicionAzar = r.nextInt(cartas.length - primeraMazo) + primeraMazo;

            // Intercambiamos carta en la posición sin cambiar la carta en posición al azar
            Carta temp = cartas[i];
            cartas[i] = cartas[posicionAzar];
            cartas[posicionAzar] = temp;
        }
    }

    /**
     * Coge cartas del mazo para dar
     * si no hay suficientes cartas o el mazo está vacío se devuelve array longitud
     * 0
     * 
     * @param numCartasDar Número de cartas a repartir
     * @return Cartas a repartir
     */
    public Carta[] darCartas(int numCartasDar) {

        Carta[] cartasParaDar;
        int cartasEnMazo = cartas.length - primeraMazo;

        // Si no hay cartas sin repartir suficientes preparamos array Carta con long 0
        if (cartasEnMazo < numCartasDar) {
            cartasParaDar = new Carta[0];

        } else { // Si hay cartas sin repartir, damos carta
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
