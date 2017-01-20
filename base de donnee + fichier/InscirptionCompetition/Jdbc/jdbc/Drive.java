package jdbc;

import java.sql.*;
public class Drive 
{
	public static void main (String[] args)
	{
		try{
			//CONNECTION TO DATABASE
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppe", "root" , "");
			//CREATON DU STATEMENT
			Statement myStmt = myConn.createStatement();
			//EXECUTER SQL QUERY
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM personne");
				while (myRs.next())
				{
						System.out.println(myRs.getString("nom") + "," + myRs.getString("prenom") + ", " +myRs.getString("email"));
				}
			}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
}