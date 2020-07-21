package utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import modelos.Materia;
import modelos.MateriaEnum;


public class Metodos {
	
	public static int indexSiExiste( List<Materia> lista, MateriaEnum mEnum) {
		for(Materia m: lista) { // devuelve el índice correspondiente a la posición de la enum en MateriaEnum
			if(m.getNombre().equals(mEnum)) {
				return lista.indexOf(m);
			}
		}
		return -1;
	}// cierre indexSiExiste  
	
	public static List<Materia> poblarMateriasDisp(List<Materia> materiasDelAlu){
		
		List<Materia> materiasDisp = new ArrayList<>();
			materiasDisp.add(new Materia(MateriaEnum.MATEMATICAS));//0
			materiasDisp.add(new Materia(MateriaEnum.LENGUAJE));//1
			materiasDisp.add(new Materia(MateriaEnum.CIENCIA));//2
			materiasDisp.add(new Materia(MateriaEnum.HISTORIA));//3
		// el uso del for tradicional a continuación permite utilizar sus indices para remover una determinada posición de la nueva lista			
			for (int j=0; j<materiasDelAlu.size(); j++) {
				for (int k=0; k<materiasDisp.size(); k++) {
					if (materiasDisp.get(k).getNombre().equals(materiasDelAlu.get(j).getNombre() ) ) {
						materiasDisp.remove(k);
						break; 
						//materiasDisp.remove(materiasDelAlu.get(k).getNombre().ordinal());
					}
				}
			}			
			//System.out.println("materias filtradas");
		return materiasDisp;
	 }// cierre poblarMateriasDisp

	
	
	public static boolean isNumeric(String datoIngresado) {
        boolean resultado;
        try {
            Double.parseDouble(datoIngresado);
            resultado = true;
        } 
        catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
	
	
	public static boolean validaNotaIngresada(String datoIngresado) {
		
		Pattern p1= Pattern.compile("[0-9]*"); // Pattern p1= Pattern.compile("\\d*");
		Pattern p2= Pattern.compile("[0-9].[0-9]*"); // Pattern p2= Pattern.compile("\\d.\\d*");
		Pattern p3= Pattern.compile("[0-9].[0-9][0-9]*"); // Pattern p3= Pattern.compile("\\d.\\d\\d*"); 
		Matcher m1 = p1.matcher(datoIngresado); Matcher m2 = p2.matcher(datoIngresado);	Matcher m3 = p3.matcher(datoIngresado);
		if (( m1.find() ||  ( m2.find() ||  m3.find() )  )   && Metodos.isNumeric(datoIngresado) )  {// 1 numero sin decimales
			//System.out.println("Dato correcto");
			return true;
		}				
		else {
			System.out.println("Si la nota es en decimal, escríbala con punto. Ejemplo: 5.6 ");
			return false;
		}
	}


}// cierre clase Metodos
