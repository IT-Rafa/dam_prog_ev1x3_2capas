package sieteymedia;

import java.util.Scanner;

// lógica de presentación.
/**
 * Lógica presentación sieteymedia.
 * incluye un main para ejecutarlo.
 * Inicia el juego, Muestra los mensajes y pide datos por texto en consola.
 * 
 * Nota: Le añadi iterface para que poder usar otras clases con misma interface 
 */
public class InterfaceConsola implements Inter {
    // ATTRIBUTTES
    // Consola donde se ejecuta
    private Scanner sc = new Scanner(System.in);

    // Partida a jugar, se le envía este objeto
    private SieteYMedia game = new SieteYMedia(this);;

    // STATIC METHODS
    public static void main(String[] args) {

        InterfaceConsola gameConsola = new InterfaceConsola();
        gameConsola.inicio();
    }
    // CLASS METHODS

    /**
     * Arranca juego y se despide
     */
    public void inicio() {
        game.jugar();
        System.out.println("Adios");
    }

    /**
     * Muestra el mensaje por consola, con o sin salto de línea
     * 
     * @param msg String con mensaje a mostrar
     * @param newLine boolean que indica si habrá salto línea al final
     */
    public void showUser(String msg, boolean newLine) {
        if (newLine == true) {
            System.out.println(msg);
        } else {
            System.out.print(msg);
        }
    }

    /**
     * Muestra el mensaje por consola con salto de línea al final
     * 
     * @param msg String con mensaje a mostrar
     */
    public void showUser(String msg) {
        showUser( msg, true);
    }

    /**
     * Muestra mensaje por consola, con o sin salto de línea, y espera 
     * respuesta usuario
     * 
     * @param msg String con mensaje a mostrar
     * @param newLine boolean que indica si habrá salto línea al final
     * @return String con la respuesta del usuario
     */
    public String askUser(String msg, boolean newLine) {
        if (newLine == true) {
            System.out.println(msg);
        } else {
            System.out.print(msg);
        }
        return sc.nextLine().trim().toUpperCase();
    }

    /**
     * Muestra mensaje por consola con salto de línea y espera 
     * respuesta usuario
     * 
     * @param msg String con mensaje a mostrar
     * @param newLine boolean que indica si habrá salto línea al final
     * @return String con la respuesta del usuario
     */
    public String askUser(String msg) {
        return askUser(msg, true);
    }
}
