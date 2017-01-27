package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class InscriptionsTest {

	@Test
	public void testGetCompetitions() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Competition compet = inscri.createCompetition("a", LocalDate.now().plusDays(10), true);
		Competition compet1 = inscri.createCompetition("b", LocalDate.now().plusDays(10), true);
		int size = inscri.getCompetitions().size();
		assertTrue(inscri.getCompetitions().contains(compet));
		assertTrue(inscri.getCompetitions().contains(compet1));
		assertEquals(size, inscri.getCompetitions().size());
	}

	@Test
	public void testGetCandidats() {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a", "b", "c");
		Personne Personne1 = inscri.createPersonne("d", "e", "f");
		Personne Personne2 = inscri.createPersonne("g", "h", "i");
		Equipe Equipe = inscri.createEquipe("j");
		Equipe.add(Personne1);
		Equipe.add(Personne2);
		
		int size = inscri.getCandidats().size();
		assertTrue(inscri.getCandidats().contains(Personne));
		assertTrue(inscri.getCandidats().contains(Equipe));
		assertEquals(size,inscri.getCandidats().size());
		
	}

	@Test
	public void testGetPersonnes() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a","b", "c");
		Personne Personne1 = inscri.createPersonne("d", "e", "f");
		int size = inscri.getPersonnes().size();
		assertTrue(inscri.getPersonnes().contains(Personne));
		assertTrue(inscri.getPersonnes().contains(Personne1));
		assertEquals(size, inscri.getPersonnes().size());
			
	}

	@Test
	public void testGetEquipes() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Equipe Equipe = inscri.createEquipe("a");
		Equipe Equipe1 = inscri.createEquipe("b");
		int size = inscri.getEquipes().size();
		assertTrue(inscri.getEquipes().contains(Equipe));
		assertTrue(inscri.getEquipes().contains(Equipe1));
		assertEquals(size,inscri.getEquipes().size());


	}


	@Test
	public void testCreateCompetition() {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		int avant = inscri.getCompetitions().size();
		Competition c = inscri.createCompetition("a", LocalDate.now().plusDays(10), true);
		int apres = inscri.getCompetitions().size();
		assertTrue(inscri.getCompetitions().contains(c));
		assertEquals(avant+1,apres);


	}


	@Test
	public void testCreatePersonne() {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a","b", "c");
		assertTrue(inscri.getCandidats().contains(Personne));
	}


	@Test
	public void testCreateEquipe() {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Equipe Equipe = inscri.createEquipe("a");
		assertTrue(inscri.getCandidats().contains(Equipe));
		
	}

	@Test
	public void testRemoveCompetition() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCandidat() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetInscriptions() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		assertNotNull(inscri);
	}

	@Test
	public void testReinitialiser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecharger() {
		fail("Not yet implemented");
	}

	@Test
	public void testSauvegarder() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		assertNotNull(inscriptions.toString());
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
