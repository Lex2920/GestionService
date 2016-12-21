package fr.gestion.departement.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gestion.departement.EnseignantRepository;
import fr.gestion.departement.SpringConfiguration;
import fr.gestion.donnees.Enseignant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository repo;

    @Before
    public void init() {
    	repo.deleteAll();
    }
    
    @After
    public void end() {
        repo.deleteAll();
    }

    @Test
    public void createRepository() {
        Enseignant ens = repo.save(new Enseignant("Molli", "Pascal"));
        Assert.assertNotNull(ens.getId());
    }

    @Test
    public void findEnseignantByName() {
    	Enseignant ens = repo.save(new Enseignant("Molli", "Pascal"));
        Assert.assertEquals(ens, repo.findByNom("Molli").iterator().next());
    }

    @Test
    public void searchEnseignantNatively() {
    	Enseignant ens = repo.save(new Enseignant("Molli", "Pascal"));
        Assert.assertEquals(ens, repo.searchNatively("%oll%").iterator().next());
    }

    @Test
    public void searchEnseignant() {
    	Enseignant ens = repo.save(new Enseignant("Molli", "Pascal"));
        Assert.assertEquals(ens, repo.search("%oll%").iterator().next());
    }

}