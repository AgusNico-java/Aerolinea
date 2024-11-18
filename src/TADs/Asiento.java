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


    // Getter para número de asiento
    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    // Getter para cliente
    public Cliente getCliente() {
        return cliente;
    }

    public String getSeccion() {
        return seccion;
    }

    // Getter para código de pasaje
    public int getCodPasaje() {
        return codPasaje;
    }

    public void eliminarCliente() {
        this.cliente = null;
        this.ocupado = false;
        this.codPasaje = 0;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "seccion=" + seccion +
                ", numeroAsiento=" + numeroAsiento +
                ", cliente=" + cliente +
                ", codPasaje=" + codPasaje +
                '}';
    }
}

