package com.microservicio.plandeentrenamiento.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.microservicio.plandeentrenamiento.models.entity.EquipoPlan;
import com.microservicio.plandeentrenamiento.models.entity.PlanEntrenamiento;
import com.microservicio.plandeentrenamiento.models.repository.EquipoPlanRepository;
import com.microservicio.plandeentrenamiento.models.repository.EquipoRepository;
import com.microservicio.plandeentrenamiento.models.repository.PlanRepository;

@Controller
@RequestMapping("/planesentrenamiento")
public class PlanEntControllerV {
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private EquipoRepository equipoRep;
	@Autowired
	private RestPlanEntrenamiento restPlan;
	@Autowired
	private EquipoPlanRepository eqPlanRep;
	

	@GetMapping(value="mostrar")
	public String mostrarTodos(Model model) {
	
		List<EquipoPlan> equipoplanes = (List<EquipoPlan>) eqPlanRep.findAll();
		model.addAttribute("titulo", "Lista de Planes de entrenamiento");
		model.addAttribute("planes", equipoplanes);

		return "/views/planes/listartodos";
	}
	
	@GetMapping("/create/{id_equipo}")
	public String crearPlan(Model model,@PathVariable("id_equipo") Long id_equipo) {

		PlanEntrenamiento plan = new PlanEntrenamiento();
		Long idEquipo=id_equipo;

		model.addAttribute("titulo", "Formulario: Nuevo Plan de Entrenamiento");
		model.addAttribute("plan", plan);
		model.addAttribute("idEquipo", idEquipo);
		

		return "/views/planes/frmCrearPlan";
	}
	

	@PostMapping("/save/{id_equipo}")
	public String guardar(@Validated @ModelAttribute PlanEntrenamiento plan, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_equipo") Long id_equipo) {
		
        EquipoPlan eqplan= new EquipoPlan(equipoRep.findById(id_equipo).get(),planRepository.findById(plan.getId_planentrenamiento()).get());
		eqPlanRep.save(eqplan);
		
		System.out.println("Plan de Entrenamiento guardado con exito!");
		attribute.addFlashAttribute("success", "Plan de Entrenamiento guardado con exito!");
		return "redirect:/planesentrenamiento/mostrarByEq/"+id_equipo;
	}
	
	
	@GetMapping(value = "mostrarByEq/{id_equipo}")
	public String mostrarMesociclosByEq(Model model,@PathVariable("id_equipo") Long id_equipo) {
		
		model.addAttribute("titulo", "Lista Planes de Entrenamiento "+equipoRep.findById(id_equipo).get().getNombre());
		model.addAttribute("planes",restPlan.listarPlanesByEquipo(id_equipo));

		return "/views/planes/listarByEq";
	}

	
}
