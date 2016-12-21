package fr.gestion.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Departement;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;
import fr.gestion.service.Service;

public class FacadeClient implements IEmettre {
	
	Service as;
	ApplicationContext context;
	
	public Service getAs() {
		return as;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public Departement getDepartement() {
		return departement;
	}

	public Enseignant getCourant() {
		return courant;
	}

	Departement departement = new Departement("informatique"); 
	Enseignant courant;
	
	public FacadeClient(){
		context = new ClassPathXmlApplicationContext("client-beans.xml");
		as = (Service) context.getBean("serviceBean");
	}
	
	public void Authentifier(String nom, String prenom){
		courant = as.getEnseignant(nom, prenom); 
	}
	
	public void AjouterVoeu(Demande d) throws PasAuthentifierException{
		if(courant == null) throw new PasAuthentifierException();
		courant.addDemande(d);
	}
	
	public void SupprimerVoeu(Demande d) throws PasAuthentifierException{
		if(courant == null) throw new PasAuthentifierException();
		courant.removeDemande(d);
	}

	public void Emettre(Enseignant enseignant, Demande d, Departement dpt) throws PasAuthentifierException, VoeuEnConflitException {
		if(courant == null) throw new PasAuthentifierException();
		if(ConflitDetecte(d)) throw new VoeuEnConflitException();
		
		as.insertEnseignant(enseignant);
	}
	
	public boolean ConflitDetecte(Demande d){
		
		if(!(d instanceof Voeu)) return false;
		Voeu v = (Voeu)d;
		System.out.println("enseignement de notre voeu: "+ v.getEnseignement());
		List<Voeu> voeux =as.getVoeux();
		System.out.println("Voeux null : "+ voeux == null);
		if(voeux != null){
			for(Voeu voeu : voeux){
				System.out.println("voeu : "+ voeu);
				System.out.println("enseignement : "+ voeu.getEnseignement());
				if(voeu.getEnseignement().equals(v.getEnseignement())){
					System.out.println("egaux ! ");
					return true;
				}
			}
			System.out.println("pas egaux ! ");
		}
		return false;
	}

}