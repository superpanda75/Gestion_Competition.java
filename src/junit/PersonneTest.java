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
		
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Personne Personne = inscriptions.createPersonne("a", "b", "c", false);
		Competition compet = inscriptions.createCompetition("d", LocalDate.of(2016,12,31),false, false);
		compet.add(Personne);
		Personne.delete();
		assertTrue(!inscriptions.getCandidats().contains(Personne));
		assertTrue(!compet.getCandidats().contains(Personne));
		
	}



	@Test
	public void testToString() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Personne Personne = inscriptions.createPersonne("a", "b", "c", false);
		assertNotNull(Personne.toString());

				
	}


	@Test
	public void testGetPrenom() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Personne Personne = inscriptions.createPersonne("a", "b", "c", false);
		String inscri = Personne.getPrenom();
		assertEquals("b",inscri);
		}

	@Test
	public void testSetPrenom() {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);
		Personne Personne = inscriptions.createPersonne("a", "b", "c", false);
		Personne.setPrenom("d");
		String inscri = Personne.getPrenom();
		assertEquals("d",inscri);
	}

	@Test
	public void testGetMail() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne = inscri.createPersonne("a", "b", "c", false);
		String m = Personne.getMail();
		assertEquals("c",m);
	}
	@Test
	
	public void testSetMail() {
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne = inscri.createPersonne("a", "b", "c", false);
		Personne.setMail("d");
		String m = Personne.getMail();
		assertEquals("d",m);
	}

	@Test
	public void testGetEquipes() {
		
		Inscriptions inscri = Inscriptions.getInscriptions(false);
		Personne Personne = inscri.createPersonne("a", "b", "c", false);
		Equipe Equipe = inscri.createEquipe("1");
		Equipe Equipe1 = inscri.createEquipe("2");
		Equipe.add(Personne);
		Equipe1.add(Personne);
		assertTrue(Personne.getEquipes().contains(Equipe));
		assertTrue(Personne.getEquipes().contains(Equipe1));
		

	}
		    


	/*@Test
	public void testRemoveEquipe() {
		Inscriptions inscri = Inscriptions.getInscriptions();
		Personne personne = inscri.createPersonne("a", "b", "c");
		Equipe Equipe = inscri.createEquipe("1");
		Equipe.remove(equipe);
		assertFalse("Message", equipes.contains(personne));
		
	}*/

}
