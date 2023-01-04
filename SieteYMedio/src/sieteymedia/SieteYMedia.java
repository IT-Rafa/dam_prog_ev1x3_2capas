package sieteymedia;

import recursos.*;

// lógica de negocio
public class SieteYMedia {
    // ATRIBUTTOS
    private Baraja baraja; 
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;
    private Inter inter;

    public void jugar() {
        presentarJuego();
        turnoJugador();
        turnoBanca();
    }

    public SieteYMedia(InterfaceConsola inter) {
        this.baraja = new Baraja();
        this.baraja.barajar();
        // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
        // nos pasamos
        // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
        // vemos que a partir de 15 cartas siempre se pasas
        this.cartasJugador = new Carta[15];
        this.cartasBanca = new Carta[15];
        this.inter = inter;
    }

    public void presentarJuego() {
        inter.showUser("- El usuario es el jugador y el ordenador la banca.\n" +
                "- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.\n" +
                "- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.\n" +
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.\n"
                +
                "- El jugador va pidiendo cartas a la banca de una en una.\n" +
                "- El jugador puede plantarse en cualquier momento.\n" +
                "- Si la suma de los valores de las cartas sacadas es superior a 7 y medio, el jugador se pasa de siete y medio' y pierde.\n"
                +
                "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta está obligada a sacar cartas hasta empatar o superar al jugador.\n"
                +
                "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.\n"
                +
                "- La banca no se puede plantar y tiene que empatar o superar la puntuación del jugador sin pasarse.\n"
                +
                "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.\n"
                +
                "\nEmpecemos!!!\n");
    }

    public void turnoJugador() {
        char opc = 'C';
        inter.showUser("Como minimo recibes una carta, luego puedes decidir si seguir o plantarte");

        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];

            // insertamos c en las cartas del jugador
            insertarCartaEnArray(cartasJugador, c);

            // mostramos cartas y su valor, si se pasa se sale del bucle
            inter.showUser("Estas son tus cartas jugador:");
            mostrarCartas(cartasJugador);
            double valor = valorCartas(cartasJugador);
            inter.showUser("\n\tValor de cartas: " + valor);
            if (valor < 7.5) {
                // suponemos que el usuario teclea bien !!!
                opc = inter.askUser("\nPides [C]arta o te [P]lantas ? ", false).charAt(0);
            }
        }
    }

    void turnoBanca() {

        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = valorCartas(cartasJugador);
        if (valorCartasJugador > 7.5) {
            inter.showUser("Jugador, te has pasado en tu jugada anterior, gana la banca", true);
            return;
        }
        inter.showUser("\n\nTurno de banca ...", true);
        // juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
        inter.showUser("Estas son mis cartas:", true);
        mostrarCartas(cartasBanca);
        inter.showUser("\nValor de mis cartas(banca): " + valorCartas(cartasBanca), true);
        if (valorCartas(cartasBanca) > 7.5) {
            inter.showUser("me pase, ganas tu, jugador", true);
        } else {
            inter.showUser("Gana la banca", true);
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
            inter.showUser("\t" + cartas[i], true);
            i++;
        }
    }
}
