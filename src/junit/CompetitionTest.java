package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import static inscriptions.Inscriptions.*;

public class CompetitionTest {

	

	@Test
	public void testGetNom() {
		inscriptions.Inscriptions inscriptions = getInscriptions(false);
		Competition compet = inscriptions.createCompetition("Tournoi de fléchettes", null, true, false);
		assertEquals("Tournoi de fléchettes", compet.getNom());
	}

	@Test
	public void testSetNom() {
		inscriptions.Inscriptions inscriptions = getInscriptions(false);
		Competition compet = inscriptions.createCompetition("gertrude", null, true, false);
		compet.setNom("gertrude");
		assertEquals("gertrude", compet.getNom());
	}

	@Test
	public void testInscriptionsOuvertes() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("Thresh", LocalDate.now(), true, false);
		assertTrue("Must be true", compet.inscriptionsOuvertes(null));
	}

	@Test
	public void testGetDateCloture() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("test", LocalDate.now(), false, false);	
		assertEquals(LocalDate.now(),compet.getDateCloture());
	}

	@Test
	public void testEstEnEquipe() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("Thresh", LocalDate.now(), true, false);	
		assertEquals(true,compet.getEnEquipe());
		
	}

	@Test
	public void testSetDateCloture() {
		LocalDate date = LocalDate.now();
		LocalDate  date1 = LocalDate.now();
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("bip", date, true, false);
		compet.setDateCloture(date1);
		assertEquals(date1,compet.getDateCloture());
	}
	

	@Test
	public void testGetCandidats() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Competition compet = inscri.createCompetition("testCompet", LocalDate.now(), false, false);
		Personne Olivier = inscri.createPersonne("ha", "oh", "eh", false);
		Personne Thresh = inscri.createPersonne("ha", "oh", "eh", false);
		compet.add(Olivier);
		compet.add(Thresh);
		assertTrue(compet.getCandidats().contains(Olivier));
		assertTrue(compet.getCandidats().contains(Thresh));
		}

	@Test
	public void testAddPersonne() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("test", null, false, false);
		Personne personne = inscriptions.createPersonne("test", "prenom", "mail", false);
		compet.add(personne);
		assertTrue(compet.getCandidats().contains(personne));
	}

	@Test
	public void testAddEquipe() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		LocalDate date = LocalDate.now().plusDays(20);
		Competition compet = inscriptions.createCompetition("test", date, true, false);
		
		Personne p = inscriptions.createPersonne("test", "prenom", "mail", false);
		Personne pp = inscriptions.createPersonne("test", "prenom", "mail", false);
		Personne ppp = inscriptions.createPersonne("test", "prenom", "mail", false);
		Equipe equipe = inscriptions.createEquipe("testTeam");
		Equipe equipe2 = inscriptions.createEquipe("testTeam");

		equipe.add(p);
		equipe.add(pp);
		equipe2.add(ppp);
		
		compet.add(equipe);
		compet.add(equipe2);
		int sizeBefore = inscriptions.getCandidats().size();
		Equipe eee = inscriptions.createEquipe("Thresh");
		Personne Personne = inscriptions.createPersonne("test", "test", "mail", false);
		eee.add(Personne);
		compet.add(eee);
		assertTrue(inscriptions.getCandidats().contains(eee));
		int sizeAfter = inscriptions.getCandidats().size();
		assertEquals(sizeBefore+1,sizeAfter);

	}

	@Test
	public void testRemove() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		LocalDate date = LocalDate.now().plusDays(20);
		Competition compet = inscri.createCompetition("test", date,false, false);
		Personne Personne = inscri.createPersonne("nom", "prenom", "mail", false);
		Personne Personne2 = inscri.createPersonne("nom", "prenom", "mail", false);
		compet.add(Personne);
		compet.add(Personne2);
		int sizeBefore = compet.getCandidats().size();
		compet.remove(Personne);
		int sizeAfter = compet.getCandidats().size();
		assertEquals(sizeBefore-1,sizeAfter);
	}

	@Test
	public void testDelete() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false, false);
		int size = inscriptions.getCompetitions().size();
		compet.delete();
		assertEquals(size-1,inscriptions.getCompetitions().size());
	}

	@Test
	public void testCompareTo() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false, false);
		Competition compet2 = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false, false);

		assertEquals(0,compet.compareTo(compet2));
	}

	@Test
	public void testToString() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Competition compet = inscriptions.createCompetition("compet", LocalDate.now().plusDays(20), false, false);
		assertNotNull(compet.toString());
	}

}
		