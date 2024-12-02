package TADs;

import java.util.Arrays;

public class VueloInternacional extends VueloPublico{
    private String[] escalas;

    public VueloInternacional(Aeropuerto origen, Aeropuerto destino, String fecha, int tripulantes,
                              double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas, String codVuelo) {
        super(destino.obtenerNombre(), origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos, codVuelo, cantRefrigerios);
        this. escalas = escalas;
    }

    @Override
    public String toString() {
        return "VueloInternacional{" +
                "escalas=" + Arrays.toString(escalas) +
                ", cantRefrigerios=" + cantRefrigerios +
                ", cantidadPasajeros=" + cantidadPasajeros +
                ", valorRefrigerio=" + valorRefrigerio +
                ", pasajeros=" + pasajeros +
                ", cantidadAsientos=" + Arrays.toString(cantidadAsientos) +
                '}';
    }
}
