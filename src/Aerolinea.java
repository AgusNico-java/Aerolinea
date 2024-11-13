import TADs.*;

import java.util.ArrayList;
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

    }

    @Override
    public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
        this.aeropuertos.add(new Aeropuerto(nombre, pais, provincia, direccion));
    }

    @Override
    public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios, int[] cantAsientos) {
        return "";
    }

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

    @Override
    public double totalRecaudado(String destino) {
        return 0;
    }

    @Override
    public String detalleDeVuelo(String codVuelo) {
        return "";
    }
}
