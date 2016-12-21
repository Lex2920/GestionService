package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "Parcours")
public class Parcours implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8617820462271698225L;
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="depId")
	private Departement departement;
	@OneToMany(targetEntity = Module.class, cascade=CascadeType.ALL, mappedBy = "parcours")
	public List<Module> modules;
	
	public Parcours() {
		this.modules = new ArrayList<Module>();
	}
	
	public Parcours(Departement departement, Module module){
		this.departement = departement;
		this.modules = new ArrayList<Module>();
		this.modules.add(module);
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		if(departement == this.departement) return;
		if(this.departement != null && this.departement.getEnseignants().contains(this)){
			this.departement.removeParcours(this);
		}
		if(departement != null)
			departement.addParcours(this);
		this.departement = departement;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module module){
		if(!this.modules.contains(module)){
			this.modules.add(module);
			module.setParcours(this);
		}
	}
	
	public void removeModule(Module module){
		if(this.modules.contains(module)){
			this.modules.remove(module);
			module.setParcours(null);
		}
	}
	
	@Override
    public String toString() {
        return id + "";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Parcours) {
            final Parcours other = (Parcours) obj;
            return new EqualsBuilder().append(id, other.id).isEquals();
        }
        return false;
    }
}
