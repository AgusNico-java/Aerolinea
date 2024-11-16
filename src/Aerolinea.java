import TADs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aerolinea implements IAerolinea {
    private String name;
    private Map<String, Vuelo> vuelos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Aeropuerto> aeropuertos;
    private String cuit;

    public Aerolinea(String name, String cuit) {
        this.name = name;
        this.cuit = cuit;
        this.clientes = new ArrayList<>();
        this.aeropuertos = new ArrayList<>();
        this.vuelos = new HashMap<>();
    }

    @Override
    public String toString() {
        //TODO: MODIFICAR LA IMPLEMENTACIÓN CON UN StringBuilder
        return "BondiJet{" +
                "name='" + name + '\'' +
                ", vuelos=" + vuelos +
                ", clientes=" + clientes +
                ", aeropuertos=" + aeropuertos +
                ", cuit='" + cuit + '\'' +
                '}';
    }

	@Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		// Verificar si ya existe un cliente con el mismo DNI
		for (Cliente cliente : clientes) {
			if (cliente.getDni() == dni) {
				throw new RuntimeException("El cliente con DNI " + dni + " ya está registrado.");
			}
		}
		// Crear un nuevo cliente y agregarlo a la lista
		Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
		clientes.add(nuevoCliente);
	}

	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getNombre().equals(nombre)) {
				throw new RuntimeException("El aeropuerto " + nombre + " ya está registrado.");
			}
		}
		aeropuertos.add(new Aeropuerto(nombre, pais, provincia, direccion));
	}

	/*
	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		return "";
		Vuelo nuevoVuelo = new VueloNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos); // ver esto despues
		String codVuelo = origen + "-" + destino + "-" + fecha;

		vuelos.put(codVuelo, nuevoVuelo);

		return "Vuelo código " + codVuelo + " registrado correctamente.";
	}
	*/

    @Override
    public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
        return "";
    }

    @Override
    public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio, int dniComprador, int[] acompaniantes) {
        return "";
    }

    @Override
    public Map<Integer, String> asientosDisponibles(String codVuelo) {
        return Map.of();
    }

    @Override
    public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
        return 0;
    }

    @Override
    public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
        return List.of();
    }

    @Override
    public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {

    }

    @Override
    public void cancelarPasaje(int dni, int codPasaje) {

    }

    @Override
    public List<String> cancelarVuelo(String codVuelo) {
        return List.of();
    }

	// FALTA IMPLEMENTAR REGISTRO DE VUELO
	@Override
	public double totalRecaudado(String destino) {
		double totalRecaudado = 0.0;

		for (Vuelo vuelo : vuelos.values()) {
			if (vuelo.getDestino().equals(destino)) {
				totalRecaudado += vuelo.getValorPasaje();
			}
		}
		return totalRecaudado;
	}

	// FALTA IMPLEMENTAR REGISTRO DE VUELO
	@Override
	public String detalleDeVuelo(String codVuelo) {
		Vuelo vuelo = vuelos.get(codVuelo);

		if (vuelo != null) {
			return vuelo.toString();
		} else {
			return "El vuelo con código " + codVuelo + " no existe.";
		}
	}

	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		// TODO Auto-generated method stub
		return null;
	}
}
