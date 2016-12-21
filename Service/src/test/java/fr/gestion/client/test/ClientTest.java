package fr.gestion.client.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gestion.datatypes.Hour;
import fr.gestion.datatypes.TypeEnseignement;
import fr.gestion.departement.EnseignantRepository;
import fr.gestion.departement.SpringConfiguration;
import fr.gestion.donnees.Contrat;
import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;
import fr.gestion.service.Service;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ClientTest {
	Service as;
	ApplicationContext context;
		
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("client-beans.xml");
		as = (Service) context.getBean("serviceBean");
	}
		
	@Test
	public void addRetrieveEnseignant() {
		Enseignant a = new Enseignant("Molli", "Pascal");
		
		as.insertEnseignant(a);

		List<Enseignant> enseignants = as.getEnseignants();
		if(enseignants != null){
			for (Enseignant each : enseignants) {
				System.out.println("enseignants : "+each);
			}
		}
		
		
	}
	
	@Test
	public void addRetrieveDemandeEnseignant(){
		Enseignant a = new Enseignant("Skaf", "Hala");
		a.addDemande(new Demande());
		a.addDemande(new Demande());
		a.addDemande(new Demande());
	
		as.insertEnseignant(a);
		
		List<Demande> demandes = as.getDemandesEnseignants("Skaf", "Hala");
		if(demandes != null){
			for (Demande each : demandes) {
				System.out.println("demande : "+each);
			}
		}
	}
	
	@Test
	public void test(){
		Contrat c1 = new Contrat(new Hour(20.5f));
		Contrat c2 = new Contrat(new Hour(30f));
		Enseignant e1 = new Enseignant(c1, "Molli", "Pascal");
		Enseignant e2 = new Enseignant(c2, "Skaf", "Hala");		
		
		Enseignement ens1 = new Enseignement(new Hour(10.0), TypeEnseignement.CM);
		Enseignement ens2 = new Enseignement(new Hour(14.0), TypeEnseignement.TD);
		
		Demande d1 = new Voeu(e2, ens1);
		Demande d2 = new Voeu(e1,ens2);
		
		e1.addDemande(d2);
		e2.addDemande(d1);	
		
		as.insertEnseignant(e1);
		as.insertEnseignant(e2);
		
		List<Demande> demandes = as.getDemandesEnseignants("Skaf", "Hala");
		if(demandes != null){
			for (Demande each : demandes) {
				System.out.println("demande : "+each);
			}
		}
	}

}
