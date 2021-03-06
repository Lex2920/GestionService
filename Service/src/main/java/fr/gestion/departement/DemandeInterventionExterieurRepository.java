package fr.gestion.departement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.gestion.donnees.DemandeInterventionExterieure;
import fr.gestion.donnees.Enseignant;

import java.util.Set;;

@Repository
public interface DemandeInterventionExterieurRepository extends CrudRepository <DemandeInterventionExterieure, Long> {
    /*Set<Enseignant> findByNom(String nom);

    @Query(value = "SELECT * FROM Enseignant p WHERE p.nom like :pattern", nativeQuery = true)
    Set<Enseignant> searchNatively(@Param("pattern") String pattern);

    @Query(value = "FROM Enseignant p WHERE p.nom like :pattern")
    Set<Enseignant> search(@Param("pattern") String pattern);*/

}
