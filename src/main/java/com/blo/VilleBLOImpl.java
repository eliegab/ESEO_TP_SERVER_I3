package com.blo;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;
	
	public ArrayList<Ville> getInfoVille(MultiValueMap<String,String> params) {
		ArrayList<Ville> listeVille;
		
		listeVille = villeDAO.findVille(params);
		
		return listeVille;
	}
	
	public ArrayList<Ville> getAllVilles(){
ArrayList<Ville> listeVille;
		
		listeVille = villeDAO.findAllVilles();
		
		return listeVille;
	}

	@Override
	public int addVille(Ville ville) {
		return villeDAO.addVille(ville);
	}

	@Override
	public int updateVille(Ville ville) {
		return villeDAO.updateVille(ville);
	}

	@Override
	public int deleteVille(String codeCommune) {
		String code="";
		System.out.println(codeCommune);
		try {
			JSONObject jsonThing= new JSONObject(codeCommune);
			code=(String) jsonThing.get("codeCommune");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return villeDAO.deleteVille(code);
	}

}
