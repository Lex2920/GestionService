package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.service.spi.ServiceRegistryAwareService;

@Entity
@Table(name = "Departement")
public class Departement implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -6521894416461174524L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="nom")
	private String nom;
	
	@OneToMany(targetEntity = Enseignant.class, cascade=CascadeType.ALL, mappedBy = "departement")
	List<Enseignant> enseignants;
	@OneToMany(targetEntity = Parcours.class, cascade=CascadeType.ALL, mappedBy = "departement")
	List<Parcours> parcours;
	
	public Departement(){
		this.enseignants = new ArrayList<Enseignant>();
		this.parcours = new ArrayList<Parcours>();
	}
	
	public Departement(String nom){
		this.enseignants = new ArrayList<Enseignant>();
		this.parcours = new ArrayList<Parcours>();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}

	public List<Parcours> getParcours() {
		return parcours;
	}

	public void setParcours(List<Parcours> parcours) {
		this.parcours = parcours;
	}
	
	public void addParcours(Parcours parcours){
		if(!this.parcours.contains(parcours)){
			this.parcours.add(parcours);
			parcours.setDepartement(this);
		}
	}
	
	public void removeParcours(Parcours parcours){
		if(this.parcours.contains(parcours)){
			this.parcours.remove(parcours);
			parcours.setDepartement(null);
		}
	}
	
	public void addEnseignant(Enseignant enseignant){
		if(!this.enseignants.contains(enseignant)){
			this.enseignants.add(enseignant);
			enseignant.setDepartement(this);
		}
	}
	
	public void removeEnseignant(Enseignant enseignant){
		if(this.enseignants.contains(enseignant)){
			this.enseignants.remove(enseignant);
			enseignant.setDepartement(null);
		}
	}
	
	@Override
    public String toString() {
        return id + " " + nom;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(nom).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Departement) {
            final Departement other = (Departement) obj;
            return new EqualsBuilder().append(nom, other.nom).append(id, other.id).isEquals();
        }
        return false;
    }
	
}
