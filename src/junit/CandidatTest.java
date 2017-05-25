package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Competition.InscriptionEnRetardException;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class CandidatTest {
	
	@Test
	public void testGetNom() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b","c");
		String inscri = Personne.getPrenom();
		assertEquals("b",inscri);
		
	}

	@Test
	public void testSetNom() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Personne.setNom("a");
		String inscri = Personne.getNom();
		assertEquals("a",inscri);
	}

	@Test
	public void testGetCompetitions() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Competition Compet = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false);
		Competition Compet1 = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false);
		Compet.add(Personne);
		Compet1.add(Personne);
		assertTrue(Personne.getCompetitions().contains(Compet));
		assertTrue(Personne.getCompetitions().contains(Compet1));
		
		
	}


	@Test
	public void testDelete() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("Yeah", "Ya", "Yo");
		Personne.delete();
		assertTrue(!inscriptions.getCandidats().contains(Personne));
	}
	

	@Test
	public void testCompareTo() throws InscriptionEnRetardException, RuntimeException, IOException {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("Ha", "Ho", "He");
		Personne Personne1 = inscriptions.createPersonne("Ha", "Ho", "He");
		
		assertEquals(0,Personne.compareTo(Personne1));		
	}

	@Test
	public void testToString() throws InscriptionEnRetardException, RuntimeException, IOException {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("Thresh", "Olivier", "Yo");
		
		assertNotNull(Personne.toString());
	}
}

