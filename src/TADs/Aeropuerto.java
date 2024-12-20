package TADs;

public class Aeropuerto {
    private String nombre;
    private String pais;
    private String provincia;
    private String direccion;

    public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
        this.nombre = nombre;
        this.pais = pais;
        this.provincia = provincia;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Aeropuerto{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", provincia='" + provincia + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public String obtenerNombre() {
		return nombre;
	}

	public String obtenerPais() {
		return pais;
	}
}
