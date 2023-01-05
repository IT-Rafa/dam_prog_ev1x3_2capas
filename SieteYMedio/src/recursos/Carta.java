//Carta.java
package recursos;

/**
 * Carta con palo y número
 */
public class Carta {
    // ATTRIBUTES
    private Palo palo;
    private int numero;

    // CONSTRUCTORS
    /**
     * Creamos carta con palo y número
     */
    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    // GETTER & SETTERS
    /**
     * Devuelve el palo de la carta
     * 
     * @return palo de la carta
     */

    public String getPalo() {
        return palo.toString();
    }

    /**
     * Devuelve el número de la carta
     * 
     * @return número de la carta
     */
    public int getNumero() {
        return numero;
    }

    // METHODS
    /**
     * Muestra el palo y el número de la carta
     */
    @Override
    public String toString() {
        return "(" + palo + ", " + numero + ')';
    }
}
