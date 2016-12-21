package fr.gestion.client;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Departement;
import fr.gestion.donnees.Enseignant;

public interface IEmettre {
	public void Emettre(Enseignant enseignant, Demande d, Departement dpt)  throws  PasAuthentifierException, VoeuEnConflitException;
}
