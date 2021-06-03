package com.microservicio.plandeentrenamiento.api;

import com.microservicio.plandeentrenamiento.models.entity.*;
import com.microservicio.plandeentrenamiento.models.repository.SesionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sesiones")
public class RestSesionEntrenamiento {
	
	
	@Autowired
	private SesionRepository sesionRepository;

	//LISTA TODAS LAS SESIONES
	@GetMapping
	public List<SesionEntrenamiento> listarSesionesEntrenamiento() {
		return (List<SesionEntrenamiento>) sesionRepository.findAll();

	}
	
	//RETORNA UNA SESIÓN POR SU ID
	@RequestMapping(value="{id_sesionentrenamiento}")
	public SesionEntrenamiento getSesionEntrenamientoById(@PathVariable("id_sesionentrenamiento") Long id_sesionentrenamiento) {
    Optional <SesionEntrenamiento> optionalSesion = sesionRepository.findById(id_sesionentrenamiento);
	if(optionalSesion.isPresent()) {
	 return optionalSesion.get();	
	}else {
		return null;	
	}

	}
	
	
	//LISTA LAS SESIONES DE UN MICROCICLO POR SU ID
	@GetMapping(value = "listarByMi/{id_microciclo}")
	public List<SesionEntrenamiento> listarSesionesByMe(@PathVariable("id_microciclo") Long id_microciclo) {
		
		List<SesionEntrenamiento> sesiones = (List<SesionEntrenamiento>) sesionRepository.findAll();
		List<SesionEntrenamiento> sesiones2 = new ArrayList<SesionEntrenamiento>();
		for (SesionEntrenamiento s : sesiones) {
			if (s.getMicrociclo().getId_microciclo() == id_microciclo) {
				sesiones2.add(s);
			}
		}
		
		return sesiones2;
	}
	

}