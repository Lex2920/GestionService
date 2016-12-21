package fr.gestion.donnees;

import javax.persistence.*;

@Entity
@Table(name = "DemandeSpeciale")
public class DemandeSpeciale extends Demande {

	private static final long serialVersionUID = 6478934123003542559L;

	/**
	 * 
	 */

	public DemandeSpeciale() {
	}

	public DemandeSpeciale(Enseignant enseignant) {
		super(enseignant);
	}

}
