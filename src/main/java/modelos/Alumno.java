package modelos;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
	
	private String rut;
	private String nombre;
	private String apellido;
	private String direccion;
	private List<Materia> materias;
	
	public Alumno(String rut, String nombre, String apellido, String direccion) {
	
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.materias = new ArrayList<Materia>();
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void addMaterias(Materia materia) {
		this.materias.add(materia);
	}

	@Override
	public String toString() {
		return "Alumno [rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", materias=" + materias + "]";
	}
		
}
