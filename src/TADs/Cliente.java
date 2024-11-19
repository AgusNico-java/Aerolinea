package TADs;

import java.util.ArrayList;

public class Cliente {
    private int dni;
    private String telefono;
    private String nombre;
    private ArrayList<Integer> codPasaje;

    public Cliente(int dni, String nombre, String telefono) {
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

    public ArrayList<Integer> getCodPasaje() {
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

}
