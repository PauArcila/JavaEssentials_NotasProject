package vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.Alumno;
import modelos.Materia;
import servicios.AlumnoServicio;
import servicios.ArchivoServicio;
import utilidades.Metodos;

public class Menu extends MenuTemplate {

	AlumnoServicio alumnoServicio = new AlumnoServicio();

	@Override
	public void cargarDatos() {
		System.out.println("--------------------------------------------- Cargar Datos:\n");
		System.out.println("Ingrese la ruta en donde se ecnuentra el archivo notas.csv :");
		System.out.println("(Deje en blanco para leer desde la raiz del archivo actual)");
		//System.out.print("/");
		Scanner scc = new Scanner(System.in);
		String ruta = scc.nextLine();
		//scc.close();
		alumnoServicio.poblarAlumno(ruta);
		
		// System.out.println("Metodo cargarDatos de Menu trae a
		// AlumnoServicio.PoblarAlumno()");
	}

	@Override
	protected void listarAlumnos() {
		alumnoServicio.imprimirAlumnos(alumnoServicio.listarAlumnos()); // alumnoServicio.imprimirAlumnos(alumnoServicio.listarAlumnos());
	}

	@Override
	public void agregarNotaPasoUno() {

		Scanner rt = new Scanner(System.in);
		Scanner mt = new Scanner(System.in);
		Scanner nt = new Scanner(System.in);// ok
		String rutAlu, notaString,op;
		boolean continuar = true, notaValida = false;
		double nota;
		int nroRamo=0;
		System.out.print("--------------------------------------------- Agregar Nota:\nIngresa rut del Alumno: ");
		// validar rut

		rutAlu = rt.nextLine();
		rt.close();
		if (!alumnoServicio.validaSiRutExiste(rutAlu)) {
			System.out.println("Rut no encontrado");
			continuar = false;
		}
		// fin validación
		if (continuar) {

			List<Materia> materiasDelAlu = alumnoServicio.materiasPorAlumnos(rutAlu);

			if (materiasDelAlu.size() == 0) {
				System.out.println("\nEl alumno no tiene materias agregadas\nVolviendo al menú...");
				MenuTemplate.iniciarMenu();
			}

			else {
				System.out.println("\nAlumno tiene las siguientes materias agregadas:" /* +materiasDelAlu.size() */);
				for (int i = 0; i < materiasDelAlu.size(); i++) {
					System.out.println("\t" + (i + 1) + ". " + materiasDelAlu.get(i).getNombre());
				}
				// materiasDelAlu.stream().forEach(x->System.out.println("\t"+(x.getNombre().ordinal()+1)+"."+x.getNombre()
				// ) );

				System.out.print("\nSeleccionar materia: ");
				do {
					op=mt.nextLine();
					if(Metodos.isNumeric(op)) {
						nroRamo = Integer.parseInt(op);
						if (nroRamo > materiasDelAlu.size() || nroRamo <= 0) {
							System.out.println("Opcion ingresada no valida, REINTENTE");
						}

					}else
						System.out.println("Opcion ingresada no valida, REINTENTE");
						
					
				} while (nroRamo > materiasDelAlu.size() || nroRamo <= 0);

				do {// nueva linea VALIDAR NOTA
					// //////////////////////////////////////////////////////////////
					System.out.print("\nIngresar nota: ");
					notaString = nt.nextLine(); ///// se lee la nota como string para procesarla en las validaciones
					// nueva linea
					notaValida = Metodos.validaNotaIngresada(notaString);
					if (!notaValida) {
						System.out.println("Ingrese una nota válida");
					} else if (notaValida)// si el formato de la nota ingresada es válido guarda la nota
					{
						nota = Double.parseDouble(notaString);
						if (nota >= 1 && nota <= 10) {
							alumnoServicio.anadirNota(rutAlu, materiasDelAlu.get(nroRamo - 1).getNombre(), nota);
							System.out.println("Nota agregada a " + materiasDelAlu.get(nroRamo - 1).getNombre()
									+ " Nota: " + nota);
							notaValida = true;
						} else { // en caso de que la nota sea numérica, cumpla con las validaciones anteriores
									// pero esté fuera de rango
							System.out.println("La nota debe estar entre 1 y 10");// esta validación se hace acá porque
																					// antes la nota no era numerica
							notaValida = false;
						}

					}
				} while (!notaValida);

			}
		}
		mt.close();
		nt.close();
		System.out.println("...volviendo al menú principal\n");
	}// cierre agregar notaPasoUno

	@Override
	public void agregaMateria() {

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		String rutAlu, op;
		boolean continuar = true;
		int opcInt = 0;
		System.out.print("--------------------------------------------- Agregar Materia:\nIngresa rut del Alumno: ");
		// validar rut

		rutAlu = sc.nextLine();
		if (!alumnoServicio.validaSiRutExiste(rutAlu)) {
			System.out.println("Rut no encontrado");
			continuar = false;

		}
		// fin validación
		if (continuar) {

			List<Materia> materiasDisp = new ArrayList<>();
			materiasDisp = Metodos.poblarMateriasDisp(alumnoServicio.materiasPorAlumnos(rutAlu));
			if (materiasDisp.size() == 0) {
				System.out.println("\nEl alumno ya tiene todas las materias asignadas.");

			} else {
				System.out.println("\nMaterias:" /* +materiasDisp.size() */); // materiasDisp.stream().forEach( x->{ int
																				// n=x.getNombre().ordinal()-(x.getNombre().ordinal()-1);//x.getNombre().ordinal();
				// System.out.println("\t"+(n+1)+". " +x.getNombre() ) ; } );
				for (int i = 0; i < materiasDisp.size(); i++) {
					System.out.println("\t" + (i + 1) + ". " + materiasDisp.get(i).getNombre());
				}

				System.out.print("Selecciona una Materia: ");
				do {
					op = sc1.nextLine();
					if(Metodos.isNumeric(op)) {
						opcInt = Integer.parseInt(op);
						if (opcInt <= 0 || opcInt > materiasDisp.size()) {
							System.out.println("opcion ingresada no valida, REINTENTE");
						}

					}else {
						System.out.println("opcion ingresada no valida, REINTENTE");
					}
					
				} while (opcInt <= 0 || opcInt > materiasDisp.size());

				// materiasDisp.size()
				Materia materia = new Materia(materiasDisp.get(opcInt - 1).getNombre()); // System.out.println("--"+materiasDisp.get(Integer.parseInt(op)-1).getNombre()
																							// );
				alumnoServicio.agregarMateria(rutAlu, materia);
				System.out.println("Alumno agregado a " + materia.getNombre());

			}
		}
		sc.close();
		sc1.close();
		System.out.println("...volviendo al menú principal\n");
	} // cierre agregar materia

	@Override
	public Alumno creaAlumno() {
		Scanner sc = new Scanner(System.in);
		String rut, nombre, apellido, direccion;
		System.out.print("--------------------------------------------- Crear Alumno\nIngresa rut del Alumno: ");
		rut = sc.nextLine();

		System.out.print("Ingrese nombre del alumno: ");
		nombre = sc.nextLine();

		System.out.print("Ingrese apellido del alumno: ");
		apellido = sc.nextLine();

		System.out.print("Ingrese dirección del alumno: ");
		direccion = sc.nextLine();

		Alumno alumno = new Alumno(rut, nombre, apellido, direccion);
		sc.close();
		return alumno;
	}// cierre crea Alumno

	@Override
	public void exportarDatos() {
		System.out.println("--------------------------------------------- Exportar Datos:\n");
		System.out.println("Ingresa la ruta en donde se guardara el archivo promedios.txt\n/");
Scanner sc = new Scanner(System.in);
		String ruta =sc.nextLine();
		
		
		ArchivoServicio.exportarDatos(AlumnoServicio.listaAlumnos, ruta+"promedios.txt");
		terminarPrograma();
	}

	@Override
	public void terminarPrograma() {
		System.out.println("...Hasta pronto!");
		System.exit(0);
	}

}// fin clase MenuTemplate
