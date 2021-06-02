package com.microservicio.plandeentrenamiento.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.plandeentrenamiento.models.entity.Mesociclo;
import com.microservicio.plandeentrenamiento.models.repository.MesocicloRepository;

@RestController
@RequestMapping("/mesociclos")
public class RestMesociclo {

	public RestMesociclo() {
		super();
	}



	@Autowired
	private MesocicloRepository mesocicloRepository;
 
	@GetMapping
	public List<Mesociclo> listarMesociclos() {
		return (List<Mesociclo>) mesocicloRepository.findAll();

	}

	@RequestMapping(value = "{id_mesociclo}")
	public Mesociclo getMesocicloById(@PathVariable("id_mesociclo") Long id_mesociclo) {
		Optional<Mesociclo> optionalMesociclo = mesocicloRepository.findById(id_mesociclo);
		if (optionalMesociclo.isPresent()) {
			return optionalMesociclo.get();
		} else {
			return null;
			
		}
	}
	
	
	@GetMapping(value = "listarByP/{id_planentrenamiento}")
	public List<Mesociclo> listarMesociclosByPlan(@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
		
		List<Mesociclo> mesociclos = (List<Mesociclo>) mesocicloRepository.findAll();
		List<Mesociclo> mesociclos2 = new ArrayList<Mesociclo>();
		for (Mesociclo m : mesociclos) {
			if (m.getPlanEntrenamiento().getId_planentrenamiento() == id_planentrenamiento) {
				mesociclos2.add(m);
			}
		}
		
		return mesociclos2;
	}
	
	

	@PostMapping
	public Mesociclo crearMesociclo(@RequestBody Mesociclo mesociclo) {
		return mesocicloRepository.save(mesociclo);
	}

}
