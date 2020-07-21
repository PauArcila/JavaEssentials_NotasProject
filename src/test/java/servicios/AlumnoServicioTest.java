package servicios;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import modelos.*;


public class AlumnoServicioTest {
	
    private static Logger logger = Logger.getLogger("edutecno.servicios.AlumnoServicioTest");
	private static AlumnoServicio servicioAlumno;
	private static Materia matematicas;
	private static Materia lenguaje;
	private static Alumno mapu;
	
	@BeforeAll
	static void setup() {
		
		matematicas = new Materia(MateriaEnum.MATEMATICAS);
		lenguaje = new Materia(MateriaEnum.LENGUAJE);
		mapu = new Alumno("12-3", "pepe", "gonzales", "su casa");
		servicioAlumno = mock(AlumnoServicio.class);
	}

	
	@Test
	@DisplayName("Testeo Crear alumno")
	public void testCrearAlumno() {
		
		logger.info("Testeo Crear alumno");
		when(servicioAlumno.crearAlumno(mapu)).thenReturn("Creado");
		String newAlumno = servicioAlumno.crearAlumno(mapu);
		assertEquals("Creado", newAlumno);
		verify(servicioAlumno).crearAlumno(mapu);
		}
	

	
	@Test
	@DisplayName("Testeo Agergar Materia")
	public void testAgregarMateria() {
		
		logger.info("Testeo AgregarMateria");
		when(servicioAlumno.agregarMateria(mapu.getRut(), lenguaje)).thenReturn("Materia agregada");
	
		String newMateria = servicioAlumno.agregarMateria(mapu.getRut(),lenguaje);
		assertEquals("Materia agregada", newMateria);
		verify(servicioAlumno).agregarMateria(mapu.getRut(),lenguaje);
	}
	
	@Test
	@DisplayName("Testeo materias por alumno")
	void testMateriasPorAlumno() {
		
		logger.info("Testeo materias por alumno");
		List<Materia> mockRespuestas = new ArrayList<Materia>();
		
		when(servicioAlumno.materiasPorAlumnos(mapu.getRut())).thenReturn(mockRespuestas);
		List<Materia> listaResp = servicioAlumno.materiasPorAlumnos(mapu.getRut());
		assertEquals(mockRespuestas,listaResp);
		verify(servicioAlumno).materiasPorAlumnos(mapu.getRut());
	}
	
	@Test
	@DisplayName("Testeo listar alumnos")
	void listarAlumnos() {
		
		logger.info("Testeo listar alumnos");
		List<Alumno> mockRespuestas=new ArrayList<Alumno>();
		
		when(servicioAlumno.listarAlumnos()).thenReturn(mockRespuestas);
		List<Alumno> listaResp = servicioAlumno.listarAlumnos();
		assertEquals(mockRespuestas,listaResp);
		verify(servicioAlumno).listarAlumnos();
	}
	
	
}// cierre Clase AlumnoServicioTest