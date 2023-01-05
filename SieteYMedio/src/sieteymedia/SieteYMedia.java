package sieteymedia;

import recursos.*;

/**
 * lógica de negocio.
 * Prepara e inicia el juego partida sieteymedio
 */
public class SieteYMedia {
    // ATTRIBUTES
    private Baraja baraja;
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;
    private Inter inter;
    // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
    // nos pasamos
    // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
    // vemos que a partir de 15 cartas siempre se pasas
    private final int MAXCARTAS = 15;

    // CONSTRUCTORS
    /**
     * Prepara partida. Baraja y prepara cartas usuarios
     * 
     * @param inter Objeto con interface inter para interactuar con usuario
     */
    public SieteYMedia(Inter inter) {
        this.baraja = new Baraja();
        this.baraja.barajar();

        this.cartasJugador = new Carta[MAXCARTAS];
        this.cartasBanca = new Carta[MAXCARTAS];
        this.inter = inter;
    }

    // METHODS
    /**
     * Presenta juego y los turnos partida
     */
    public void jugar() {
        presentarJuego();
        turnoJugador();
        turnoBanca();
    }

    /**
     * Muestra información de cómo funciona el sieteymedio
     */
    private void presentarJuego() {
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

    /**
     * Turno usuario. Usuario recibe carta, se muestra el valor y le pregunta si
     * quiere pedir mas,
     * salvo que el valor de las cartas pase de 7 y medio.
     */
    private  void turnoJugador() {
        char opc = 'C';
        inter.showUser("Como minimo recibes una carta, luego puedes decidir si seguir o plantarte");

        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            // Cogemos la carta a repartir
            Carta c = baraja.darCartas(1)[0];

            // Añadimos esa carta en las cartas del jugador
            insertarCartaEnArray(cartasJugador, c);

            // Mostramos cartas del jugador
            inter.showUser("Estas son tus cartas jugador:");
            mostrarCartas(cartasJugador);

            // Mostramos valor
            double valor = valorCartas(cartasJugador);
            inter.showUser("\n\tValor de cartas: " + valor);

            // Si es inferior a 7 y medio, pregunta al usuario si quiere otra
            if (valor < 7.5) {
                // suponemos que el usuario teclea bien !!!
                opc = inter.askUser("\nPides [C]arta o te [P]lantas ? ",
                        false).charAt(0);
            }
        }
    }

    /**
     * Turno banca. Banca pide carta hasta alcanzar mínimo el valor de las cartas
     * del jugador.
     * Si supera siete y medio, pierde.
     */
    private void turnoBanca() {

        // Comprobamos valor cartas jugador
        double valorCartasJugador = valorCartas(cartasJugador);
        // Si jugador supero el siete y medio. Gana banca y finaliza partida
        if (valorCartasJugador > 7.5) {
            inter.showUser("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return;
        }
        // En caso que no, banca pide carta hasta alcanzar valor cartas jugador, mínimo
        inter.showUser("\n\nTurno de banca ...");

        // juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }

        // Mostramos lista de cartas de jugador banca
        inter.showUser("Estas son mis cartas:");
        mostrarCartas(cartasBanca);
        inter.showUser("\nValor de mis cartas(banca): " + valorCartas(cartasBanca));

        // Comprobamos si se supero siete y medio. si lo hizo, gana jugador,
        // si no, gana banca
        if (valorCartas(cartasBanca) > 7.5) {
            inter.showUser("me pase, ganas tu, jugador");
        } else {
            inter.showUser("Gana la banca");
        }
    }

    /**
     * Devuelve el valor de una lista de cartas de jugador
     * 
     * @param cartas lista de cartas de jugador a contar
     * @return double con valor de las cartas
     */
    private double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int val;
        int i = 0;
        // Recorremos cartas jugasdor hasta que encontrarnos con carta null
        while (cartas[i] != null) {
            // Cogemos número de la carta
            val = cartas[i].getNumero();
            // Añadimos el número o 0.5, según valor y sumamos al total
            total += (val > 7) ? 0.5 : val;
            i++;
        }
        return total;
    }

    /**
     * Añade carta a una lista de cartas de jugador
     * 
     * @param cartas lista cartas jugador
     * @param c      carta a añadir
     */
    private void insertarCartaEnArray(Carta[] cartas, Carta c) {
        // inserta al final detectando el primer null
        int i = 0;
        // llega a la primera carta null
        while (cartas[i] != null) {
            i++;
        }
        // Añade carta
        cartas[i] = c;
    }

    /**
     * Mostramso las cartas de una lista de cartas de jugador
     * 
     * @param cartas lista cartas jugador
     */
    private void mostrarCartas(Carta[] cartas) {
        int i = 0;
        // llega a la primera carta null
        while (cartas[i] != null) {
            inter.showUser("\t" + cartas[i]);
            i++;
        }
    }
}
