package TADs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class Vuelo {
    private String destino;

	private int totalTripulantes;
    private double[] precios;

    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;

    private String fechaSalida;
	private String codVuelo;

    private Cliente[] registroPasajeros;

    public Vuelo(String destino, int totalTripulantes, double[] precios,
				 Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, String fechaSalida, String codVuelo) {

        this.destino = destino;
		this.totalTripulantes = totalTripulantes;
        this.precios = precios;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaSalida = fechaSalida;
		this.codVuelo = codVuelo;
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

	public String getDestino() {
		return destino;
	}

	public int getTotalTripulantes() {
		return totalTripulantes;
	}

	public double[] getPrecios() {
		return precios;
	}

	public String getCodVuelo() {
		return codVuelo;
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

	public boolean esVueloSimilar(String origenAComparar, String destinoAComparar, String fechaAComparar) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String origenVuelo = this.aeropuertoSalida.getNombre();
		String destinoVuelo = this.destino;

		LocalDate fechaVuelo = LocalDate.parse(this.fechaSalida, formatter);
		LocalDate dateFechaAComparar = LocalDate.parse(fechaAComparar, formatter);

		return origenVuelo.equals(origenAComparar) &&
				destinoVuelo.equals(destinoAComparar) &&
				DAYS.between(fechaVuelo, dateFechaAComparar) <= 7;
	}
}
