package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import inscriptions.Competition;
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
		fail("Not yet implemented");
	}

	@Test
	public void testInscriptionsOuvertes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateCloture() {
		fail("Not yet implemented");
	}

	@Test
	public void testEstEnEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDateCloture() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCandidats() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
