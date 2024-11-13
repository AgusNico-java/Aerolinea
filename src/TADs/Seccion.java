package TADs;

import java.util.ArrayList;

public class Seccion {
    private int capacidad;
    private float precio;
    private ArrayList<Asiento> asientos;

    public Seccion(int capacidad, float precio, ArrayList<Asiento> asientos) {
        this.capacidad = capacidad;
        this.precio = precio;
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "Seccion{" +
                "capacidad=" + capacidad +
                ", precio=" + precio +
                ", asientos=" + asientos +
                '}';
    }
}
