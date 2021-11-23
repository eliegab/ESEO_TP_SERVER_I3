package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JDBCConfiguration {

	@Bean
	public static Connection getConnection() {

		String BDD = "maven";
		String url = "jdbc:postgresql://localhost:15432/" + BDD;
		String user = "postgres";
		String password = "postgres";
		Connection connection = null;
		// L'essai de connexion à votre base de donées
		try {
			// création de la connexion
            if(connection == null) {
            	connection = DriverManager.getConnection(url, user, password);
            }
		} catch (SQLException e1) {
			System.out.println("Erreur pendant la creation de la connexion à la BDD." + e1);
			e1.printStackTrace();
		}
		return connection;
	}
}
