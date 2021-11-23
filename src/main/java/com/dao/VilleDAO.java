package com.dao;

import java.util.ArrayList;

import org.springframework.util.MultiValueMap;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	public ArrayList<Ville> findVille(MultiValueMap<String,String> monParam);

}
