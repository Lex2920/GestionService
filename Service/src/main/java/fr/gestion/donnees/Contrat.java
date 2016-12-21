package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.gestion.datatypes.Hour;
@Entity
@Table(name = "Contrat")
public class Contrat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8837481738067099888L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id; 
	@Embedded
	@AttributeOverride(name="Count",column=@Column(name="hourMin"))
	private Hour min;
	@Embedded
	@AttributeOverride(name="Count",column=@Column(name="hourMax"))
	private Hour max;
	
	@OneToMany(targetEntity = Enseignant.class, cascade=CascadeType.ALL, mappedBy = "contrat")
	List<Enseignant> enseignants;
	
	public Contrat(){
		this.enseignants = new ArrayList<Enseignant>();
	}
	
	public Contrat(Hour min){
		this.min = min;
		this.enseignants = new ArrayList<Enseignant>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hour getMin() {
		return min;
	}

	public void setMin(Hour min) {
		this.min = min;
	}

	public Hour getMax() {
		return max;
	}

	public void setMax(Hour max) {
		this.max = max;
	}

	public List<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(List<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}
	
	public void addEnseignant(Enseignant enseignant){
		if(!this.enseignants.contains(enseignant)){
			this.enseignants.add(enseignant);
			enseignant.setContrat(this);
		}
	}
	
	public void removeEnseignant(Enseignant enseignant){
		if(this.enseignants.contains(enseignant)){
			this.enseignants.remove(enseignant);
			enseignant.setContrat(null);
		}
	}
	
	@Override
    public String toString() {
        return id + " " + min + " " + max;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(min).append(max).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Contrat) {
            final Contrat other = (Contrat) obj;
            return new EqualsBuilder().append(min, other.min).append(max, other.max).append(id, other.id).isEquals();
        }
        return false;
    }
}
