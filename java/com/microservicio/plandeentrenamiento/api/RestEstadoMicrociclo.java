package com.microservicio.plandeentrenamiento.api;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.plandeentrenamiento.models.entity.EstadoMicrociclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMicrocicloRepository;

@RestController
@RequestMapping("/estadosMicrociclo")
public class RestEstadoMicrociclo {


	@Autowired
	private EstadoMicrocicloRepository estadoMicrocicloRepository;

	// LISTA TODOS LOS ESTADOS DE MICROCICLO
	@GetMapping
	public List<EstadoMicrociclo> listarEstadosMicrociclo() {
		return (List<EstadoMicrociclo>) estadoMicrocicloRepository.findAll();

	}
	
	//RETORNA UN ESTADO DE MICROCICLO POR SU ID
	@RequestMapping(value="{id_estadoMicrociclo}")
	public EstadoMicrociclo getEstadoMicrocicloById(@PathVariable("id_estadoMicrociclo") Long id_estadoMicrociclo) {
    Optional <EstadoMicrociclo> optionalEstadoMicrociclo = estadoMicrocicloRepository.findById(id_estadoMicrociclo);
	if(optionalEstadoMicrociclo.isPresent()) {
	 return optionalEstadoMicrociclo.get();	
	}else {
		return null;	
	}
}
	
}
