package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> listVille = new ArrayList<Ville>();

		Connection con = JDBCConfiguration.getConnection();

		ResultSet resultSet = null;
		String requete = "SELECT * FROM ville_france";

		try {
			Statement stmt = con.createStatement();
			resultSet = stmt.executeQuery(requete);

			
			
			while (resultSet.next()) {
				Ville ville = new Ville();

				ville.setCodeCommune(resultSet.getString("Code_commune_INSEE"));
				ville.setNomCommune(resultSet.getString("Nom_commune"));
				ville.setCodePostal(resultSet.getString("Code_postal"));
				ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
				ville.setLigne(resultSet.getString("Ligne_5"));

				listVille.add(ville);
			}

		} catch (SQLException e) {
			// traitement de l'exception
		}
		return listVille;
	}
	
	private Map<String,String> prepareParameters(MultiValueMap<String, String> queryParameters) {

		   Map<String,String> parameters = new HashMap<String,String>();

		   for(String str : queryParameters.keySet()){
		     parameters.put(str, queryParameters.getFirst(str));
		   }
		   return parameters;

	}
	
	@Override
	public ArrayList<Ville> findVille(MultiValueMap<String,String> params) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();

		Connection con = JDBCConfiguration.getConnection();

		ResultSet resultSet = null;
		String requete = "SELECT * FROM ville_france WHERE ";
		int i=0;
		for (Map.Entry<String, String> entry : prepareParameters(params).entrySet()) {
		    if(i==0) {
		    	
		    	requete += (entry.getKey() + " = '" + entry.getValue()+"'");
		    }
		    else {
		    	requete += " AND " + (entry.getKey() + " = '" + entry.getValue()+"'");
		    }
		    i++;
		}
		System.out.println(requete);
		try {
			Statement stmt = con.createStatement();
			resultSet = stmt.executeQuery(requete);

			
			
			while (resultSet.next()) {
				Ville ville = new Ville();

				ville.setCodeCommune(resultSet.getString("Code_commune_INSEE"));
				ville.setNomCommune(resultSet.getString("Nom_commune"));
				ville.setCodePostal(resultSet.getString("Code_postal"));
				ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
				ville.setLigne(resultSet.getString("Ligne_5"));

				listVille.add(ville);
			}

		} catch (SQLException e) {
			// traitement de l'exception
		}
		return listVille;
	}
}
