import TADs.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Aerolinea implements IAerolinea {
    private String name;
    private Map<String, Vuelo> vuelos;
    private Map<Integer, Cliente> clientes;
    private Map<String, Aeropuerto> aeropuertos;
    private String cuit;
    private int codigoPasaje;

    public Aerolinea(String name, String cuit) {
        this.name = name;
        this.cuit = cuit;
        this.clientes = new HashMap<>();
        this.aeropuertos = new HashMap<>();
        this.vuelos = new HashMap<>();
        this.codigoPasaje = 0;
    }

    @Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		// Verificar si ya existe un cliente con el mismo DNI
        if (clientes.containsKey(dni)) {
            throw new RuntimeException("El cliente con DNI " + dni + " ya está registrado.");
        }
		// Crear un nuevo cliente y agregarlo a la lista
		Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
		clientes.put(dni, nuevoCliente);
	}

	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
        if (aeropuertos.containsKey(nombre)) {
            throw new RuntimeException("El aeropuerto " + nombre + " ya está registrado.");
        }
        aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
	}


	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
                                                double valorRefrigerio, double[] precios, int[] cantAsientos) {
        Aeropuerto aeropuertoSalida = aeropuertos.get(origen);
        Aeropuerto aeropuertoDestino = aeropuertos.get(destino);
        Vuelo vuelo = new VueloNacional(aeropuertoSalida, aeropuertoDestino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos); // ver esto despues
        String codVuelo = crearCodigoPublico();

        if (!aeropuertos.containsKey(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");
        }

        if (!vuelo.paisSalidaIgualPaisDestino()) {
            throw new RuntimeException("Los datos ingresados corresponden a un vuelo internacional");
        }
		vuelos.put(codVuelo, vuelo);

		return codVuelo;
	}

    @Override
    public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
        Vuelo vuelo = new VueloInternacional(aeropuertos.get(origen), aeropuertos.get(destino), fecha, tripulantes, valorRefrigerio, cantRefrigerios, precios, cantAsientos, escalas);
        String codVuelo = crearCodigoPublico();

        if (!aeropuertos.containsKey(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");

        }
        if (vuelo.paisSalidaIgualPaisDestino()) {
            throw new RuntimeException("Los datos ingresados corresponden a un vuelo nacional.");
        }

        vuelos.put(codVuelo, vuelo);

        return codVuelo;
    }

    @Override
    public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio, int dniComprador, int[] acompaniantes) {
        if (!aeropuertos.containsKey(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");
        }
        if (!aeropuertos.containsKey(origen)) {
            throw new RuntimeException("El origen " + origen + " no está registrado.");
        }
        if (!fechaValida(fecha)) {
            throw new RuntimeException("La fecha debe ser posterior a la fecha actual.");
        }
        Cliente comprador = clientes.get(dniComprador);
        ArrayList<Cliente> listaDeAcompaniantes = new ArrayList<>();
        Aeropuerto aeropuertoSalida = aeropuertos.get(origen);
        Aeropuerto aeropuertoDestino = aeropuertos.get(destino);


        for (int dni : acompaniantes) {
            listaDeAcompaniantes.add(clientes.get(dni));
        }

        Cliente[] arregloDeAcompaniantes = listaDeAcompaniantes.toArray(new Cliente[listaDeAcompaniantes.size()]);
        Vuelo vuelo = new VueloPrivado(destino, tripulantes, null, aeropuertoSalida, aeropuertoDestino, fecha, comprador, arregloDeAcompaniantes, precio);
        String codVuelo = crearCodigoPrivado();

        vuelos.put(codVuelo, vuelo);

        return codVuelo;
    }

    private boolean fechaValida(String fecha) {
        Date fechaIngresada = new Date(fecha);
        Date hoy = Calendar.getInstance().getTime();

        return hoy.before(fechaIngresada);
    }

    @Override
    public Map<Integer, String> asientosDisponibles(String codVuelo) {
        VueloPublico vuelo = (VueloPublico) vuelos.get(codVuelo);
        return vuelo.getAsientosDisponibles();
    }

    @Override
    public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
        if (!clientes.containsKey(dni)) {
            throw new RuntimeException("El cliente debe estar registrado para comprar un pasaje");
        }
        if (!vuelos.containsKey(codVuelo)) {
            throw new RuntimeException("El cliente debe estar registrado para comprar un pasaje");
        }

        this.codigoPasaje = codigoPasaje + 1;
        VueloPublico vuelo = (VueloPublico) this.vuelos.get(codVuelo);
        Cliente cliente = clientes.get(dni);

        if (!vuelo.getAsientosDisponibles().containsKey(nroAsiento)) {
            throw new RuntimeException("El número de asiento ya se encuentra vendido.");
        }
        Asiento asientoVendido = vuelo.getAsientosVuelo().get(nroAsiento - 1);
        asientoVendido.asignarAsiento(cliente, aOcupar, this.codigoPasaje);
        vuelo.getAsientosDisponibles().remove(nroAsiento);

        return this.codigoPasaje;
    }

    @Override
    public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
        List<String> vuelosSimilares = new ArrayList<>();

        for (String key : vuelos.keySet()) {
            Vuelo vuelo = vuelos.get(key);
            if (vuelo.esVueloSimilar(origen, destino, Fecha)) {
                vuelosSimilares.add(key);
            }
        }

        return vuelosSimilares;
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
                for (double precio : vuelo.getPrecios()) {
                    totalRecaudado += precio;
                }
            }
        }
        return totalRecaudado;
    }

   @Override
   public String detalleDeVuelo(String codVuelo) {
       Vuelo vuelo = vuelos.get(codVuelo);

       if (vuelo == null) {
           return "El vuelo con código " + codVuelo + " no existe.";
       }

       //Aca vemos el tipo de vuelo
       if (vuelo instanceof VueloPrivado) {
           VueloPrivado vueloPrivado = (VueloPrivado) vuelo;
           int cantidadPasajeros = 1; // Comprador
           if (vueloPrivado.getRegistroPasajeros() != null) {
               cantidadPasajeros += vueloPrivado.getRegistroPasajeros().length;
           }
           return codVuelo + " - " 
               + vuelo.getAeropuertoSalida().getNombre() + " - "
               + vuelo.getAeropuertoLlegada().getNombre() + " - "
               + vuelo.getFechaSalida() + " - PRIVADO (" + cantidadPasajeros + ")";
       }

       String tipoVuelo = "NACIONAL";
       if (vuelo instanceof VueloInternacional) {
           tipoVuelo = "INTERNACIONAL";
       }

       return codVuelo + " - " 
           + vuelo.getAeropuertoSalida().getNombre() + " - "
           + vuelo.getAeropuertoLlegada().getNombre() + " - "
           + vuelo.getFechaSalida() + " - " + tipoVuelo;
   }
    @Override
    public String toString() {
        //TODO: IMPLEMENTAR
        return "";
    }

    /*
    contamos los vuelos públicos registrados y le agregamos el sufijo "-PUB" para generar el código
     */
    private String crearCodigoPublico() {
        int cantidadDeVuelosPublicos = (int) vuelos.keySet().stream().filter(s -> {
            return s.endsWith("key");
        }).count();

        return cantidadDeVuelosPublicos + "-PUB";
    }

    /*
    contamos los vuelos públicos registrados y le agregamos el sufijo "-priv" para generar el código
     */
    private String crearCodigoPrivado() {
        int cantidadDeVuelosPublicos = (int) vuelos.keySet().stream().filter(s -> {
            return s.endsWith("key");
        }).count();

        return cantidadDeVuelosPublicos + "-PRI";
    }


}
