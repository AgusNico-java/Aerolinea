package TADs;

import java.util.Arrays;

public class VueloNacional extends VueloPublico{

    public VueloNacional(Aeropuerto origen, Aeropuerto destino, String fecha, int tripulantes,
                         double valorRefrigerio, double[] precios, int[] cantAsientos){
        super(destino.getNombre(), origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
    }

    @Override
    public String toString() {
        return "VueloNacional{" +
                "cantidadPasajeros=" + cantidadPasajeros +
                ", valorRefrigerio=" + valorRefrigerio +
                ", pasajeros=" + pasajeros +
                ", cantidadAsientos=" + Arrays.toString(cantidadAsientos) +
                '}';
    }
}
