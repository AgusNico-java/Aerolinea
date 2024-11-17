package TADs;

import java.time.LocalDateTime;
import java.util.HashMap;

public class VueloPrivado extends Vuelo {
    private double precioJet;
    private double capacidadMaximaJet;
    private Cliente comprador;
    private HashMap<Integer, Cliente> clientes;

    public VueloPrivado(String destino, int totalTripulantes, double[] precios, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, String fechaSalida) {
        super(destino, totalTripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fechaSalida);
    }

    @Override
    public String toString() {

        return "VueloPrivado{" +
                "precioJet=" + precioJet +
                ", capacidadMaximaJet=" + capacidadMaximaJet +
                ", comprador=" + comprador +
                ", clientes=" + clientes +
                '}';
    }
}
