package fr.gestion.donnees.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.gestion.datatypes.Hour;
import fr.gestion.datatypes.TypeEnseignement;
import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;

public class EnseignementTest {

	@Test
	public void addVoeuTest() {
		Enseignant ens1 = new Enseignant("Molli", "Pascal");
		Enseignement e1 = new Enseignement(new Hour(1.5), TypeEnseignement.TD);
		Enseignement e2 = new Enseignement(new Hour(10), TypeEnseignement.CM);
		
		Voeu v = new Voeu(ens1, e1);
		
		assert(e1.getVoeux().contains(v));
		
		e1.addVoeu(v);
		
		assert(e1.getVoeux().contains(v));
		
		Voeu v2 = new Voeu(ens1, e1);
		
		assert(e1.getVoeux().contains(v2));
		
		e2.addVoeu(v2);
		
		assert(!e1.getVoeux().contains(v2));
		assert(e2.getVoeux().contains(v2));
	}
	
	@Test
	public void removeVoeuTest(){
		Enseignant ens1 = new Enseignant();
		Enseignement e1 = new Enseignement();
		Voeu v = new Voeu(ens1, e1);
		
		e1.removeVoeu(v);
		
		assert(!e1.getVoeux().contains(v));
		assert(v.getEnseignement() == null);
	}

}
