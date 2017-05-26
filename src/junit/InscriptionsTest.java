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
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Competition compet = inscri.createCompetition("a", LocalDate.now().plusDays(10), true, false);
		Competition compet1 = inscri.createCompetition("b", LocalDate.now().plusDays(10), true, false);
		assertTrue(inscri.getCompetitions().contains(compet));
		assertTrue(inscri.getCompetitions().contains(compet1));
	}

	@Test
	public void testGetCandidats() {
		
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne = inscri.createPersonne("a", "b", "c", false);
		Personne Personne1 = inscri.createPersonne("d", "e", "f", false);
		Personne Personne2 = inscri.createPersonne("g", "h", "i", false);
		Equipe Equipe = inscri.createEquipe("j");
		Equipe.add(Personne1);
		Equipe.add(Personne2);
		
		assertTrue(inscri.getCandidats().contains(Personne));
		assertTrue(inscri.getCandidats().contains(Equipe));
		
	}

	@Test
	public void testGetPersonnes() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne1 = inscri.createPersonne("d", "e", "f", false);
		assertTrue(inscri.getPersonnes().contains(Personne1));
		
			
	}

	@Test
	public void testGetEquipes() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Equipe Equipe = inscri.createEquipe("a");
		Equipe Equipe1 = inscri.createEquipe("b");
		assertTrue(inscri.getEquipes().contains(Equipe));
		assertTrue(inscri.getEquipes().contains(Equipe1));


	}


	@Test
	public void testCreateCompetition() {
		
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		int avant = inscri.getCompetitions().size();
		Competition c = inscri.createCompetition("a", LocalDate.now().plusDays(10), true, false);
		int apres = inscri.getCompetitions().size();
		assertTrue(inscri.getCompetitions().contains(c));
		assertEquals(avant+1,apres);


	}


	@Test
	public void testCreatePersonne() {
		
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne = inscri.createPersonne("a","b", "c", false);
		assertTrue(inscri.getCandidats().contains(Personne));
	}


	@Test
	public void testCreateEquipe() {
		
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Equipe Equipe = inscri.createEquipe("a");
		assertTrue(inscri.getCandidats().contains(Equipe));
		
	}


	@Test
	public void testGetInscriptions() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		assertNotNull(inscri);
	}

	@Test
	public void testReinitialiser()
	{
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		assertNotNull("Une inscription a été réinitialisée", inscriptions);
	}
	@Test
	public void testRecharger()
	{
		Inscriptions inscriptions = null;
		inscriptions = Inscriptions.getInscriptions(false);
		assertNotNull("Une inscription a été rechargée", inscriptions);
	}


	@Test
	public void testToString() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		assertNotNull(inscriptions.toString());
	}



}
