package fr.gestion.departement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.Enseignant;

import java.util.Set;;

@Repository
public interface EnseignantRepository extends CrudRepository <Enseignant, Long> {
    Set<Enseignant> findByNom(String nom);

    @Query(value = "SELECT * FROM Enseignant p WHERE p.nom like :pattern", nativeQuery = true)
    Set<Enseignant> searchNatively(@Param("pattern") String pattern);

    @Query(value = "FROM Enseignant p WHERE p.nom like :pattern")
    Set<Enseignant> search(@Param("pattern") String pattern);

    @Query(value = "FROM Enseignant p WHERE p.nom like :nom AND p.prenom like :prenom")
    Set<Enseignant> find(@Param("nom") String nom, @Param("prenom") String prenom);
    
    @Query(value = "FROM Enseignant p")
    Set<Enseignant> allEnseignant();
    
    @Query(value = "FROM Enseignant p, Demande d WHERE p.nom like :nom AND p.prenom like :prenom and d.enseignant = p.id")
    Set<Demande> allDemandesOf(@Param("nom") String nom, @Param("prenom") String prenom);
}