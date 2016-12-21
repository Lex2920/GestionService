package fr.gestion.donnees;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="DemandeInterventionExterieure")
public class DemandeInterventionExterieure extends Demande {


	private static final long serialVersionUID = -428378015670563360L;
	
	@Column(name = "organisation")
	private String organisation;
	
	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public DemandeInterventionExterieure() {
	}

	public DemandeInterventionExterieure(Enseignant enseignant) {
		super(enseignant);
	}
	
	@Override
    public String toString() {
        return super.toString() + " " + organisation;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(heures).append(publiee).append(enseignant).append(organisation).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
    	if(super.equals(obj)){
			return new EqualsBuilder().append(organisation, ((DemandeInterventionExterieure)obj).organisation).isEquals();
    	}
    	return false;
    }

}
