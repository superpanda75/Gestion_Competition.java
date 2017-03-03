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
	public void testCompetition() {
		fail("Not yet implemented");
		
		
	}

	@Test
	public void testGetNom() {
		inscriptions.Inscriptions inscriptions = getInscriptions();
		Competition compet = inscriptions.createCompetition("Tournoi de fléchettes", null, true);
		assertEquals("Tournoi de fléchettes", compet.getNom());
	}

	@Test
	public void testSetNom() {
		inscriptions.Inscriptions inscriptions = getInscriptions();
		Competition compet = inscriptions.createCompetition("gertrude", null, true);
		compet.setNom("gertrude");
		assertEquals("gertrude", compet.getNom());
	}

	@Test
	public void testInscriptionsOuvertes() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("Thresh", LocalDate.now().plusDays(3), true);
		assertTrue(compet.inscriptionsOuvertes());
	}

	@Test
	public void testGetDateCloture() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("test", LocalDate.now(), false);	
		assertEquals(LocalDate.now(),compet.getDateCloture());
	}

	@Test
	public void testEstEnEquipe() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("Thresh", LocalDate.now().plusDays(20), true);	
		assertEquals(true,compet.estEnEquipe());
		
	}

	@Test
	public void testSetDateCloture() {
		LocalDate date = LocalDate.now().plusDays(10);
		LocalDate  date1 = LocalDate.now().plusDays(20);
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("bip", date, true);
		compet.setDateCloture(date1);
		assertEquals(date1,compet.getDateCloture());
	}
	

	@Test
	public void testGetCandidats() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Competition compet = inscri.createCompetition("testCompet", LocalDate.now().plusDays(20), false);
		Personne Olivier = inscri.createPersonne("ha", "oh", "eh");
		Personne Thresh = inscri.createPersonne("ha", "oh", "eh");
		compet.add(Olivier);
		compet.add(Thresh);
		assertTrue(compet.getCandidats().contains(Olivier));
		assertTrue(compet.getCandidats().contains(Thresh));
		}

	@Test
	public void testAddPersonne() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		LocalDate date = LocalDate.now().plusDays(20);
		Competition compet = inscriptions.createCompetition("test", date, false);
		Personne personne = inscriptions.createPersonne("test", "prenom", "mail");
		int sizeBefore = compet.getCandidats().size();
		compet.add(personne);
		int sizeAfter = compet.getCandidats().size();
		assertTrue(compet.getCandidats().contains(personne));
		assertEquals(sizeBefore+1,sizeAfter);
	}

	@Test
	public void testAddEquipe() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		LocalDate date = LocalDate.now().plusDays(20);
		Competition compet = inscriptions.createCompetition("test", date, true);
		
		Personne p = inscriptions.createPersonne("test", "prenom", "mail");
		Personne pp = inscriptions.createPersonne("test", "prenom", "mail");
		Personne ppp = inscriptions.createPersonne("test", "prenom", "mail");
		Equipe equipe = inscriptions.createEquipe("testTeam");
		Equipe equipe2 = inscriptions.createEquipe("testTeam");

		equipe.add(p);
		equipe.add(pp);
		equipe2.add(ppp);
		
		compet.add(equipe);
		compet.add(equipe2);
		int sizeBefore = inscriptions.getCandidats().size();
		Equipe eee = inscriptions.createEquipe("Thresh");
		Personne Personne = inscriptions.createPersonne("test", "test", "mail");
		eee.add(Personne);
		compet.add(eee);
		assertTrue(inscriptions.getCandidats().contains(eee));
		int sizeAfter = inscriptions.getCandidats().size();
		assertEquals(sizeBefore+1,sizeAfter);

	}

	@Test
	public void testRemove() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		LocalDate date = LocalDate.now().plusDays(20);
		Competition compet = inscri.createCompetition("test", date,false);
		Personne Personne = inscri.createPersonne("nom", "prenom", "mail");
		Personne Personne2 = inscri.createPersonne("nom", "prenom", "mail");
		compet.add(Personne);
		compet.add(Personne2);
		int sizeBefore = compet.getCandidats().size();
		compet.remove(Personne);
		int sizeAfter = compet.getCandidats().size();
		assertEquals(sizeBefore-1,sizeAfter);
	}

	@Test
	public void testDelete() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false);
		int size = inscriptions.getCompetitions().size();
		compet.delete();
		assertEquals(size-1,inscriptions.getCompetitions().size());
	}

	@Test
	public void testCompareTo() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false);
		Competition compet2 = inscriptions.createCompetition("Compet", LocalDate.now().plusDays(20), false);

		assertEquals(0,compet.compareTo(compet2));
	}

	@Test
	public void testToString() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition compet = inscriptions.createCompetition("compet", LocalDate.now().plusDays(20), false);
		assertNotNull(compet.toString());
	}

}
	