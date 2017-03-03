package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import jdbc.Drive;
public class DriveTest {

	@Test
	public void testConnexion() {
		fail("Vous êtes connecté",Drive.Connexion());
	}

	private void fail(String string, String connexion) {
		// TODO Auto-generated method stub
		
	}

	

}
