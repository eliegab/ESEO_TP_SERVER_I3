package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;


@RestController
public class VilleControler {
	
	@Autowired
	VilleBLO villeBLOService;

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGet(@RequestParam MultiValueMap<String, String> params) {
		System.out.println("Appel GET");
		// affichage console
		ArrayList<Ville> listeVille;
		if(params.toString()!="{}") {
			listeVille = villeBLOService.getInfoVille(params);
		}
		else {
			listeVille = villeBLOService.getAllVilles();
		}

		return listeVille;
		
	}
	
	// Methode POST
		@RequestMapping(value = "/ville", method = RequestMethod.POST, consumes = "application/json")
		@ResponseBody
		public int appelPost(@RequestBody Ville ville) {
			System.out.println("Appel POST");
			int response = villeBLOService.addVille(ville);
			return response;
			
		}
}
