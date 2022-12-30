package sieteymedia;

import recursos.*;

// lógica de negocio
public class SieteYMedia {
    private Baraja baraja;
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;
    InterfaceConsola inter;

    public SieteYMedia(InterfaceConsola inter){
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

    public void turnoJugador(){
        char opc = 'C';
        inter.showUser("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");

        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];

            // insertamos c en las cartas del jugador
            insertarCartaEnArray(cartasJugador, c);

            // mostramos cartas y su valor, si se pasa se sale del bucle
            inter.showUser("Éstas son tus cartas jugador:");
            mostrarCartas(cartasJugador);
            double valor = valorCartas(cartasJugador);
            inter.showUser("\n\tValor de cartas: " + valor);
            if (valor < 7.5) {

                // suponemos que el usuario teclea bien !!!
                opc = inter.askUser("\n¿Pides [C]arta o te [P]lantas? ", false).charAt(0);
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
        inter.showUser("Éstas son mis cartas:", true);
        mostrarCartas(cartasBanca);
        inter.showUser("\nValor de mis cartas(banca): " + valorCartas(cartasBanca), true);
        if (valorCartas(cartasBanca) > 7.5) {
            inter.showUser("me pasé, ganas tú,jugador", true);
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
