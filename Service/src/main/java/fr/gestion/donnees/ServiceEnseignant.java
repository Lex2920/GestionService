package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "ServiceEnseignant")
public class ServiceEnseignant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -547351976473608236L;
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(targetEntity = Intervention.class, cascade=CascadeType.ALL, mappedBy = "service")
	private List<Intervention> interventions;
	@ManyToMany(targetEntity = Enseignant.class, cascade=CascadeType.ALL, mappedBy = "services")
	private List<Enseignant> enseignants;
	
	public ServiceEnseignant(){
		this.interventions = new ArrayList<Intervention>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public List<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}
	
	public void addIntervention(Intervention intervention){
		if(!this.interventions.contains(intervention)){
			this.interventions.add(intervention);
			intervention.setService(this);
		}
	}
	
	public void removeIntervention(Intervention intervention){
		if(this.interventions.contains(intervention)){
			this.interventions.remove(intervention);
			intervention.setService(null);
		}
	}
	
	public void addEnseignant(Enseignant enseignant){
		if(!this.enseignants.contains(enseignant)){
			this.enseignants.add(enseignant);
			enseignant.addService(this);
		}
	}
	
	public void removeEnseignant(Enseignant enseignant){
		if(this.enseignants.contains(enseignants)){
			this.enseignants.remove(enseignants);
			enseignant.removeService(this);
		}
	}
	
	@Override
    public String toString() {
        return id +"";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof ServiceEnseignant) {
            final ServiceEnseignant other = (ServiceEnseignant) obj;
            return new EqualsBuilder().append(id, other.id).isEquals();
        }
        return false;
    }
	
}
