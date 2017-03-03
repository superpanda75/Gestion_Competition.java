package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class PersonneTest {

	@Test
	public void testDelete() {
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Competition compet = inscriptions.createCompetition("d", LocalDate.of(2016,12,31),false);
		compet.add(Personne);
		Personne.delete();
		assertTrue(!inscriptions.getCandidats().contains(Personne));
		assertTrue(!compet.getCandidats().contains(Personne));
		
	}



	@Test
	public void testToString() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		assertNotNull(Personne.toString());

				
	}

	@Test
	public void testPersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrenom() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		String inscri = Personne.getPrenom();
		assertEquals("d",inscri);
		}

	@Test
	public void testSetPrenom() {
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Personne Personne = inscriptions.createPersonne("a", "b", "c");
		Personne.setPrenom("d");
		String inscri = Personne.getPrenom();
		assertEquals("e",inscri);
	}

	@Test
	public void testGetMail() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a", "b", "c");
		String m = Personne.getMail();
		assertEquals("d",m);
	}

	@Test
	public void testSetMail() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("a", "b", "c");
		Personne.setMail("d");
		String m = Personne.getMail();
		assertEquals("d",inscri);
	}

	@Test
	public void testGetEquipes() {
		
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne Personne = inscri.createPersonne("test", "testeur", "azerty");
		Equipe Equipe = inscri.createEquipe("test");
		Equipe Equipe1 = inscri.createEquipe("test2");
		Equipe.add(Personne);
		Equipe1.add(Personne);
		int size = inscri.getEquipes().size();
		assertTrue(Personne.getEquipes().contains(Equipe));
		assertTrue(Personne.getEquipes().contains(Equipe1));
		assertEquals(size,inscri.getEquipes().size());
		

	}
	@Test
	public void testAddEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEquipe() {
		fail("Not yet implemented");
	}

}
