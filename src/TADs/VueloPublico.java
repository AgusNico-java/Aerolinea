package TADs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class VueloPublico extends Vuelo {
    int cantidadPasajeros;
    double valorRefrigerio;
    HashMap<Cliente, Integer> pasajeros;
    int[] cantidadAsientos;
    Map<Integer, String> asientosDisponibles;
    ArrayList<Asiento> asientosVuelo;

    public VueloPublico(String destino, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada,
                        String fecha, int tripulantes, double valorRefrigerio, double[] precios,
                        int[] cantidadAsientos){
        super(destino, tripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fecha);
        this.cantidadAsientos = cantidadAsientos;
        this.valorRefrigerio = valorRefrigerio;
        this.asientosDisponibles = inicializarAsientosDisponibles(cantidadAsientos);
        this.asientosVuelo = generarAsientos();
    }

    private ArrayList<Asiento> generarAsientos() {
        ArrayList<Asiento> asientos = new ArrayList<>();
        for (int numeroAsiento : asientosDisponibles.keySet()) {
            Asiento nuevoAsiento = new Asiento(asientosDisponibles.get(numeroAsiento), numeroAsiento);
            asientos.add(nuevoAsiento);
        }
        return asientos;
    }

    private Map<Integer, String> inicializarAsientosDisponibles(int[] cantidadAsientos) {
        Map<Integer, String> asientosDisponibles = new HashMap<>();

        for (int i = 0; i < cantidadAsientos[0]; i++) {
            int numeroAsiento = 1 + i;
            asientosDisponibles.put(numeroAsiento, "Turista");
        }

        for (int i = 0; i < cantidadAsientos[1]; i++) {
            int numeroAsiento = cantidadAsientos[0] + i + 1;
            asientosDisponibles.put(numeroAsiento, "Ejecutiva");
        }

        if (cantidadAsientos.length == 3){
            for (int i = 0; i < cantidadAsientos[2]; i++) {
                int numeroAsiento = cantidadAsientos[0] + cantidadAsientos[1] + i + 1;
                asientosDisponibles.put(numeroAsiento, "Primera clase");
            }
        }

        return asientosDisponibles;
    }

    public void cancelarPasaje(int dni, int nroAsiento) {
        Asiento asientoCancelado = this.asientosVuelo.get(nroAsiento - 1);
        if (asientoCancelado.getCliente().getDni() == dni) {
            asientosDisponibles.put(asientoCancelado.getNumeroAsiento(), asientoCancelado.getSeccion());
            asientoCancelado.eliminarCliente();
            asientosVuelo.add(asientoCancelado);
        }
    }

    public Map<Integer, String> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public ArrayList<Asiento> getAsientosVuelo() {
        return asientosVuelo;
    }

    public int pasajePertenece(int codPasaje) {
        for (Asiento asiento : asientosVuelo) {
            if (asiento.getCodPasaje() == codPasaje) {
                return asiento.getNumeroAsiento();
            }
        }
        return 0;
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
