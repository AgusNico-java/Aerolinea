package TADs;

import java.util.Arrays;

public class VueloInternacional extends VueloPublico{
    private String[] escalas;
    private int cantRefrigerios;

    public VueloInternacional(Aeropuerto origen, Aeropuerto destino, String fecha, int tripulantes,
                              double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas, String codVuelo) {
        super(destino.getNombre(), origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos, codVuelo);
        this. escalas = escalas;
        this.cantRefrigerios = cantRefrigerios;
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
