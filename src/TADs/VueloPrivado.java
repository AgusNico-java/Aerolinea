package TADs;

import java.time.LocalDateTime;
import java.util.HashMap;

public class VueloPrivado extends Vuelo {
    private double precioJet;
    private double capacidadMaximaJet;
    private Cliente comprador;
    private HashMap<Integer, Cliente> clientes;

    public VueloPrivado(String destino, int cantidadAsientos, int totalTripulantes, int asientosDisponibles, double valorPasaje, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, LocalDateTime horaSalida, LocalDateTime horaLlegada, Cliente[] registroPasajeros, double precioJet, int capacidadMaximaJet, Cliente comprador, HashMap<Integer, Cliente> clientes) {
        super(destino, cantidadAsientos, totalTripulantes, asientosDisponibles, valorPasaje, aeropuertoSalida, aeropuertoLlegada, horaSalida, horaLlegada, registroPasajeros);
        this.precioJet = precioJet;
        this.capacidadMaximaJet = capacidadMaximaJet;
        this.comprador = comprador;
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "VueloPrivado{" +
                super.toString() +
                "precioJet=" + precioJet +
                ", capacidadMaximaJet=" + capacidadMaximaJet +
                ", comprador=" + comprador +
                ", clientes=" + clientes +
                '}';
    }
}
