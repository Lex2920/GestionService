package fr.gestion.donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="Enseignant")
public class Enseignant implements Serializable {

	private static final long serialVersionUID = -1383387464390610213L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name="nom")
    private String nom;
	@Column(name="prenom")
    private String prenom;
	@Column(name="status")
    private String status;
	@OneToMany(fetch=FetchType.EAGER,targetEntity = Demande.class, cascade=CascadeType.ALL, mappedBy = "enseignant")
	private List<Demande> demandes;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="depId")
	private Departement departement;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ServiceEnseignant", joinColumns = @JoinColumn(name = "Service_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "enseignant_id", referencedColumnName = "id"))
	private List<ServiceEnseignant> services;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contratId")	
	private Contrat contrat;

    public Enseignant(){
    	this.demandes = new ArrayList<Demande>();
    }
    
    public Enseignant(final String nom, final String prenom) {
    	this();
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Enseignant(final Contrat contrat, final String nom, final String prenom) {
    	this(nom, prenom);
        this.contrat = contrat;
    }

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}
	
	public void addDemande(Demande demande){
		if(!this.demandes.contains(demande)){
			this.demandes.add(demande);
			demande.setEnseignant(this);
		}
	}
	
	public void removeDemande(Demande demande){
		if(this.demandes.contains(demande)){
			this.demandes.remove(demande);
			demande.setEnseignant(null);
		}
	}

    public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		if(departement == this.departement) return;
		if(this.departement != null && this.departement.getEnseignants().contains(this)){
			this.departement.removeEnseignant(this);
		}
		if(departement != null)
			departement.addEnseignant(this);
		this.departement = departement;
	}

	public List<ServiceEnseignant> getServices() {
		return services;
	}

	public void setServices(List<ServiceEnseignant> services) {
		this.services = services;
	}
	
	public void addService(ServiceEnseignant service){
		if(!this.services.contains(service)){
			this.services.add(service);
			service.addEnseignant(this);
		}
	}
	
	public void removeService(ServiceEnseignant service){
		if(this.services.contains(services)){
			this.services.remove(services);
			service.removeEnseignant(this);
		}
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		if(contrat == this.contrat) return;
		if(this.contrat != null && this.contrat.getEnseignants().contains(this)){
			this.contrat.removeEnseignant(this);
		}
		if(contrat != null)
			contrat.addEnseignant(this);
		this.contrat = contrat;
	}

	@Override
    public String toString() {
        return id + " " + nom + " " + prenom + " " + status;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(nom).append(prenom).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Enseignant) {
            final Enseignant other = (Enseignant) obj;
            return new EqualsBuilder().append(prenom, other.prenom).append(nom, other.nom).append(id, other.id).isEquals();
        }
        return false;
    }

}
