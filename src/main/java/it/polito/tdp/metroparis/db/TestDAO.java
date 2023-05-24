package it.polito.tdp.metroparis.db;

import java.sql.Connection;

public class TestDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = DBConnect.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			MetroDAO dao = new MetroDAO() ;
			
			System.out.println("fermata "+dao.readFermate()) ;
			System.out.println("linee: "+dao.readLinee()) ;
			//System.out.println(dao.trovaCollegamento(57, ));

		} catch (Exception e) {
			throw new RuntimeException("Test FAILED", e);
		}
	}

}
