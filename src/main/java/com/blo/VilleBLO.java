package com.blo;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.util.MultiValueMap;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getInfoVille(MultiValueMap<String,String> params);
	public ArrayList<Ville> getAllVilles();
	public int addVille(Ville ville);
	public int updateVille(Ville ville);
	public int deleteVille(String codeCommune);
	
}
