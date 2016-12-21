package fr.gestion.donnees.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;

public class VoeuTest {

	@Test
	public void testCreationVoeu() {
		Enseignant ens1 = new Enseignant();
		Enseignement e1 = new Enseignement();
		Voeu v = new Voeu(ens1, e1);
		
		assert(v.getEnseignement() == e1);
		assert(e1.getVoeux().contains(v));
	}
	
	@Test
	public void testSetEnseignement() {
		Enseignant ens1 = new Enseignant();
		Enseignement e1 = new Enseignement();
		Enseignement e2 = new Enseignement();
		Voeu v = new Voeu(ens1, e1);
		
		assert(v.getEnseignement() == e1);
		
		v.setEnseignement(e2);
		
		assert(v.getEnseignement() != e1);
		assert(v.getEnseignement() == e2);
		
		assert(!e1.getVoeux().contains(v));
		assert(e2.getVoeux().contains(v));
	}

}
