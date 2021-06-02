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
import com.microservicio.plandeentrenamiento.models.entity.EstadoMicrociclo;
import com.microservicio.plandeentrenamiento.models.entity.Microciclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMicrocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.MesocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.MicrocicloRepository;




@Controller
@RequestMapping("/microciclos")
public class MicrocicloControllerV {

	@Autowired
	private RestMicrociclo restMicrociclo;
	@Autowired
	private EstadoMicrocicloRepository estMicrorep;
	@Autowired
	private MesocicloRepository mesoRep;
	@Autowired
	private MicrocicloRepository microRep;

	@GetMapping(value = "mostrarByMe/{id_mesociclo}")
	public String mostrarMicrociclosByPlan(Model model,@PathVariable("id_mesociclo") Long id_mesociclo) {
		
		model.addAttribute("titulo", "Lista microciclos");
		model.addAttribute("microciclos", restMicrociclo.listarMicrociclosByMe(id_mesociclo));

		return "/views/microciclos/listarByMe";
	}
	
	
	@GetMapping("/create/{id_mesociclo}")
	public String crear(Model model,@PathVariable("id_mesociclo") Long id_mesociclo) {
        
		Microciclo microciclo = new Microciclo();
		Long id_meso=id_mesociclo;
		List<EstadoMicrociclo> listEstadosMicro = (List<EstadoMicrociclo>) estMicrorep.findAll(); 
		
		
		model.addAttribute("titulo", "Formulario: Nuevo Microciclo");
		model.addAttribute("microciclo", microciclo);
		model.addAttribute("estados", listEstadosMicro);
		model.addAttribute("id_meso", id_meso);
		

		return "/views/microciclos/frmCrearMicro";
	}
	
	
	@PostMapping("/save/{id_mesociclo}")
	public String guardar(@Validated @ModelAttribute Microciclo microciclo, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_mesociclo") Long id_mesociclo) {
		
		microciclo.setMesociclo(mesoRep.findById(id_mesociclo).get());
		
		microRep.save(microciclo);
		System.out.println("Microciclo guardado con exito!");
		attribute.addFlashAttribute("success", "Microciclo guardado con exito!");
		return "redirect:/microciclos/mostrarByMe/"+microciclo.getMesociclo().getId_mesociclo().toString();
	}
}


