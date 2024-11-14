package TADs;

import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class Vuelo {
    private String destino;

    private int cantidadAsientos;
    private int totalTripulantes;
    private int asientosDisponibles;
    private double valorPasaje;

    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;

    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;

    private Cliente[] registroPasajeros;

    public Vuelo(String destino, int cantidadAsientos, int totalTripulantes, int asientosDisponibles, double valorPasaje, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, LocalDateTime horaSalida, LocalDateTime horaLlegada, Cliente[] registroPasajeros) {
        this.destino = destino;
        this.cantidadAsientos = cantidadAsientos;
        this.totalTripulantes = totalTripulantes;
        this.asientosDisponibles = asientosDisponibles;
        this.valorPasaje = valorPasaje;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.registroPasajeros = registroPasajeros;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "destino='" + destino + '\'' +
                ", cantidadAsientos=" + cantidadAsientos +
                ", totalTripulantes=" + totalTripulantes +
                ", asientosDisponibles=" + asientosDisponibles +
                ", valorPasaje=" + valorPasaje +
                ", aeropuertoSalida=" + aeropuertoSalida +
                ", aeropuertoLlegada=" + aeropuertoLlegada +
                ", horaSalida=" + horaSalida +
                ", horaLlegada=" + horaLlegada +
                ", registroPasajeros=" + Arrays.toString(registroPasajeros) +
                '}';
    }
}
