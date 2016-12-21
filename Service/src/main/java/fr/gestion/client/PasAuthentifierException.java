package fr.gestion.client;

public class PasAuthentifierException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031371289216313091L;
	
	@Override
	public String getMessage() {
		
		return super.getMessage() + "Erreur, pas authentifié !";
	}
}