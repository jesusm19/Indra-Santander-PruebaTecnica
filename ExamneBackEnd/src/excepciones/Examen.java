package excepciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Examen {
	
	
	
	public static void main(String[] args) {
		System.out.println("Intentamos ejecutar el bloque de instrucciones:");
		System.out.println("Instrucción 1.");
		
		/*************************************
		 * Justificacion
		 * Se declara una sentencia de try catch con la excepción de NumberFormatException,
		 *  ya que manda un error de tiempo de ejecución al intentar convertir un String a un valor entero
		 * ************************************/
		try {
			int n = Integer.parseInt("M"); // error forzado en tiempo de ejecución.
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		
		System.out.println("Instrucción 2.");
		System.out.println("Instrucción 3, etc.");
		
		System.out.println("\n\n");
		try {
			getFecha("","");
		} catch (ExamenException e) {
			System.out.println(e);
		}
		
	}
	
	public static Date getFecha(String fecha, String formato) throws ExamenException {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
	    String dateInString = fecha;
	    Date fechaSalida = null;
	    
	    try {
			fechaSalida = formatter.parse(dateInString);
		} catch (ParseException e) {
			throw new ExamenException("Error al convertir la fecha");
		}
			
	    	return fechaSalida;
	}


	
}
