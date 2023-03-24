package cuentabancaria;

import java.io.IOException;
import java.util.List;

public class ContrasenaCuentaBancaria {
	
	public static void main(String args[]) {
		//Se instancia objeto que realiza la lógica de las operaciones
		ContraseniaUtil contraseniaUtil = new ContraseniaUtil();
		
		try {
			//Solicita entrada de la contraseña
			String contrasenia = contraseniaUtil.solicitarCadena();
			
			//Separa la linea de la contraseña en palabras si se encuentran separadas por espacios
			String[] contrasenias = contraseniaUtil.obtenerContrasenias(contrasenia);
			
			//Se obtienen las contrasenias que cumplen con las restricciones de formato
			List<String> contraseniasValidas = contraseniaUtil.validarContrasenias(contrasenias);
			
			//Si se encontraron contrasenias validas se obtiene la contraseña con mayor longitud
			if(!contraseniasValidas.isEmpty()) {
				System.out.println("Contraseñas válidas");
				contraseniasValidas.forEach(contValida -> {
					System.out.println(contValida);
				});
				
				//Se obtiene contraseña de mayor longitud
				List<String> contraseniasMayorLongitud = contraseniaUtil.obtenerContraseniaMayorLongitud(contraseniasValidas); 
				System.out.println();
				System.out.println("La(s) contraseña(s) mas larga es");
				contraseniasMayorLongitud.forEach(contraseniaMayor -> {
					System.out.println("La contraseña más larga es: " + contraseniaMayor +  " y su longitud es " + contraseniaMayor.length());
				});
				
				
			} else {
				System.out.println("No se encontraron contraseñas válidas");
			}
			
			
					
		} catch (IOException | NullPointerException e) {
			System.out.println(e);
		}
		
	}


}
