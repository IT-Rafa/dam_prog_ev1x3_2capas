package sieteymedia;

/**
 * Interface que indica los métodos a construir en la clase
 */
public interface Inter {
    public void inicio();

    public void showUser(String msg, boolean newLine);

    public void showUser(String msg);

    public String askUser(String msg, boolean newLine);

    public String askUser(String msg);
}
