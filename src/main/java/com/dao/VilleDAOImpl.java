package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
				ville.setLatitude(resultSet.getString("Latitude"));
				ville.setLongitude(resultSet.getString("Longitude"));

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
				ville.setLatitude(resultSet.getString("Latitude"));
				ville.setLongitude(resultSet.getString("Longitude"));

				listVille.add(ville);
			}

		} catch (SQLException e) {
			// traitement de l'exception
		}
		return listVille;
	}

	@Override
	public int addVille(Ville ville) {
		int response=-1;
		try {	
			Connection conn = JDBCConfiguration.getConnection();
	
			Statement st = conn.createStatement(); 
			
			List<String> fields = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			
			if(ville.getCodeCommune()!=null) {
				fields.add("code_commune_insee");
				values.add(ville.getCodeCommune());
			}
			if(ville.getNomCommune()!=null) {
				fields.add("nom_commune");
				values.add(ville.getNomCommune());
			}
			if(ville.getCodePostal()!=null) {
				fields.add("code_postal");
				values.add(ville.getCodePostal());
			}
			if(ville.getLibelleAcheminement()!=null) {
				fields.add("libelle_acheminement");
				values.add(ville.getLibelleAcheminement());
			}
			if(ville.getLigne()!=null) {
				fields.add("ligne_5");
				values.add(ville.getLigne());
			}
			if(ville.getLatitude()!=null) {
				fields.add("latitude");
				values.add(ville.getLatitude());
			}
			if(ville.getLongitude()!=null) {
				fields.add("longitude");
				values.add(ville.getLongitude());
			}
			
			String fieldsString="";
			String valuesString="";
			for(int i = 0;i<fields.size();i++) {
				if(i==0) {
					fieldsString+=fields.get(i);
					valuesString+="'"+values.get(i)+"'";
				}
				else {
					fieldsString+=", "+fields.get(i);
					valuesString+=", '"+values.get(i)+"'";
				}
			}
			String request = "INSERT INTO ville_france("+fieldsString+") VALUES ("+valuesString+")";
			System.out.println(request);
			response = st.executeUpdate(request);
	
	        conn.close(); 
	    } catch (Exception e) { 
	        System.err.println("Got an exception! "); 
	        System.err.println(e.getMessage()); 
	    } 
		
		return response;

	}

	@Override
	public int updateVille(Ville ville) {
		int response=-1;
		try {	
			Connection conn = JDBCConfiguration.getConnection();
	
			Statement st = conn.createStatement(); 
			
			List<String> fields = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			
			if(ville.getNomCommune()!=null) {
				fields.add("nom_commune");
				values.add(ville.getNomCommune());
			}
			if(ville.getCodePostal()!=null) {
				fields.add("code_postal");
				values.add(ville.getCodePostal());
			}
			if(ville.getLibelleAcheminement()!=null) {
				fields.add("libelle_acheminement");
				values.add(ville.getLibelleAcheminement());
			}
			if(ville.getLigne()!=null) {
				fields.add("ligne_5");
				values.add(ville.getLigne());
			}
			if(ville.getLatitude()!=null) {
				fields.add("latitude");
				values.add(ville.getLatitude());
			}
			if(ville.getLongitude()!=null) {
				fields.add("longitude");
				values.add(ville.getLongitude());
			}
			
			String updatedValue="";
			for(int i = 0;i<fields.size();i++) {
				if(i==0) {
					updatedValue+=(fields.get(i)+"= '"+values.get(i)+"'");
				}
				else {
					updatedValue+=(", "+fields.get(i)+"= '"+values.get(i)+"'");
				}
			}
			String request = "UPDATE ville_france SET "+updatedValue+" WHERE code_commune_insee='"+ville.getCodeCommune()+"'";
			System.out.println(request);
			response = st.executeUpdate(request);
	
	        conn.close(); 
	    } catch (Exception e) { 
	        System.err.println("Got an exception! "); 
	        System.err.println(e.getMessage()); 
	    } 
		
		return response;
	}

	@Override
	public int deleteVille(String codeCommune) {
		int response=-1;
		try {	
			Connection conn = JDBCConfiguration.getConnection();
	
			Statement st = conn.createStatement(); 
			
			String request = "DELETE FROM ville_france WHERE code_commune_insee='"+codeCommune+"'";
			System.out.println(request);
			response = st.executeUpdate(request);
	
	        conn.close(); 
	    } catch (Exception e) { 
	        System.err.println("Got an exception! "); 
	        System.err.println(e.getMessage()); 
	    } 
		
		return response;
	}
}
