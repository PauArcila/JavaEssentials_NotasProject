package servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import utilidades.Metodos;

public class ArchivoServicio {

	// nuevo
		public static boolean existeServicio(String ruta) {
			File archivo = new File("/" + ruta);
			if (!archivo.exists()) {
				return true;
			} else {
				return false;
			}
		}

		// nuevo
		public static Map<String, Alumno> cargarDatos(String ruta, boolean mensajes) {
			if (existeServicio(ruta)) {
				if (mensajes)
					System.out.println("Archivo Encontrado");
				try {
					BufferedReader br = new BufferedReader(new FileReader(ruta));
					if (mensajes)
						System.out.println("Cargando datos");
					List<List<String>> datos = br.lines().map(line -> line.split(","))
							.map(value -> new ArrayList<>(Arrays.asList(value[0], value[1], value[2], value[3])))
							.collect(Collectors.toList());
					if (mensajes)
						System.out.println("Lista creada");
					br.close();
					return csvListToMap(datos, mensajes);
				} 
				catch ( IOException e) {
					System.out.println("Probmeas con el archivo "+e.getMessage() );
					return null;
				}
			} else {
				System.out.println("Archivo no encontrado");
				return null;
			}
		}// cierre cargar datos

		//nuevo
		private static Map<String, Alumno> csvListToMap(List<List<String>> datos, boolean mensajes) {

			Map<String, Alumno> listaAlumnos = new HashMap<String, Alumno>();
			if (mensajes)
				System.out.println("Transformando lista a mapa");
			for (List<String> a : datos) {
				// System.out.println(a);
				String rutAlumno = a.get(0).toString();
				String nombreAlumno = a.get(1).toString();
				MateriaEnum materiaAlumno = MateriaEnum.valueOf(a.get(2).toString());
				double notaAlumno = Double.parseDouble(a.get(3).toString());

				if (listaAlumnos.containsKey(rutAlumno)) {
					if (mensajes)
						System.out.print("█");
					int indexx = Metodos.indexSiExiste(listaAlumnos.get(rutAlumno).getMaterias(), materiaAlumno);
					if (indexx >= 0) {
						if (mensajes)
							System.out.print("█");

						listaAlumnos.get(rutAlumno).getMaterias().get(indexx).addNotas(notaAlumno);

					} else {
						if (mensajes)
							System.out.print("█");
						Materia materia = new Materia(materiaAlumno);
						materia.getNotas().add(notaAlumno);
						listaAlumnos.get(rutAlumno).addMaterias(materia);
					}
				} else {
					if (mensajes)
						System.out.print("█");
					Alumno alumno = new Alumno(rutAlumno, nombreAlumno, "", "");
					Materia materia = new Materia(materiaAlumno);
					materia.getNotas().add(notaAlumno);

					alumno.addMaterias(materia);
					listaAlumnos.put(alumno.getRut(), alumno);
				}
			}
			if (mensajes)
				System.out.println("\nMapa Creado");
			System.out.println("Datos cargados correctamente.");
			return listaAlumnos;
		}

		//nuevo
		public static void exportarDatos(Map<String, Alumno> mapAlumnos, String ruta) {
			Scanner sc = new Scanner(System.in);
			String respuesta;
			if (existeServicio(ruta)) {

				System.out.println("Ya existe el archivo\nDesea sobre escribir? (si/no)");
				do {
					respuesta = sc.nextLine().toLowerCase();
					if (!respuesta.equals("si") && !respuesta.equals("no")) {
						System.out.println("respuesta incorrecta, reintente:");
					}

				} while (!respuesta.equals("si") && !respuesta.equals("no"));
				sc.close();
				if (respuesta.equals("si")) {
					try {
						File archivo = new File(ruta);
						archivo.createNewFile();
						FileWriter fw = new FileWriter(archivo, true);
						mapAlumnos.values().stream().forEach(x -> {
							try {
								List<Double> notas = new ArrayList<Double>();

								fw.write(
										x.getNombre() + "\n");

								// TODO Auto-generated catch block

								x.getMaterias().stream().forEach(y -> {
									notas.addAll(y.getNotas());
									try {
										fw.write(String.format("\t%s %.1f\n", y.getNombre(),
												PromedioServicioImp.promedio(y.getNotas())));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								});
								fw.write(String.format("\tPromedio general: %.1f\n", PromedioServicioImp.promedio(notas)));
							} catch (IOException e) {
								e.printStackTrace();
							}
						});

						System.out.println("Datos exportados al archivo "+ruta);

						fw.flush();
						fw.close();

					} catch (Exception e) {
						System.out.println("Error al crear archivo:\n" + e.getMessage());
					}
				} else {
					System.out.println("Exportacion cancelada");
				}

			}
		}
		
		public static void agregarLinea(String rut, MateriaEnum mEnum, double nota, Map<String, Alumno> listaAlumnos) {
			//en AlumnoServicio se llama este métdodo (linea 65) se agrega una nota a un alumno(rut) en una materia
			try {
				if (listaAlumnos.containsKey(rut)) {
					String linea = rut + "," + listaAlumnos.get(rut).getNombre() + "," + mEnum + "," + nota;
					FileWriter fw = new FileWriter(new File("notas.csv"), true);
					fw.write(linea + "\n");
					fw.flush();
					fw.close();	
				}
			}
			catch(Exception e){
				System.out.println("Ocurrió excepción " +e);
			}
		}// cierre agregar linea
		
		
}// cierre clase
