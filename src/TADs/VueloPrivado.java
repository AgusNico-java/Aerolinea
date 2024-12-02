package TADs;

public class VueloPrivado extends Vuelo {
    private final double PORCENTAJE_IMPUESTOS = 0.3;
    private final double CAPACIDAD_JET = 15;

    private double precioJet;
    private Cliente comprador;
    private Cliente[] acompaniantes;

    public VueloPrivado(String destino, int totalTripulantes, double[] precios, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada,
                        String fechaSalida, Cliente comprador, Cliente[] acompaniantes, double precioJet, String codVuelo) {
        super(destino, totalTripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fechaSalida, codVuelo);
        this.precioJet = precioJet;
        this.comprador = comprador;
        this.acompaniantes = acompaniantes;
    }

    public double calcularPrecio() {
        double cantidadPasajeros = acompaniantes.length + 1;
        int cantidadDeJets = (int) Math.ceil(cantidadPasajeros / CAPACIDAD_JET);
        double precio = precioJet * cantidadDeJets;
        double totalImpuestos = precio * PORCENTAJE_IMPUESTOS;
        return precio + totalImpuestos;
    }

    @Override
    public String toString() {

        return "VueloPrivado{" +
                "precioJet=" + precioJet +
                ", capacidadMaximaJet=" + CAPACIDAD_JET +
                ", comprador=" + comprador +
                ", acompaniantes=" + acompaniantes +
                '}';
    }
}
