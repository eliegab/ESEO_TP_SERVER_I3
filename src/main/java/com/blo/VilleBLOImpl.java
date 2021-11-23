package com.blo;

import java.util.ArrayList;

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

}
