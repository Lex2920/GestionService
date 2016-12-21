package fr.gestion.donnees;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.gestion.datatypes.Hour;

@Entity
public class Demande implements Serializable {
	
	private static final long serialVersionUID = -3986178066499710949L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
	
	@Embedded
	@Column(name="heures")
	protected Hour heures;
	@Column(name="publiee")
	protected boolean publiee;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="enseignant")
	protected Enseignant enseignant;
	
	public Demande(){
		
	}
	
	public Demande(Enseignant enseignant){
		setEnseignant(enseignant);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hour getHeures() {
		return heures;
	}

	public void setHeures(Hour heures) {
		this.heures = heures;
	}

	public boolean isPubliee() {
		return publiee;
	}

	public void setPubliee(boolean publiee) {
		this.publiee = publiee;
	}
	
	
	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		if(enseignant == this.enseignant) return;
		if(this.enseignant != null && this.enseignant.getDemandes().contains(this)){
			this.enseignant.removeDemande(this);
		}
		if(enseignant != null)
			enseignant.addDemande(this);
		this.enseignant = enseignant;
	}
    
	@Override
    public String toString() {
        return id + " " + heures + " " + publiee + " " + enseignant;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(heures).append(publiee).append(enseignant).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Demande) {
            final Demande other = (Demande) obj;
            return new EqualsBuilder().append(heures, other.heures).append(publiee, other.publiee).append(enseignant, other.enseignant).append(id, other.id).isEquals();
        }
        return false;
    }
	
}
