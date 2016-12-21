package fr.gestion.donnees.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;

public class DemandeTest {

	@Test
	public void testCreationDemande() {
		Enseignant e1 = new Enseignant();
		Demande d = new Demande(e1);
		
		assert(d.getEnseignant() == e1);
		assert(e1.getDemandes().contains(d));
	}
	
	@Test
	public void testSetEnseignant() {
		Enseignant e1 = new Enseignant();
		Enseignant e2 = new Enseignant();
		Demande d = new Demande(e1);
		
		assert(d.getEnseignant() == e1);
		
		d.setEnseignant(e2);
		
		assert(d.getEnseignant() != e1);
		assert(d.getEnseignant() == e2);
		
		assert(!e1.getDemandes().contains(d));
		assert(e2.getDemandes().contains(d));
	}

}
