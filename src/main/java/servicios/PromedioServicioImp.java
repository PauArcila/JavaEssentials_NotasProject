package servicios;

import java.util.List;

public class PromedioServicioImp {
	
	public static  double promedio(List<Double> valores) {
		double suma = valores.stream().reduce((double)0, (x,y) -> (x+y));
		double promedio = suma/valores.size();
		return promedio;
	}
}
