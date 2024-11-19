package TADs;

public class VueloPrivado extends Vuelo {
    private double precioJet;
    private double capacidadMaximaJet;
    private Cliente comprador;
    private Cliente[] acompaniantes;

    public VueloPrivado(String destino, int totalTripulantes, double[] precios, Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada,
                        String fechaSalida, Cliente comprador, Cliente[] acompaniantes, double precioJet, String codVuelo) {
        super(destino, totalTripulantes, precios, aeropuertoSalida, aeropuertoLlegada, fechaSalida, codVuelo);
        this.precioJet = precioJet;
        this.comprador = comprador;
        this.acompaniantes = acompaniantes;
    }

    @Override
    public String toString() {

        return "VueloPrivado{" +
                "precioJet=" + precioJet +
                ", capacidadMaximaJet=" + capacidadMaximaJet +
                ", comprador=" + comprador +
                ", acompaniantes=" + acompaniantes +
                '}';
    }
}
