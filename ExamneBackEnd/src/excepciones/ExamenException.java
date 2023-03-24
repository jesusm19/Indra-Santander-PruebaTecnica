package excepciones;

public class ExamenException extends Exception{

	private static final long serialVersionUID = 1303454450535514738L;

    /**
     * Constructor
     * @param message Mensaje de error
     */
    public ExamenException(String message) {
        super(message);
    }
    
	
}
