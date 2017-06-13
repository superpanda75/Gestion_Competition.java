package junit;

import static org.junit.Assert.*;
import org.junit.Test;
import inscriptions.*;

public class EquipeTest {

	@Test
	public void testDelete() {
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
	public void testToString() {

		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);
		assertNotNull(Personne.toString());
	}


	@Test
	public void testGetMembres() {
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
	public void testAddPersonne() {

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
	public void testRemovePersonne() {

		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Equipe Equipe = inscriptions.createEquipe("d");
		Equipe.add(Personne);


		assertTrue(Equipe.getMembres().contains(Personne));
		Personne.delete();
		assertTrue(!Equipe.getMembres().contains(Personne));
	}

}