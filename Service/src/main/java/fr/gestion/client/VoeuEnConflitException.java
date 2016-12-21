package fr.gestion.client;

public class VoeuEnConflitException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5586330198777109743L;
	
	@Override
	public String getMessage() {
		
		return super.getMessage() + "Voeu en conflit !";
	}
}