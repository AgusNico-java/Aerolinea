package TADs;

public class Asiento {
    private String seccion;
    private int numeroAsiento;
    private Cliente cliente;
    private boolean ocupado;
    private int codPasaje;

    public Asiento(String seccion, int numeroAsiento) {
        this.seccion = seccion;
        this.numeroAsiento = numeroAsiento;
        this.ocupado = false;
    }

    public void asignarAsiento(Cliente cliente, boolean aOcupar, int codPasaje) {
        this.cliente = cliente;
        this.ocupado = aOcupar;
        this.codPasaje = codPasaje;
    }

}
