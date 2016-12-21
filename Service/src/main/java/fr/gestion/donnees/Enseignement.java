package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import fr.gestion.datatypes.Hour;
import fr.gestion.datatypes.TypeEnseignement;

@Entity
public class Enseignement implements Serializable {

	private static final long serialVersionUID = -2122117901111734426L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Embedded
	private Hour volume;
	@Enumerated(EnumType.STRING)
	private TypeEnseignement type;

	@OneToMany(targetEntity = Voeu.class, cascade=CascadeType.ALL, mappedBy = "enseignement")
	private List<Voeu> voeux;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="modId")
	private Module module;
	
	public Enseignement(){
		this.voeux = new ArrayList<Voeu>();
	}
	
	public Enseignement(Hour volume, TypeEnseignement type){
		this.volume = volume;
		this.type = type;
		
		this.voeux = new ArrayList<Voeu>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hour getVolume() {
		return volume;
	}

	public void setVolume(Hour volume) {
		this.volume = volume;
	}

	public TypeEnseignement getType() {
		return type;
	}

	public void setType(TypeEnseignement type) {
		this.type = type;
	}

	public List<Voeu> getVoeux() {
		return voeux;
	}

	public void setVoeux(List<Voeu> voeux) {
		this.voeux = voeux;
	}
	
	public void addVoeu(Voeu voeu){
		if(!this.voeux.contains(voeu)){
			this.voeux.add(voeu);
			voeu.setEnseignement(this);
		}
	}
	
	public void removeVoeu(Voeu voeu){
		if(this.voeux.contains(voeu)){
			this.voeux.remove(voeu);
			voeu.setEnseignement(null);
		}
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		if(module == this.module) return;
		if(this.module != null && this.module.getEnseignements().contains(this)){
			this.module.removeEnseignement(this);
		}
		if(module != null)
			module.addEnseignement(this);
		this.module = module;
	}

	@Override
    public String toString() {
        return id + " " + volume + " " + type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(volume).append(type).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Enseignement) {
            final Enseignement other = (Enseignement) obj;
            return new EqualsBuilder().append(volume, other.volume).append(type, other.type).append(id, other.id).isEquals();
        }
        return false;
    }
}
