package fr.gestion.departement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.gestion.donnees.Enseignant;
import fr.gestion.donnees.Voeu;

import java.util.Set;;

@Repository
public interface VoeuRepository extends CrudRepository <Voeu, Long> {
    /*Set<Enseignant> findByNom(String nom);

    @Query(value = "SELECT * FROM Enseignant p WHERE p.nom like :pattern", nativeQuery = true)
    Set<Enseignant> searchNatively(@Param("pattern") String pattern);

    @Query(value = "FROM Enseignant p WHERE p.nom like :pattern")
    Set<Enseignant> search(@Param("pattern") String pattern);*/

	@Query(value = "FROM Voeu ")
	Set<Voeu> getVoeux();

}
