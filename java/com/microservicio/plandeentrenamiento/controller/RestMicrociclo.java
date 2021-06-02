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


import com.microservicio.plandeentrenamiento.models.entity.Microciclo;
import com.microservicio.plandeentrenamiento.models.repository.MicrocicloRepository;

@RestController
@RequestMapping("/microciclos")
public class RestMicrociclo {

	@Autowired
	private MicrocicloRepository MicrocicloRepository;

	
	@GetMapping
	public List<Microciclo> listarMicrociclos() {
		return (List<Microciclo>) MicrocicloRepository.findAll();

	}
	
	@RequestMapping(value="{id_microciclo}")
	public Microciclo getMicrocicloById(@PathVariable("id_microciclo") Long id_microciclo) {
    Optional <Microciclo> optionalMicrociclo = MicrocicloRepository.findById(id_microciclo);
	if(optionalMicrociclo.isPresent()) {
	 return optionalMicrociclo.get();	
	}else {
		return null;	
	}
	
}
	
	@GetMapping(value = "listarByMe/{id_mesociclo}")
	public List<Microciclo> listarMicrociclosByMe(@PathVariable("id_mesosciclo") Long id_mesosciclo) {
		
		List<Microciclo> microciclos = (List<Microciclo>) MicrocicloRepository.findAll();
		List<Microciclo> microciclos2 = new ArrayList<Microciclo>();
		for (Microciclo m : microciclos) {
			if (m.getMesociclo().getId_mesociclo() == id_mesosciclo) {
				microciclos2.add(m);
			}
		}
		
		return microciclos2;
	}
	
	@PostMapping
	public Microciclo crearMicrociclo(@RequestBody Microciclo microciclo) {
		return MicrocicloRepository.save(microciclo);
	}
	
	
}
