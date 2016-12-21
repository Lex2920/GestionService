/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gestion.service;

import java.util.List;

import org.springframework.test.context.ContextConfiguration;

import fr.gestion.departement.SpringConfiguration;
import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Enseignement;
import fr.gestion.donnees.Voeu;

public interface Service {

	public void insertEnseignant(Enseignant enseignant);
	public Enseignant getEnseignant(String nom, String prenom);
	public List<Enseignant> getEnseignants();
	public List<Demande> getDemandesEnseignants(String nom, String prenom);
	public List<Demande> getDemandes();
	public List<Voeu> getVoeux();
	public void insertEnseignement(Enseignement ens);
	public Enseignement getEnseignement(Long id);

}