package TADs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class VueloPublico extends Vuelo {
    int cantidadPasajeros;
    String refrigerio;
    HashMap<Cliente, Integer> pasajeros;
    ArrayList<Seccion> secciones;

    public VueloPublico(String destino, int cantidadAsientos, int totalTripulantes, int asientosDisponibles, double valorPasaje, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, LocalDateTime horaSalida, LocalDateTime horaLlegada, Cliente[] registroPasajeros, int cantidadPasajeros, String refrigerio, HashMap<Cliente, Integer> pasajeros, ArrayList<Seccion> secciones) {
        super(destino, cantidadAsientos, totalTripulantes, asientosDisponibles, valorPasaje, aeropuertoSalida, aeropuertoLlegada, horaSalida, horaLlegada, registroPasajeros);
        this.cantidadPasajeros = cantidadPasajeros;
        this.refrigerio = refrigerio;
        this.pasajeros = pasajeros;
        this.secciones = secciones;
    }

    @Override
    public String toString() {
        return "VueloPublico{" +
                "cantidadPasajeros=" + cantidadPasajeros +
                ", refrigerio='" + refrigerio + '\'' +
                ", pasajeros=" + pasajeros +
                ", secciones=" + secciones +
                '}';
    }
}
