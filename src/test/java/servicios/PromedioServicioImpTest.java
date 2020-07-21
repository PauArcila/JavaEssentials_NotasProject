package servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test Calculo Promedio")
public class PromedioServicioImpTest {

	private static Logger logger = Logger.getLogger("edutecno.servicios.PromedioServicioImp");
	
	@Test
	@DisplayName("*** Test calcularPromedio ***")
	public void calcularPromedioTest(){
		logger.info("Test calcularPromedio");
		List<Double> dobles = new ArrayList<Double>();
		dobles.add(5.2);
		dobles.add(6.2);
		double respuesta = PromedioServicioImp.promedio(dobles);
		assertEquals(5.7, respuesta);
	}/*
	@Test
	@DisplayName("Test calcularPromedio")
	void testCalculadora1() {
		
		logger.info("test 2");
		//PromedioServicioImp promedioservicio = new PromedioServicioImp();
		List<Double> dobles = new ArrayList<Double>();
		double respuesta = PromedioServicioImp.promedio(dobles);
		assertEquals(0, respuesta);
	}*/
}
	
	

