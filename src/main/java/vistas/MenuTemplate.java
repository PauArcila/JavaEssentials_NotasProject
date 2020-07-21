package vistas;


import java.util.Scanner;

import modelos.Alumno;
import servicios.AlumnoServicio;

public abstract class MenuTemplate {
	static Scanner sc = new Scanner(System.in);
	public static void iniciarMenu() {
		
		MenuTemplate menuChico = new Menu();
		AlumnoServicio alumnoServicio = new AlumnoServicio();
		//despliega el menu al menos una vez y hasta que el usuario decida salir
		String seleccion; //variable que guarda seleccion elegida
		 
		System.out.print("\n--------------------------------------------- Menú Principal: \n");
		System.out.print("1. Crear Alumnos\n2. Listar Alumnos\n3. Agregar Materias\n"
					+ "4. Agregar Notas\n5. Cargar Datos \n6. Exportar Datos\n7. Salir \n\nSelección: ");
			seleccion = sc.nextLine();
			
			
			switch(seleccion) { //llama a cada método según la opcion elegida por el susuario
				case "1": 
					alumnoServicio.crearAlumno(menuChico.creaAlumno());
					iniciarMenu();
					break;
				
				case "2": 
					menuChico.listarAlumnos();
					iniciarMenu();
					break;
				
				case "3": 
					menuChico.agregaMateria();
					iniciarMenu();
					break;
				
				case "4": 
					menuChico.agregarNotaPasoUno();
					iniciarMenu();
					break;
				
				case "5": 
					menuChico.cargarDatos();
					iniciarMenu();
					break;
				
				case "6": 
					menuChico.exportarDatos();
					iniciarMenu();
					break;
				
				case "7": 
					menuChico.terminarPrograma();
					break;
				
				default:
					System.out.printf("Usted sabe que ' %s ' no es una opcion valida.\nR E I N T E N T E",seleccion);
					iniciarMenu();
					break;
			}
		
	}
	
	//Métodos que se van a llamar y sobreescrubir en la clase subclase Menu
	protected void exportarDatos() {
	}

	protected void cargarDatos() {
	}

	protected void agregarNotaPasoUno() {}

	protected void agregaMateria() {}

	protected void listarAlumnos() {
	}

	
	protected Alumno creaAlumno() {
		return null;
	}
	
	protected void terminarPrograma() {
	}

}// cierrre clase MenuTemplate
