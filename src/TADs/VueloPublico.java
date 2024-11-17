package TADs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class VueloPublico extends Vuelo {
    int cantidadPasajeros;
    double valorRefrigerio;
    HashMap<Cliente, Integer> pasajeros;
    int[] cantidadAsientos;
    Map<Integer, String> asientosDisponibles;

    public VueloPublico(String destino, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada,
                        String fecha, int tripulantes, double valorRefrigerio, double[] precios,
                        int[] cantidadAsientos){
        super(destino, tripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fecha);
        this.cantidadAsientos = cantidadAsientos;
        this.valorRefrigerio = valorRefrigerio;
        this.asientosDisponibles = inicializarAsientosDisponibles(cantidadAsientos);
    }

    private Map<Integer, String> inicializarAsientosDisponibles(int[] cantidadAsientos) {
        Map<Integer, String> asientosDisponibles = new HashMap<>();

        for (int i = 0; i < cantidadAsientos[0]; i++) {
            int numeroAsiento = 1 + i;
            System.out.println(numeroAsiento);
            asientosDisponibles.put(numeroAsiento, "Turista");
        }

        for (int i = 0; i < cantidadAsientos[1]; i++) {
            int numeroAsiento = cantidadAsientos[0] + i + 1;
            System.out.println(numeroAsiento);
            asientosDisponibles.put(numeroAsiento, "Ejecutiva");
        }

        if (cantidadAsientos.length == 3){
            for (int i = 0; i < cantidadAsientos[2]; i++) {
                int numeroAsiento = cantidadAsientos[0] + cantidadAsientos[1] + i + 1;
                System.out.println(numeroAsiento);
                asientosDisponibles.put(numeroAsiento, "Primera clase");
            }
        }

        return asientosDisponibles;
    }

    public Map<Integer, String> getAsientosDisponibles() {
        return asientosDisponibles;
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
