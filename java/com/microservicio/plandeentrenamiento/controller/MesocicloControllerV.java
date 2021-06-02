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

import com.microservicio.plandeentrenamiento.models.entity.EstadoMesociclo;
import com.microservicio.plandeentrenamiento.models.entity.Mesociclo;
import com.microservicio.plandeentrenamiento.models.entity.TipoMesociclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMesocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.MesocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.PlanRepository;
import com.microservicio.plandeentrenamiento.models.repository.TipoMesocicloRepository;



@Controller
@RequestMapping("/mesociclos")
public class MesocicloControllerV {

	@Autowired
	private RestMesociclo restMesociclo;
	@Autowired
	private PlanRepository planRep;
	@Autowired
	private TipoMesocicloRepository tipoMesoRep;
	@Autowired
	private EstadoMesocicloRepository estadoMesoRep;
	@Autowired
	private MesocicloRepository mesoRep;

	@GetMapping(value = "mostrarByP/{id_planentrenamiento}")
	public String mostrarMesociclosByPlan(Model model,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
		
		model.addAttribute("titulo", "Lista mesociclos");
		model.addAttribute("mesociclos", restMesociclo.listarMesociclosByPlan(id_planentrenamiento));

		return "/views/mesociclos/listarByP";
	}
	
	
	@GetMapping("/create/{id_planentrenamiento}")
	public String crear(Model model,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
        
		Mesociclo mesociclo = new Mesociclo();
		
		Long id_plan=id_planentrenamiento;
		List<EstadoMesociclo> listEstadosMeso = (List<EstadoMesociclo>) estadoMesoRep.findAll(); 
		List<TipoMesociclo> listTiposMeso =(List<TipoMesociclo>) tipoMesoRep.findAll();
		
		model.addAttribute("titulo", "Formulario: Nuevo Mesociclo");
		model.addAttribute("mesociclo", mesociclo);
		model.addAttribute("estados", listEstadosMeso);
		model.addAttribute("tipos", listTiposMeso);
		model.addAttribute("id_plan", id_plan);
		

		return "/views/mesociclos/frmCrearMeso";
	}

	
	@PostMapping("/save/{id_planentrenamiento}")
	public String guardar(@Validated @ModelAttribute Mesociclo mesociclo, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
		mesociclo.setPlanEntrenamiento(planRep.findById(id_planentrenamiento).get());
		
		mesoRep.save(mesociclo);
		System.out.println("Mesociclo guardado con exito!");
		attribute.addFlashAttribute("success", "Mesociclo guardado con exito!");
		return "redirect:/mesociclos/mostrarByP/"+mesociclo.getPlanEntrenamiento().getId_planentrenamiento().toString();
	}
}
