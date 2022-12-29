package sieteymedia;

import recursos.*;

// lógica de negocio
public class SieteYMedia {
    Baraja baraja;
    Carta[] cartasJugador;
    Carta[] cartasBanca;

    void jugar() {
        turnoJugador();
        turnoBanca();
        
    }
    void turnoJugador() {
        char opc = 'C';

        // obligamos a que como mínimo se tenga 1 carta
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");
        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];

            // insertamos c en las cartas del jugador
            insertarCartaEnArray(cartasJugador, c);

            // mostramos cartas y su valor, si se pasa se sale del bucle
            System.out.println("Éstas son tus cartas jugador:");
            mostrarCartas(cartasJugador);
            double valor = valorCartas(cartasJugador);
            System.out.println("\n\tValor de cartas: " + valor);
            if (valor < 7.5) {

                // suponemos que el usuario teclea bien !!!
                System.out.println("\n¿Pides [C]arta o te [P]lantas?");
                opc = sc.next().trim().toUpperCase().charAt(0);
            }
        }
    }

    void turnoBanca() {
        
        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = valorCartas(cartasJugador);
        if (valorCartasJugador > 7.5) {
            System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return;
        }
        System.out.println("\n\nTurno de banca ...");
        // juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
        System.out.println("Éstas son mis cartas:");
        mostrarCartas(cartasBanca);
        System.out.println("\nValor de mis cartas(banca): " + valorCartas(cartasBanca));
        if (valorCartas(cartasBanca) > 7.5) {
            System.out.println("me pasé, ganas tú,jugador");
        } else {
            System.out.println("Gana la banca");
        }
    }

    double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int val;
        int i = 0;
        while (cartas[i] != null) {
            val = cartas[i].getNumero();
            total += (val > 7) ? 0.5 : val;
            i++;
        }
        return total;
    }

    void insertarCartaEnArray(Carta[] cartas, Carta c) {
        // inserta al final detectando el primer null
        int i = 0;
        while (cartas[i] != null) {
            i++;
        }
        cartas[i] = c;
    }

    void mostrarCartas(Carta[] cartas) {
        int i = 0;
        while (cartas[i] != null) {
            System.out.print("\t" + cartas[i]);
            i++;
        }
    }
}
