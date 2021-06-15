package com.microservicio.plandeentrenamiento.api;

import com.microservicio.plandeentrenamiento.models.entity.*;
import com.microservicio.plandeentrenamiento.models.repository.EquipoPlanRepository;
import com.microservicio.plandeentrenamiento.models.repository.PlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planesentrenamiento")
public class RestPlanEntrenamiento {
	
	@Autowired
	private EquipoPlanRepository equipoPlanRep;
	
	@Autowired
	private PlanRepository planRepository;

	//LISTA TODOS LOS PLANES 
	@GetMapping
	public List<PlanEntrenamiento> listarPlanesEntrenamiento() {
		return (List<PlanEntrenamiento>) planRepository.findAll();

	}
	
	
    //RETORNA UN PLAN POR SU ID
	@RequestMapping(value="{id_planentrenamiento}")
	public PlanEntrenamiento getPlanEntrenamientoById(@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
    Optional <PlanEntrenamiento> optionalPlan = planRepository.findById(id_planentrenamiento);
	if(optionalPlan.isPresent()) {
	 return optionalPlan.get();	
	}else {
		return null;	
	}

	}
	
	//LISTA LOS PLANES DE UN EQUIPO POR SU ID
	@GetMapping(value ="listarByEq/{id_equipo}")
	public List<PlanEntrenamiento> listarPlanesByEquipo(@PathVariable("id_equipo") Long id_equipo) {
		
		List<EquipoPlan> equiposplanes = (List<EquipoPlan>) equipoPlanRep.findAll();
		List<PlanEntrenamiento> planesR = new ArrayList<PlanEntrenamiento>();
		for (EquipoPlan e : equiposplanes) {
			if ( e.getEquipo().getId_equipo() == id_equipo) {
				planesR.add(e.getPlanentrenamiento());
			}
		}
		
		return planesR;
	}
	
	//INSERTA UN PLAN
	@PostMapping
	public PlanEntrenamiento crearPlanEntrenamiento(@RequestBody PlanEntrenamiento planEntrenamiento) {
		return planRepository.save(planEntrenamiento);
	}
	
	
	//EDITA UN PLAN DE ENTRENAMIENTO
	@PutMapping
	public PlanEntrenamiento editarPlanEntrenamiento(@RequestBody PlanEntrenamiento planEntrenamiento) {
		if(planRepository.existsById(planEntrenamiento.getId_planentrenamiento())) {
			return planRepository.save(planEntrenamiento);
		}else {
		return null;
	}
	}
	
}


