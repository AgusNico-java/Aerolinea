package TADs;

public class Cliente {
    private int dni;
    private String telefono;
    private String nombre;
    private int codPasaje;

    public Cliente(int dni, String telefono, String nombre) {
        this.dni = dni;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + dni +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public int getCodPasaje() {
    return codPasaje;
}
	public int getDni() {
		return dni;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setCodPasaje(int codPasaje) {
    this.codPasaje = codPasaje;
}
}
