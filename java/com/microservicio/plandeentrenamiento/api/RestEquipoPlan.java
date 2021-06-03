package com.microservicio.plandeentrenamiento.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservicio.plandeentrenamiento.models.entity.EquipoPlan;
import com.microservicio.plandeentrenamiento.models.repository.EquipoPlanRepository;

@RestController
@RequestMapping("/equiposPlan")
public class RestEquipoPlan {

	@Autowired
	private EquipoPlanRepository eqPlanRepository;

	
	//LISTAR LAS RELACIONES EQUIPO-PLAN MEDIANTE LA INTERFAZ REPOSITORY
	@GetMapping
	public List<EquipoPlan> listarEquiposPlan() {
		return (List<EquipoPlan>) eqPlanRepository.findAll();
	}

}
