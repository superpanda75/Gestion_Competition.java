package junit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import inscriptions.Competition.InscriptionEnRetardException;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class EquipeTest {

	@Test
	public void testDelete() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);
		
		assertTrue(inscriptions.getPersonnes().contains(Personne));
		
		Personne.delete();
		assertTrue(!Equipe.getMembres().contains(Personne));
		assertTrue(!inscriptions.getPersonnes().contains(Personne));
		assertTrue(!inscriptions.getCandidats().contains(Personne));
		
	}

	@Test
	public void testToString() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);
		assertNotNull(Personne.toString());
	}


	@Test
	public void testGetMembres() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Personne Personne1 = inscriptions.createPersonne("c", "d", "e");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);
		Equipe.add(Personne1);
		int size = Equipe.getMembres().size();
		assertTrue(Equipe.getMembres().contains(Personne));
		assertTrue(Equipe.getMembres().contains(Personne1));
		
	
		assertEquals(size,Equipe.getMembres().size());
		
	}

	@Test
	public void testAddPersonne() throws InscriptionEnRetardException, RuntimeException, IOException {

		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Personne Personne1 = inscriptions.createPersonne("d", "e", "f");
		Equipe Equipe = inscriptions.createEquipe("g");
		Equipe.add(Personne);
		Equipe.add(Personne1);
		assertTrue(Equipe.getMembres().contains(Personne));
		assertTrue(Equipe.getMembres().contains(Personne1));
		
	}

	@Test
	public void testRemovePersonne() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);
		
		
		assertTrue(Equipe.getMembres().contains(Personne));
		Personne.delete();
		assertTrue(!Equipe.getMembres().contains(Personne));
	}

}