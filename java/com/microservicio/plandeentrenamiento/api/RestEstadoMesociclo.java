package com.microservicio.plandeentrenamiento.api;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.plandeentrenamiento.models.entity.EstadoMesociclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMesocicloRepository;

@RestController
@RequestMapping("/estadosMesociclo")
public class RestEstadoMesociclo {

	@Autowired
	private EstadoMesocicloRepository estadoMesocicloRepository;

	//LISTA TODOS LOS MESOCICLOS EXISTENTES
	@GetMapping
	public List<EstadoMesociclo> listarEstadosMesociclo() {
		return (List<EstadoMesociclo>) estadoMesocicloRepository.findAll();

	}
	
	// RETORNA UN MESOCICLO POR SU ID
	@RequestMapping(value="{id_estadoMesociclo}")
	public EstadoMesociclo getPlanEntrenamientoById(@PathVariable("id_estadoMesociclo") Long id_estadoMesociclo) {
    Optional <EstadoMesociclo> optionalEstadoMesociclo = estadoMesocicloRepository.findById(id_estadoMesociclo);
	if(optionalEstadoMesociclo.isPresent()) {
	 return optionalEstadoMesociclo.get();	
	}else {
		return null;	
	}
	
}
}
