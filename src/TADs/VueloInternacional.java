package TADs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class VueloInternacional extends VueloPublico{
    private Aeropuerto escala;

    public VueloInternacional(String destino, int cantidadAsientos, int totalTripulantes, int asientosDisponibles, double valorPasaje, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, LocalDateTime horaSalida, LocalDateTime horaLlegada, Cliente[] registroPasajeros, int cantidadPasajeros, String refrigerio, HashMap<Cliente, Integer> pasajeros, ArrayList<Seccion> secciones, Aeropuerto escala) {
        super(destino, cantidadAsientos, totalTripulantes, asientosDisponibles, valorPasaje, aeropuertoSalida, aeropuertoLlegada, horaSalida, horaLlegada, registroPasajeros, cantidadPasajeros, refrigerio, pasajeros, secciones);
        this. escala = escala;
    }

    @Override
    public String toString() {
        return "VueloInternacional{" +
                "escala=" + escala +
                ", cantidadPasajeros=" + cantidadPasajeros +
                ", refrigerio='" + refrigerio + '\'' +
                ", pasajeros=" + pasajeros +
                ", secciones=" + secciones +
                '}';
    }
}
