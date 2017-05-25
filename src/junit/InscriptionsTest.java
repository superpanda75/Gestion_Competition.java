package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Competition.InscriptionEnRetardException;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class InscriptionsTest {

	@Test
	public void testGetCompetitions() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Competition compet = inscri.createCompetition("a", LocalDate.now().plusDays(10), true);
		Competition compet1 = inscri.createCompetition("b", LocalDate.now().plusDays(10), true);
		assertTrue(inscri.getCompetitions().contains(compet));
		assertTrue(inscri.getCompetitions().contains(compet1));
	}

	@Test
	public void testGetCandidats() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a", "b", "c");
		Personne Personne1 = inscri.createPersonne("d", "e", "f");
		Personne Personne2 = inscri.createPersonne("g", "h", "i");
		Equipe Equipe = inscri.createEquipe("j");
		Equipe.add(Personne1);
		Equipe.add(Personne2);
		
		assertTrue(inscri.getCandidats().contains(Personne));
		assertTrue(inscri.getCandidats().contains(Equipe));
		
	}

	@Test
	public void testGetPersonnes() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne1 = inscri.createPersonne("d", "e", "f");
		assertTrue(inscri.getPersonnes().contains(Personne1));
		
			
	}

	@Test
	public void testGetEquipes() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Equipe Equipe = inscri.createEquipe("a");
		Equipe Equipe1 = inscri.createEquipe("b");
		assertTrue(inscri.getEquipes().contains(Equipe));
		assertTrue(inscri.getEquipes().contains(Equipe1));


	}


	@Test
	public void testCreateCompetition() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		int avant = inscri.getCompetitions().size();
		Competition c = inscri.createCompetition("a", LocalDate.now().plusDays(10), true);
		int apres = inscri.getCompetitions().size();
		assertTrue(inscri.getCompetitions().contains(c));
		assertEquals(avant+1,apres);


	}


	@Test
	public void testCreatePersonne() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a","b", "c");
		assertTrue(inscri.getCandidats().contains(Personne));
	}


	@Test
	public void testCreateEquipe() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Equipe Equipe = inscri.createEquipe("a");
		assertTrue(inscri.getCandidats().contains(Equipe));
		
	}


	@Test
	public void testGetInscriptions() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscri = Inscriptions.getInscriptions();
		assertNotNull(inscri);
	}

	@Test
	public void testReinitialiser() throws InscriptionEnRetardException, RuntimeException, IOException
	{
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		assertNotNull("Une inscription a été réinitialisée", inscriptions);
	}
	@Test
	public void testRecharger() throws InscriptionEnRetardException, RuntimeException, IOException
	{
		Inscriptions inscriptions = null;
		inscriptions = Inscriptions.getInscriptions();
		assertNotNull("Une inscription a été rechargée", inscriptions);
	}


	@Test
	public void testToString() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		assertNotNull(inscriptions.toString());
	}



}
