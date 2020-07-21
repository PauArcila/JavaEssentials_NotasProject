package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import utilidades.Metodos;

public class AlumnoServicio {

	public static Map<String, Alumno> listaAlumnos = new HashMap<String, Alumno>();

	public  String crearAlumno(Alumno alumno) {
		if (alumno != null) {
			listaAlumnos.putIfAbsent(alumno.getRut(), alumno);
			System.out.println("Alumno creado!");
			return "Creado";
		} else {
			return "No creado";
		}
		//escribir datos del nuevo alumno en csv
	}
	
	public  void poblarAlumno(String ruta) {
		Map<String, Alumno> newMap=ArchivoServicio.cargarDatos(ruta+"notas.csv", false);
		if(newMap!=null)
		listaAlumnos.putAll(newMap);// aqui se agrego el parametro bool
	}

	public  String agregarMateria(String rutAlumno, Materia currentMate) {
		if (rutAlumno != "" && rutAlumno != null && currentMate != null) {
			if (listaAlumnos.containsKey(rutAlumno)) {
				listaAlumnos.get(rutAlumno).addMaterias(currentMate);
				return "Materia agregada";
			} else {
				return "Alumno no encontrado";
			}
		} else {
			return "No ingresado";
		}
	}

	public  List<Materia> materiasPorAlumnos(String rutAlumno) {
		if (rutAlumno != "" && rutAlumno != null) {
			if (listaAlumnos.containsKey(rutAlumno)) {
				List<Materia> materias = listaAlumnos.get(rutAlumno).getMaterias();
				return materias;
			}
		}
		return null;
	}

	public List<Alumno> listarAlumnos(){
		return  new ArrayList<Alumno>(listaAlumnos.values()) ;
	}
	
	public  void imprimirAlumnos(List<Alumno> lista) {/////////////////public  void imprimirAlumnos(List<Alumno> lista) {
		
		if(lista.size()==0) { ///////////////if(lista.size()==0)
			System.out.println("Primero agregue un alumno (1) o Cargue los datos del archivo (5)");
		}
		else {
			lista.stream().forEach(alu -> {//////////////lista.stream().forEach(alu -> {
				System.out.print(
						"\nDatos alumno\n\tRut: " + alu.getRut() + "\n\tNombre: " + alu.getNombre() + "\n\tApellido: "
								+ alu.getApellido() + "\n\tDireccion: " + alu.getDireccion() + "\n\nMaterias \n");
	
				alu.getMaterias().stream().forEach(mat -> {
					System.out.println("\t" + mat.getNombre());
					System.out.println("\t" + mat.getNotas().toString());
					;
				});
	
			});
		}
	}// cierre imprimir Alumnos
	
	public  String anadirNota(String rut, MateriaEnum mEnum, double nota) {	
		if (rut != "" && rut != null && mEnum != null) {
			int index = Metodos.indexSiExiste(listaAlumnos.get(rut).getMaterias(), mEnum);
				if (index>=0) {				
					listaAlumnos.get(rut).getMaterias().get(index).addNotas(nota);
					ArchivoServicio.agregarLinea(rut, mEnum, nota, listaAlumnos);//
					return "OK";
				}
				else { return "Materia no encontrada";}
		}
		else {return "No ingresado";}
	}// cierre anadir nota
	
	public  boolean validaSiRutExiste(String rutIngresado) {
		if (listaAlumnos.containsKey(rutIngresado)) {
			return true;
		}
		else 
			return false;
	}
	
	
}
