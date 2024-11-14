package TADs;

public class Asiento {
    private Seccion seccion;
    private int numeroAsiento;
    private Cliente cliente;

    public Asiento(Seccion seccion, int numeroAsiento) {
        this.seccion = seccion;
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "seccion=" + seccion +
                ", numeroAsiento=" + numeroAsiento +
                ", cliente=" + cliente +
                '}';
    }
}
