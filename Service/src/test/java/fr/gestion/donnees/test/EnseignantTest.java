package fr.gestion.donnees.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;

public class EnseignantTest {

	@Test
	public void addDemandeTest() {
		Enseignant e1 = new Enseignant();
		Enseignant e2 = new Enseignant();
		Demande d = new Demande(e1);
		
		assert(e1.getDemandes().contains(d));
		
		e1.addDemande(d);
		
		assert(e1.getDemandes().contains(d));
		
		Demande d2 = new Demande(e1);
		
		assert(e1.getDemandes().contains(d2));
		
		e2.addDemande(d2);
		
		assert(!e1.getDemandes().contains(d2));
		assert(e2.getDemandes().contains(d2));
	}
	
	@Test
	public void removeDemandeTest(){
		Enseignant e1 = new Enseignant();
		Demande d1 = new Demande(e1);
		
		e1.removeDemande(d1);
		
		assert(!e1.getDemandes().contains(d1));
		assert(d1.getEnseignant() == null);
	}

}
