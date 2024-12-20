import TADs.*;

import java.util.*;

public class Aerolinea implements IAerolinea {
    private String name;
    private Map<String, Vuelo> vuelos;
    private Map<Integer, Cliente> clientes;
    private Map<String, Aeropuerto> aeropuertos;
    private Map<String, Double> recaudadoPorDestino;
    private String cuit;
    private int codigoPasajeActual;

    public Aerolinea(String name, String cuit) {
        this.name = name;
        this.cuit = cuit;
        this.clientes = new HashMap<>();
        this.aeropuertos = new HashMap<>();
        this.vuelos = new HashMap<>();
        this.recaudadoPorDestino = new HashMap<>();
        this.codigoPasajeActual = 0;
    }

    @Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		// Verificar si ya existe un cliente con el mismo DNI
        if (clienteExistente(dni)) {
            throw new RuntimeException("El cliente con DNI " + dni + " ya está registrado.");
        }
		// Crear un nuevo cliente y agregarlo a la lista
		Cliente nuevoCliente = new Cliente(dni, nombre, telefono);
		clientes.put(dni, nuevoCliente);
	}

    @Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
        if (aeropuertoExistente(nombre)) {
            throw new RuntimeException("El aeropuerto " + nombre + " ya está registrado.");
        }
        aeropuertos.put(nombre, new Aeropuerto(nombre, pais, provincia, direccion));
	}


	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
                                                double valorRefrigerio, double[] precios, int[] cantAsientos) {
        Aeropuerto aeropuertoSalida = aeropuertos.get(origen);
        Aeropuerto aeropuertoDestino = aeropuertos.get(destino);

        if (!aeropuertoExistente(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");
        }
        String codVuelo = crearCodigoPublico();
        Vuelo vuelo = new VueloNacional(aeropuertoSalida, aeropuertoDestino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos, codVuelo);

        if (!vuelo.paisSalidaIgualPaisDestino()) {
            throw new RuntimeException("Los datos ingresados corresponden a un vuelo internacional");
        }

		vuelos.put(codVuelo, vuelo);

		return codVuelo;
	}

    @Override
    public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {

        if (!aeropuertoExistente(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");
        }

        String codVuelo = crearCodigoPublico();
        Vuelo vuelo = new VueloInternacional(aeropuertos.get(origen), aeropuertos.get(destino), fecha, tripulantes, valorRefrigerio, cantRefrigerios, precios, cantAsientos, escalas, codVuelo);

        if (vuelo.paisSalidaIgualPaisDestino()) {
            throw new RuntimeException("Los datos ingresados corresponden a un vuelo nacional.");
        }

        vuelos.put(codVuelo, vuelo);

        return codVuelo;
    }

    @Override
    public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio, int dniComprador, int[] acompaniantes) {
        if (!aeropuertoExistente(destino)) {
            throw new RuntimeException("El destino " + destino + " no está registrado.");
        }
        if (!aeropuertoExistente(origen)) {
            throw new RuntimeException("El origen " + origen + " no está registrado.");
        }
        if (!fechaValida(fecha)) {
            throw new RuntimeException("La fecha debe ser posterior a la fecha actual.");
        }

        Cliente comprador = clientes.get(dniComprador);
        ArrayList<Cliente> listaDeAcompaniantes = new ArrayList<>();
        Aeropuerto aeropuertoSalida = aeropuertos.get(origen);
        Aeropuerto aeropuertoDestino = aeropuertos.get(destino);

        List<Integer> listaDniAcompaniantes = Arrays.asList(Arrays.stream(acompaniantes).boxed().toArray(Integer[]::new));
        Iterator<Integer> iterator = listaDniAcompaniantes.iterator();
        while (iterator.hasNext()) {
            int dni = iterator.next();
            listaDeAcompaniantes.add(clientes.get(dni));
        }

        int totalPasajeros = listaDeAcompaniantes.size() + 1;
        Cliente[] arregloDeAcompaniantes = listaDeAcompaniantes.toArray(new Cliente[0]);
        String codVuelo = crearCodigoPrivado();
        VueloPrivado vuelo = new VueloPrivado(destino, tripulantes, null, aeropuertoSalida, aeropuertoDestino, fecha, comprador, arregloDeAcompaniantes, precio, codVuelo);

        incrementarRecaudadoPrivado(totalPasajeros, precio, destino, vuelo);
        vuelos.put(codVuelo, vuelo);

        return codVuelo;
    }

    private void incrementarRecaudadoPrivado(double totalPasajeros, double precio, String destino, VueloPrivado vuelo) {
        double recaudado = totalRecaudado(destino) + vuelo.calcularPrecio();
        this.recaudadoPorDestino.put(destino, recaudado);
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
        if (!clienteExistente(dni)) {
            throw new RuntimeException("El cliente debe estar registrado para comprar un pasaje");
        }
        if (!vueloExistente(codVuelo)) {
            throw new RuntimeException("El vuelo no existe");
        }

        this.codigoPasajeActual = codigoPasajeActual + 1;
        VueloPublico vuelo = (VueloPublico) this.vuelos.get(codVuelo);
        Cliente cliente = clientes.get(dni);

        if (!vuelo.getAsientosDisponibles().containsKey(nroAsiento)) {
            throw new RuntimeException("El número de asiento ya se encuentra vendido.");
        }
        Asiento asientoVendido = vuelo.getAsientosVuelo().get(nroAsiento - 1);
        asientoVendido.asignarAsiento(cliente, aOcupar, this.codigoPasajeActual);
        incrementarRecaudadoPublico(vuelo, nroAsiento);
        vuelo.getAsientosDisponibles().remove(nroAsiento);

        return this.codigoPasajeActual;
    }

    private void incrementarRecaudadoPublico(VueloPublico vuelo, int nroAsiento) {
        String destino = vuelo.obtenerDestino();
        double recaudado = totalRecaudado(destino);

        recaudado = recaudado + vuelo.calcularPrecio(nroAsiento);

        this.recaudadoPorDestino.put(destino, recaudado);

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
        if (!vueloExistente(codVuelo)) {
            throw new RuntimeException("El vuelo no existe");
        }

        VueloPublico vuelo = (VueloPublico) vuelos.get(codVuelo);
        vuelo.cancelarPasaje(dni, nroAsiento);
    }

    @Override
    public void cancelarPasaje(int dni, int codPasaje) {
        for (String key : this.vuelos.keySet()) {
            VueloPublico vueloPublico = (VueloPublico) vuelos.get(key);
            int numeroAsiento = vueloPublico.pasajePertenece(codPasaje);
            if (numeroAsiento != 0) {
                vueloPublico.cancelarPasaje(dni, numeroAsiento);
            }
        }
    }

    @Override
    public List<String> cancelarVuelo(String codVuelo) {
        if (!vueloExistente(codVuelo)) {
            throw new RuntimeException("El vuelo no existe");
        }
        VueloPublico vueloACancelar = (VueloPublico) this.vuelos.get(codVuelo);
        String origen = vueloACancelar.obtenerAeropuertoSalida().obtenerNombre();
        String destino = vueloACancelar.obtenerAeropuertoLlegada().obtenerNombre();
        String fecha = vueloACancelar.obtenerFechaSalida();
        List<Vuelo> vuelosDeReemplazo = new ArrayList<>();
        List<String> estadoPasajeros = new ArrayList<>();

        //Obtenemos los candidatos a reemplazo del vuelo cancelado (mismo origen, destino y fecha)
        for (String posibleReemplazo : consultarVuelosSimilares(origen, destino, fecha)) {
            vuelosDeReemplazo.add(vuelos.get(posibleReemplazo));
        }

        //Revisamos cada asiento del vuelo en búsqueda de los clientes existentes
        for (Asiento asiento : vueloACancelar.getAsientosVuelo()) {
            Cliente cliente = asiento.obtenerCliente();
            String estadoPasajero = "";
            String nuevoVuelo;
            if (cliente != null) {
                estadoPasajero = estadoPasajero +
                        cliente.obtenerDni() + " - " +
                        cliente.obtenerNombre() + " - " +
                        cliente.obtenerTelefono() + " - ";
                nuevoVuelo = reasignarVuelo(cliente, asiento, vuelosDeReemplazo);
                estadoPasajero = estadoPasajero + nuevoVuelo;
                estadoPasajeros.add(estadoPasajero);
            }
        }
        this.vuelos.remove(codVuelo);
        return estadoPasajeros;
    }

    private String reasignarVuelo(Cliente cliente, Asiento asiento, List<Vuelo> vuelosDeReemplazo) {
        String seccion = asiento.obtenerSeccion();
        int dni = cliente.obtenerDni();
        int nroAsiento;
        for (Vuelo vuelo : vuelosDeReemplazo) {
            if (!(vuelo.getClass().equals(VueloNacional.class)
            || vuelo.getClass().equals(VueloInternacional.class))){
                continue;
            }
            VueloPublico nuevaAsignacion = (VueloPublico) vuelo;
            nroAsiento = nuevaAsignacion.asientoDisponible(seccion);
            if (nroAsiento != 0) {
                venderPasaje(dni, nuevaAsignacion.getCodVuelo(), nroAsiento, true);
                return nuevaAsignacion.getCodVuelo();
            }
        }
        return "CANCELADO";
    }

   @Override
    public double totalRecaudado(String destino) {
        return this.recaudadoPorDestino.get(destino) != null ? this.recaudadoPorDestino.get(destino) : 0;
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
           if (vueloPrivado.obtenerRegistroPasajeros() != null) {
               cantidadPasajeros += vueloPrivado.obtenerRegistroPasajeros().length;
           }
           return codVuelo + " - " 
               + vuelo.obtenerAeropuertoSalida().obtenerNombre() + " - "
               + vuelo.obtenerAeropuertoLlegada().obtenerNombre() + " - "
               + vuelo.obtenerFechaSalida() + " - PRIVADO (" + cantidadPasajeros + ")";
       }

       String tipoVuelo = "NACIONAL";
       if (vuelo instanceof VueloInternacional) {
           tipoVuelo = "INTERNACIONAL";
       }

       return codVuelo + " - " 
           + vuelo.obtenerAeropuertoSalida().obtenerNombre() + " - "
           + vuelo.obtenerAeropuertoLlegada().obtenerNombre() + " - "
           + vuelo.obtenerFechaSalida() + " - " + tipoVuelo;
   }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nombre: " + this.name + "\n" +
                "CUIT: " + this.cuit + "\n" +
                "Clientes: {\n");

        for (Cliente cliente : this.clientes.values()) {
            builder.append(cliente.toString() + "\n");
        }

        builder.append(" }\n" +
                "Aeropuertos: {\n");

        for (Aeropuerto aeropuerto : this.aeropuertos.values()) {
            builder.append(aeropuerto.toString() + "\n");
        }

        builder.append(" }\n" +
                "Vuelos: {\n" );

        for (Vuelo vuelo : this.vuelos.values()) {
            builder.append(vuelo.toString() + "\n");
        }

        builder.append(" }");

        return builder.toString();
    }

    /*
    contamos los vuelos públicos registrados y le agregamos el sufijo "-PUB" para generar el código
     */
    private String crearCodigoPublico() {
        int cantidadDeVuelosPublicos = (int) vuelos.keySet().stream().filter(s -> {
            return s.endsWith("PUB");
        }).count();

        return cantidadDeVuelosPublicos + "-PUB";
    }

    /*
    contamos los vuelos públicos registrados y le agregamos el sufijo "-priv" para generar el código
     */
    private String crearCodigoPrivado() {
        int cantidadDeVuelosPublicos = (int) vuelos.keySet().stream().filter(s -> {
            return s.endsWith("PRI");
        }).count();

        return cantidadDeVuelosPublicos + "-PRI";
    }

    //Validaciones
    private boolean aeropuertoExistente(String codAeropuerto) {
        return this.aeropuertos.containsKey(codAeropuerto);
    }

    private boolean clienteExistente(int dni) {
        return this.clientes.containsKey(dni);
    }

    private boolean vueloExistente(String codVuelo) {
        return this.vuelos.containsKey(codVuelo);
    }


}
