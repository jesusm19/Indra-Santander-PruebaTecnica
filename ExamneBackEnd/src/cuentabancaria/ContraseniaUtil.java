package cuentabancaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContraseniaUtil {
	
	/**
	 * Expresion regular para el formato alfanumerico de la contraseña
	 * */
	private static final String FORMATO_ALFANUMERICO = "^[a-zA-Z0-9]*$";
	
	/**
	 * Expresion regular para el validar que sea letras
	 * */
	private static final String FORMATO_LETRAS = "^[a-zA-Z]*$";
	
	/**
	 * Expresion regular para validar numeros
	 * */
	private static final String FORMATO_NUMEROS = "[0-9]+";
	

	/**
	 * Este metodo permite solicitar la entrada de la contraseña a través del teclado
	 * @return String
	 * @throws IOException 
	 * */
	public String solicitarCadena() throws IOException {
		//Se utiliza Buffer reader para que pueda obtener espacios de la entrada
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("Ingrese su contraseña: ");

		return br.readLine();
	}

	/**
	 * Este metodo permite separar la contraseña de entrada que da el usuario en palabras, en caso de que haya espacios
	 * @param contrasenia es la entrada que da el usuario
	 * @return String[]
	 * */
	public String[] obtenerContrasenias(String contrasenia) {
		return contrasenia.split(" ");
	}

	/**
	 * Este metodo permite realizar la validacion de la lista de contraseñas que se obtuvo por parte del usuario
	 * y se regresa en un arreglo las que cumplen con el formato de la contraseña
	 * @param contrasenias es la lista de contraseñas que se obtuvieron de la separación de contraseñas de la entrada del usuario
	 * @return List<String>
	 * */
	public List<String> validarContrasenias(String[] contrasenias) {
		List<String> contraseniasValidas = new ArrayList<>();
		//Se recorre el arreglo de la lista de contraseñas
		for (String contrasenia : contrasenias) {
			//Si la contrasenia cumple con el formato de contrasenias, se agrega a una lista de contraseñas validas
			if(validarFormatoContrasenia(contrasenia)) {
				contraseniasValidas.add(contrasenia);
			}
		}
		return contraseniasValidas;
	}
	
	/**
	 * Este metodo que la contraseña cumpla con la expresion regular
	 * @param contrasenia
	 * @return boolean
	 * */
	private boolean validarFormatoContrasenia(String contrasenia) {
		if(!contrasenia.matches(FORMATO_ALFANUMERICO)) return false;
		if(!validarNumeroParLetras(contrasenia)) return false;
		if(!validarNumeroImparDigitos(contrasenia)) return false;
		
		return true;
	}

	/**
	 * Este metodo permite contar el numero de digitos y validar que la cantidad sea un número impar
	 * @param contrasenia
	 * @return boolean
	 * */
	private boolean validarNumeroImparDigitos(String contrasenia) {
		int contadorNumeros = 0;
		//Se itera la cadena para validar que el caracter sea una letra
		for (int i = 0; i < contrasenia.length(); i++) {
			if(String.valueOf(contrasenia.charAt(i)).matches(FORMATO_NUMEROS)) {
				contadorNumeros ++;
			}
	    }
		//Se pregunta si el contador de numero es impar
		if(contadorNumeros % 2 != 0) return true;
		
		return false;
	}

	/**
	 * Este metodo valida que exista un numero par de letras en la contraseña
	 * @param contrasenia
	 * @return boolean
	 * */
	private boolean validarNumeroParLetras(String contrasenia) {
		int contadorLetras = 0;
		//Se itera la cadena para validar que el caracter sea una letra
		for (int i = 0; i < contrasenia.length(); i++) {
			if(String.valueOf(contrasenia.charAt(i)).matches(FORMATO_LETRAS)) {
				contadorLetras ++;
			}
	    }
		//Se pregunta si el contador de letras es == 0 o el numero de letras sea un par
		if(contadorLetras == 0 || contadorLetras % 2 == 0) return true;
		
		return false;
	}

	/**
	 * Este metodo obtiene la contraseña de mayor longitud
	 * @param contraseniasValidas es la lista de contraseñas que cumpplen con el formato
	 * @return List<String>
	 * */
	public List<String> obtenerContraseniaMayorLongitud(List<String> contraseniasValidas) {
		List<String> contraseniasMayorLongitud = new ArrayList<>();
		int mayourLongitud = 0;
		//Se itera la lista de contraseñas para obtener la mayor longitud
		for (String contrasenia : contraseniasValidas) {
			if(mayourLongitud < contrasenia.length()) {
				mayourLongitud = contrasenia.length();
			}
		}
		
		//Se realiza una segunda iteración para verificar que haya mas de una contraseña con la misma longitud
		for (String contrasenia : contraseniasValidas) {
			if(mayourLongitud == contrasenia.length()) {
				contraseniasMayorLongitud.add(contrasenia);
			}
		}
		
		
		return contraseniasMayorLongitud;
	}
}
