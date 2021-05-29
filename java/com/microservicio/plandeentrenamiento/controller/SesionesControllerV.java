package com.microservicio.plandeentrenamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sesiones")
public class SesionesControllerV {
	
	@Autowired
	private RestSesionEntrenamiento sesionRest;
	
	@GetMapping(value = "mostrarByMi/{id_microciclo}")
	public String buscarSesionByPlan(Model model,@PathVariable("id_microciclo") Long id_microciclo) {
		
		model.addAttribute("titulo", "Lista Sesiones de Entrenamiento");
		model.addAttribute("sesiones", sesionRest.buscarSesionByMe(id_microciclo));

		return "/views/sesiones/listarByMi";
	}
}
