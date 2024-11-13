# LISTA DE TADs:
- Aerolinea
- Vuelo
- Pasajero
- Asiento
- Aeropuerto
- Seccion

# DEFINICIÓN DE TADS

## Aerolinea
### Datos
- vuelos
- aeropuertos
- nombre
- cuit
- pasajeros
### Operaciones
- crear(nombre, cuit);
- registrarPasajero(nombre, telefono, dni);
- estaRegistrado(dniPasajero);
- registrarAeropuerto(nombre, ubicacion, direccion);
- crearVueloPublico(horaSalida, aeropuertoSalida, horaDestino, aeropuertoDestino, cantidadAsientos,
  cantidadTripulantes, pasajeros, precioRefrigerio, secciones);
- crearVueloNacional();
- crearVueloInteracional(aeropuertoDeEscala, cantidadRefrigerios);
- registrarVueloPrivado(comprador, pasajerosAcompañantes[]);
- consultarAsientosDisponibles(codigoVuelo);
- venderPasajePublico(dniPasajero, codigoVuelo, numeroAsiento, seccion);
- consultarVuelosDisponibles(origen, destino, fechaPartida);
- cancelarPasaje(dniPasajero, codigoVuelo, asiento);
- cancelarVuelo(codigoVuelo);
- totalRecaudado(destino);

## Vuelo
### Datos
- codigo
- horaSalida
- aeropuertoSalida
- horaDestino
- aeropuertoDestino
- cantidadAsientos
- cantidadTripulantes
- pasajeros
- secciones
- pasajerosPorSeccion
- refrigerios
- precioRefrigerio
### Operaciones
- constructor();
- paisSalidaIgualPaisDestino();
- consultarAsientosDisponibles();
- parteDe(origen);
- dirigeA(destino);
- fechaProxima(fechaPartida);
- cancelar();

## VueloPublico extends Vuelo
### Datos
### Operaciones
- constructor();

## VueloNacional extends VueloPublico
### Datos
- constructor();
### Operaciones

## VueloInternacional extends VueloPublico
### Datos
- aeropuertoDeEscala
- cantidadRefrigerios
### Operaciones
- constructor(aeropuertoEscala, cantidadRefrigerios);

## VueloPrivado extends Vuelo
### Datos
- comprador
- pasajerosAcompañantes
- precioPorJet
- precioFinal
### Operaciones
- constructor();

## Pasajero
### Datos
- dni
- nombre
- telefono
### Operaciones
- constructor();

## Aeropuerto
### Datos
- nombre
- ubicacion
- direccion
### Operaciones
- constructor(nombre, ubicacion, direccion)

## Asiento
### Datos
- seccion
- pasajero
### Operaciones
- estaDisponible();
- asignarPasajero();
- mostrarSeccion();
- mostrarPasajero();

## Seccion
### Datos
- capacidad
- precio
- asientos
### Operaciones

# INTERFAZ
- void crear(String nombre, String cuit);
- registrarPasajero(String nombre, String telefono, String dni) throws PasajeroExistenteException; 
- boolean estaRegistrado(String dniPasajero);
- registrarAeropuerto(String nombre, String ubicacion, String direccion) throws AeropuertoExistenteException;
- void crearVueloPublico(DateTime horaSalida, Aeropuerto aeropuertoSalida, DateTime horaDestino, Aeropuerto aeropuertoDestino, int cantidadAsientos,
  int cantidadTripulantes, List<Pasajero> pasajeros, float precioRefrigerio, String refrigerio, Seccion[] secciones);
- void crearVueloInteracional(DateTime horaSalida, Aeropuerto aeropuertoSalida, DateTime horaDestino, Aeropuerto aeropuertoDestino, int cantidadAsientos,
  int cantidadTripulantes, List<Pasajero> pasajeros, float precioRefrigerio, Seccion[] secciones, Aeropuerto aeropuertoDeEscala, int cantidadRefrigerios, String[] refrigerios);
- void registrarVueloPrivado(Pasajero comprador, Pasajero [] pasajerosAcompañantes);
- List<String>consultarAsientosDisponibles(String codigoVuelo);
- void venderPasajePublico(String dniPasajero, String codigoVuelo, int numeroAsiento, Seccion seccion);
- List<String> consultarVuelosDisponibles(Aeropuerto origen,Aeropuerto destino, DateTime fechaPartida);
- void cancelarPasaje(String dniPasajero, String codigoVuelo, Asiento asiento);
- List<String> cancelarVuelo(String codigoVuelo);
- float totalRecaudado(String destino);

# DIAGRAMA DE CLASES