package com.blo;

import java.util.ArrayList;

import org.springframework.util.MultiValueMap;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getInfoVille(MultiValueMap<String,String> params);
	public ArrayList<Ville> getAllVilles();
	
}
