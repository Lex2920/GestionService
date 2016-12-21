package fr.gestion.donnees;

import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Voeu")
public class Voeu extends Demande {

	private static final long serialVersionUID = -3522905151378911691L;

	private int preference;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="enseignements")
	private Enseignement enseignement;
	@OneToMany(targetEntity = InterventionAuDepartement.class, cascade=CascadeType.ALL, mappedBy = "voeu")
	private List<InterventionAuDepartement> interventions;

	public Voeu() {
	}

	public Voeu(Enseignant enseignant, Enseignement enseignement) {
		super(enseignant);
		setEnseignement(enseignement);
	}
	
	public int getPreference() {
		return preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		if(enseignement == this.enseignement) return;
		if(this.enseignement != null && this.enseignement.getVoeux().contains(this)){
			this.enseignement.removeVoeu(this);
		}
		if(enseignement != null)
			enseignement.addVoeu(this);
		this.enseignement = enseignement;
	}
		
	public List<InterventionAuDepartement> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<InterventionAuDepartement> interventions) {
		this.interventions = interventions;
	}
	
	public void addIntervention(InterventionAuDepartement intervention){
		if(!this.interventions.contains(intervention)){
			this.interventions.add(intervention);
			intervention.setVoeu(this);
		}
	}
	
	public void removeIntervention(InterventionAuDepartement intervention){
		if(this.interventions.contains(intervention)){
			this.interventions.remove(intervention);
			intervention.setVoeu(null);
		}
	}

	@Override
    public String toString() {
        return super.toString() + " " + enseignement;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(heures).append(publiee).append(enseignant).append(enseignement).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
    	if(super.equals(obj)){
			return new EqualsBuilder().append(enseignement, ((Voeu)obj).enseignement).isEquals();
    	}
    	return false;
    }

}
