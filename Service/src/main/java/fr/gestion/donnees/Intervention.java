package fr.gestion.donnees;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="Intervention")
public class Intervention implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7013593152081433604L;

	 public Intervention() {
		
	}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(name="volume")
	protected String volume;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="service")
	protected ServiceEnseignant service;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Intervention(ServiceEnseignant service){
		this.service = service;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public ServiceEnseignant getService() {
		return service;
	}

	public void setService(ServiceEnseignant service) {
		if(service == this.service) return;
		if(this.service != null && this.service.getInterventions().contains(this)){
			this.service.removeIntervention(this);
		}
		if(service != null)
			service.addIntervention(this);
		this.service = service;
	}
	
	@Override
    public String toString() {
        return id + " " + volume + " " + service;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(volume).append(service).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Intervention) {
            final Intervention other = (Intervention) obj;
            return new EqualsBuilder().append(id, other.id).append(volume, other.volume).append(service, other.service).isEquals();
        }
        return false;
    }
	
}
