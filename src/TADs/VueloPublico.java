package TADs;

import java.util.Arrays;
import java.util.HashMap;

public abstract class VueloPublico extends Vuelo {
    int cantidadPasajeros;
    double valorRefrigerio;
    HashMap<Cliente, Integer> pasajeros;
    int[] cantidadAsientos;

    public VueloPublico(String destino, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada,
                        String fecha, int tripulantes, double valorRefrigerio, double[] precios,
                        int[] cantidadAsientos){
        super(destino, tripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fecha);
        this.cantidadAsientos = cantidadAsientos;
        this.valorRefrigerio = valorRefrigerio;

    }

    @Override
    public String toString() {
        return "VueloPublico{" +
                "cantidadPasajeros=" + cantidadPasajeros +
                ", valorRefrigerio=" + valorRefrigerio +
                ", pasajeros=" + pasajeros +
                ", cantidadAsientos=" + Arrays.toString(cantidadAsientos) +
                '}';
    }
}
