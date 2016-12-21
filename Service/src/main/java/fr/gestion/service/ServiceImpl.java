/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gestion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import fr.gestion.departement.DemandeRepository;
import fr.gestion.departement.EnseignantRepository;
import fr.gestion.departement.EnseignementRepository;
import fr.gestion.departement.SpringConfiguration;
import fr.gestion.departement.VoeuRepository;
import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;

@ContextConfiguration(classes = SpringConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ServiceImpl implements Service {

	@Autowired(required = true)
	private EnseignantRepository repoEnseignant;
	
	@Autowired(required = true)
	private DemandeRepository repoDemande;
	
	@Autowired(required = true)
	private EnseignementRepository repoEnseignement;
	
	@Autowired(required = true)
	private VoeuRepository repoVoeu;

	public void insertEnseignant(Enseignant ens) {
		repoEnseignant.save(ens);
	}
	
	public List<Demande> getDemandesEnseignants(String nom, String prenom){
		return new ArrayList<Demande>(repoEnseignant.allDemandesOf(nom, prenom));
	}

	public List<Enseignant> getEnseignants() {
		return new ArrayList<Enseignant>(repoEnseignant.allEnseignant());
	}

	public Enseignant getEnseignant(String nom, String prenom) {
		return (Enseignant)repoEnseignant.find(nom, prenom).iterator().next();
	}
	
	public List<Demande> getDemandes(){
		return new ArrayList<Demande>(repoDemande.allDemandes());
	}

	public List<Voeu> getVoeux(){
		return (List<Voeu>) repoVoeu.findAll();
	}
	
	public void insertEnseignement(Enseignement ens) {
		repoEnseignement.save(ens);
	}
	
	public Enseignement getEnseignement(Long id){
		return repoEnseignement.findOne(id);
	}
	
}