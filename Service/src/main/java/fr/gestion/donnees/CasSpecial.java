package fr.gestion.donnees;

import javax.persistence.*;

@Entity
@Table(name = "CasSpecial")
public class CasSpecial extends Intervention {
	
	public CasSpecial() {

	}

	public CasSpecial(ServiceEnseignant service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

}
