package com.hexaware.cars.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.hexaware.cars.dao.ICrimeAnalysisDaoImpl;
import com.hexaware.cars.entity.Incident;
import com.hexaware.cars.entity.Victim;
import com.hexaware.cars.dao.ICrimeAnalysisDaoImpl;

class CrimeAnalysisDaoImplTest {
	 private static Connection connection;
	    private static ICrimeAnalysisDaoImpl dao;

	    @BeforeAll
	    static void setupDatabase() throws SQLException {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "Poors@30");
	        dao = new ICrimeAnalysisDaoImpl(connection);
	    }

	@Test
	void testInsertVictim() {
		Victim victim = new Victim();
        victim.setFirstName("David");
        victim.setLastName("Doe");
        victim.setDateOfBirth(Date.valueOf("1990-02-15"));
        victim.setGender("Male");
        victim.setContactInformation("david.doe@example.com");

        assertDoesNotThrow(() -> dao.insertVictim(victim));
    }
	@Test
    void testInsertIncident() {
        Incident incident = new Incident(
                0, // Incident ID auto-generated
                "Theft",
                Date.valueOf("2024-10-10"),
                "Downtown",
                "Reported phone theft near bus stop",
                "Open",
                1, // Make sure this VictimID exists
                1, // Make sure this SuspectID exists
                1  // Make sure this OfficerID exists
        );

        assertDoesNotThrow(() -> dao.insertIncident(incident));
    }

	  @AfterAll
	    static void tearDown() throws SQLException {
	        connection.close();
	    }

}