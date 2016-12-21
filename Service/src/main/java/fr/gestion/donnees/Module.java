package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "Module")
public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5982334509921623977L;
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parcoursId")
	Parcours parcours;
	@OneToMany(targetEntity = Enseignement.class, cascade=CascadeType.ALL, mappedBy = "module")
	List<Enseignement> enseignements;
	
	public Module(){
		this.enseignements = new ArrayList<Enseignement>();
	}
	
	public Module(Parcours parcours){
		this.parcours = parcours;
		this.enseignements = new ArrayList<Enseignement>();
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		if(parcours == this.parcours) return;
		if(this.parcours != null && this.parcours.getModules().contains(this)){
			this.parcours.removeModule(this);
		}
		if(parcours != null)
			parcours.addModule(this);
		this.parcours = parcours;
	}

	public List<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}
	
	public void addEnseignement(Enseignement enseignement){
		if(!this.enseignements.contains(enseignement)){
			this.enseignements.add(enseignement);
			enseignement.setModule(this);
		}
	}
	
	public void removeEnseignement(Enseignement enseignement){
		if(this.enseignements.contains(enseignement)){
			this.enseignements.remove(enseignement);
			enseignement.setModule(null);
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

        if (obj instanceof Module) {
            final Module other = (Module) obj;
            return new EqualsBuilder().append(id, other.id).isEquals();
        }
        return false;
    }
}
