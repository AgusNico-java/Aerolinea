package TADs;

public class Asiento {
    private Seccion seccion;
    private int numeroAsiento;
    private Cliente cliente;
    private int codPasaje;
    
    public Asiento(Seccion seccion, int numeroAsiento) {
        this.seccion = seccion;
        this.numeroAsiento = numeroAsiento;
    }

    // Getter para número de asiento
    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    // Getter para cliente
    public Cliente getCliente() {
        return cliente;
    }

    // Setter para cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getter para código de pasaje
    public int getCodPasaje() {
        return codPasaje;
    }

    // Setter para código de pasaje
    public void setCodPasaje(int codPasaje) {
        this.codPasaje = codPasaje;
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