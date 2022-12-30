package sieteymedia;

import java.util.Scanner;

// lógica de presentación.
public class InterfaceConsola {
    private Scanner sc = new Scanner(System.in);

    private SieteYMedia game;

    public static void main(String[] args) {
        new InterfaceConsola();
        
    }

    public InterfaceConsola() {
        game = new SieteYMedia(this);
        presentarJuego();
        jugar();
    }

    void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println(
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y pierde.");
        System.out.println(
                "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println(
                "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println(
                "- La banca no se puede plantar y tiene que empatar o superar la puntuación del jugador sin pasarse.");
        System.out.println(
                "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    void jugar() {
        game.turnoJugador();
        game.turnoBanca();
        System.out.println("Adios");
    }

    public void showUser(String msg) {
            System.out.println(msg);
        
    }
    public void showUser(String msg, boolean newLine) {
        if (newLine == true) {
            System.out.println(msg);
        } else {
            System.out.print(msg);
        }
    }

    public String askUser(String msg, boolean newLine) {
        if (newLine == true) {
            System.out.println(msg);
        } else {
            System.out.print(msg);
        }
        return sc.nextLine().trim().toUpperCase();
    }
}
