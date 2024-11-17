package TADs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Vuelo {
    private String destino;
    private ArrayList<Seccion> secciones;

	private int totalTripulantes;
    private double[] precios;

    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;

    private String fechaSalida;

    private Cliente[] registroPasajeros;

    public Vuelo(String destino, int totalTripulantes, double[] precios,
				 Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, String fechaSalida) {

        this.destino = destino;
		this.totalTripulantes = totalTripulantes;
        this.precios = precios;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaSalida = fechaSalida;
    }

	@Override
	public String toString() {
		return "Vuelo{" +
				"destino='" + destino + '\'' +
				", totalTripulantes=" + totalTripulantes +
				", precios=" + Arrays.toString(precios) +
				", aeropuertoSalida=" + aeropuertoSalida +
				", aeropuertoLlegada=" + aeropuertoLlegada +
				", fechaSalida='" + fechaSalida + '\'' +
				", registroPasajeros=" + Arrays.toString(registroPasajeros) +
				'}';
	}
	public List<Seccion> getSecciones() {
	    return secciones;
	}
	public String getDestino() {
		return destino;
	}

	public int getTotalTripulantes() {
		return totalTripulantes;
	}

	public double[] getPrecios() {
		return precios;
	}

	public Aeropuerto getAeropuertoSalida() {
		return aeropuertoSalida;
	}

	public Aeropuerto getAeropuertoLlegada() {
		return aeropuertoLlegada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public Cliente[] getRegistroPasajeros() {
		return registroPasajeros;
	}

	public boolean paisSalidaIgualPaisDestino() {
		return aeropuertoSalida.getPais().equals(aeropuertoLlegada.getPais());
	}
}
