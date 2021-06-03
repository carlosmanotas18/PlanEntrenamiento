package com.microservicio.plandeentrenamiento.api;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.plandeentrenamiento.models.entity.TipoMesociclo;
import com.microservicio.plandeentrenamiento.models.repository.TipoMesocicloRepository;

@RestController
@RequestMapping("/tiposMesociclo")
public class RestTipoMesociclo {

	@Autowired
	private TipoMesocicloRepository TipoMesocicloRepository;

	//LISTA TODOS LOS TIPO DE MESOCICLOS
	@GetMapping
	public List<TipoMesociclo> listarTiposMesociclo() {
		return (List<TipoMesociclo>) TipoMesocicloRepository.findAll();

	}
	
	//RETORNA UN TIPO DE MESOCICLO POR SU ID
	@RequestMapping(value="{id_tipoMesociclo}")
	public TipoMesociclo getTipoMesocicloById(@PathVariable("id_tipoMesociclo") Long id_tipoMesociclo) {
    Optional <TipoMesociclo> optionalTipoMesociclo = TipoMesocicloRepository.findById(id_tipoMesociclo);
	if(optionalTipoMesociclo.isPresent()) {
	 return optionalTipoMesociclo.get();	
	}else {
		return null;	
	}
	}
	
	//INSERTA UN TIPO DE MESOCICLO
	@PostMapping
	public TipoMesociclo crearTipoMesociclo(@RequestBody TipoMesociclo tipoMesociclo) {
		return TipoMesocicloRepository.save(tipoMesociclo);
	}
	
}
