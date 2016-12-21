package fr.gestion.departement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.gestion.donnees.Demande;
import fr.gestion.donnees.DemandeSpeciale;
import fr.gestion.donnees.Enseignant;

import java.util.List;
import java.util.Set;;

@Repository
public interface DemandeRepository extends CrudRepository <Demande, Long> {
    /*Set<Enseignant> findByNom(String nom);

    @Query(value = "SELECT * FROM Enseignant p WHERE p.nom like :pattern", nativeQuery = true)
    Set<Enseignant> searchNatively(@Param("pattern") String pattern);*/

    @Query(value = "FROM Demande d")
    List<Demande> allDemandes();

}
