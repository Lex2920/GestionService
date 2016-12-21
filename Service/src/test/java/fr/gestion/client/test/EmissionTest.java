package fr.gestion.client.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gestion.client.FacadeClient;
import fr.gestion.client.PasAuthentifierException;
import fr.gestion.client.VoeuEnConflitException;
import fr.gestion.datatypes.Hour;
import fr.gestion.datatypes.TypeEnseignement;
import fr.gestion.donnees.Contrat;
import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EmissionTest {
	
	FacadeClient facade;
	Enseignant e1;
	Enseignant e2;
	
	Enseignement ens1;
	Enseignement ens2;
	
	Demande d1;
	Demande d2;
	Demande d3;
	
	@Before
	public void init(){
		facade = new FacadeClient();
		//ajout de quelques enseignants, cours et demandes...
		Contrat c1 = new Contrat(new Hour(20.5f));
		Contrat c2 = new Contrat(new Hour(30f));
		e1 = new Enseignant(c1, "Molli", "Pascal");
		e2 = new Enseignant(c2, "Skaf", "Hala");		
		
		ens1 = new Enseignement(new Hour(10.0), TypeEnseignement.CM);
		ens2 = new Enseignement(new Hour(14.0), TypeEnseignement.TD);
		
		d1 = new Voeu(e2, ens1);
		d2 = new Voeu(e1, ens2);
			
		facade.getAs().insertEnseignant(e1);
		facade.getAs().insertEnseignant(e2);
		
		List<Enseignant> enseignants = facade.getAs().getEnseignants();
		if(enseignants != null){
			for (Enseignant each : enseignants) {
				System.out.println("enseignants : "+each);
			}
		}
	}

	/*@Test
	public void TestAuthentification(){
		facade.Authentifier("Molli", "Pascal");
		assert(facade.getCourant()!=null);	}
	*/
	@Test 
	public void TestConflit(){
		/*d3 = new Voeu(facade.getAs().getEnseignant("Skaf", "Hala"), facade.getAs().getEnseignement(0L));
		
		assert(facade.ConflitDetecte(d3));*/
	}
	
	
	@Test
	public void TestAjoutVoeu(){

		Demande d1 = new Voeu(e1, ens1);
		Demande d2 = new Voeu(e1, ens2);
		
		try {
			facade.Authentifier("Molli", "Pascal");
			facade.AjouterVoeu(d1);
			facade.AjouterVoeu(d2);
			
			assert(facade.getCourant().getDemandes().contains(d1));
			assert(facade.getCourant().getDemandes().contains(d2));
			//facade.Emettre(null, null, null);
		} catch (PasAuthentifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (VoeuEnConflitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
