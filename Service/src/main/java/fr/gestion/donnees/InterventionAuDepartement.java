package fr.gestion.donnees;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="InterventionAuDepartement")
public class InterventionAuDepartement extends Intervention {

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="voeu")
	private Voeu voeu;
	
	public InterventionAuDepartement( ) {
		
	}
	
	public InterventionAuDepartement(ServiceEnseignant service) {
		super(service);
	}

	public Voeu getVoeu() {
		return voeu;
	}

	public void setVoeu(Voeu voeu) {
		if(voeu == this.voeu) return;
		if(this.voeu != null && this.voeu.getInterventions().contains(this)){
			this.voeu.removeIntervention(this);
		}
		if(voeu != null)
			voeu.addIntervention(this);
		this.voeu = voeu;
	}
	
	@Override
    public String toString() {
        return super.toString() + " " + voeu;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(volume).append(service).append(voeu).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (super.equals(obj)) {
            final InterventionAuDepartement other = (InterventionAuDepartement) obj;
            return new EqualsBuilder().append(voeu, other.voeu).isEquals();
        }
        return false;
    }

}
