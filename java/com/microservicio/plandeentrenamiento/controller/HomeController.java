package com.microservicio.plandeentrenamiento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.microservicio.plandeentrenamiento.models.entity.Equipo;
import com.microservicio.plandeentrenamiento.models.repository.EquipoRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.microservicio.plandeentrenamiento.api.RestPlanEntrenamiento;
import com.microservicio.plandeentrenamiento.models.entity.EquipoPlan;
import com.microservicio.plandeentrenamiento.models.entity.PlanEntrenamiento;
import com.microservicio.plandeentrenamiento.models.repository.EquipoPlanRepository;
import com.microservicio.plandeentrenamiento.models.repository.PlanRepository;
import com.microservicio.plandeentrenamiento.api.RestMesociclo;
import com.microservicio.plandeentrenamiento.models.entity.EstadoMesociclo;
import com.microservicio.plandeentrenamiento.models.entity.Mesociclo;
import com.microservicio.plandeentrenamiento.models.entity.TipoMesociclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMesocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.MesocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.TipoMesocicloRepository;
import com.microservicio.plandeentrenamiento.api.RestMicrociclo;
import com.microservicio.plandeentrenamiento.models.entity.EstadoMicrociclo;
import com.microservicio.plandeentrenamiento.models.entity.Microciclo;
import com.microservicio.plandeentrenamiento.models.repository.EstadoMicrocicloRepository;
import com.microservicio.plandeentrenamiento.models.repository.MicrocicloRepository;
import com.microservicio.plandeentrenamiento.api.RestSesionEntrenamiento;

@Controller
@RequestMapping("/")
public class HomeController {

	//DECLARACIÓN DE DEPENDENCIA
	@Autowired
	private EquipoRepository equipoRep;
	@Autowired
	private PlanRepository planRep;
	@Autowired
	private RestPlanEntrenamiento restPlan;
	@Autowired
	private EquipoPlanRepository eqPlanRep;
	@Autowired
	private RestMesociclo restMesociclo;
	@Autowired
	private TipoMesocicloRepository tipoMesoRep;
	@Autowired
	private EstadoMesocicloRepository estadoMesoRep;
	@Autowired
	private RestMicrociclo restMicrociclo;
	@Autowired
	private EstadoMicrocicloRepository estMicrorep;
	@Autowired
	private MesocicloRepository mesoRep;
	@Autowired
	private MicrocicloRepository microRep;
	@Autowired
	private RestSesionEntrenamiento sesionRest;
	
	//-------------MAPEO LOCALHOST----------
	
	//MAPEO URL INICIAL PARA LISTAR LOS EQUIPOS(RESERVADO PARA INDEX)
	@GetMapping
	public String verEquipos(Model model) {
	
		List<Equipo> listadoEquipos = (List<Equipo>) equipoRep.findAll();
		model.addAttribute("titulo", "Lista de Equipos");
		model.addAttribute("equipos", listadoEquipos);


		return "/views/equipos/listar";
	}
	
	//------------VISTAS EQUIPOS-----------
	
	//LISTAR TODOS LOS EQUIPOS
	@GetMapping(value="/equipos/mostrar")
	public String mostrarEquipos(Model model) {
		List<Equipo> listadoEquipos = (List<Equipo>) equipoRep.findAll();
		model.addAttribute("titulo", "Lista de Equipos");
		model.addAttribute("equipos", listadoEquipos);


		return "/views/equipos/listar";
	}
	
	//--------------VISTAS PLANES----------------
	
	//MOSTRAR TODOS LOS PLANES EXISTENTES
	@GetMapping(value="/planesentrenamiento/mostrar")
	public String mostrarEquiposPlanes(Model model) {
	
		List<EquipoPlan> equipoplanes = (List<EquipoPlan>) eqPlanRep.findAll();
		model.addAttribute("titulo", "Lista de Planes de entrenamiento");
		model.addAttribute("planes", equipoplanes);

		return "/views/planes/listartodos";
	}
	
	//MAPEO DE URL PARA CREAR UN PLAN DIRECCIONANDO AL FORMULARIO
	@GetMapping("/planesentrenamiento/create/{id_equipo}")
	public String crear(Model model,@PathVariable("id_equipo") Long id_equipo) {

		PlanEntrenamiento plan = new PlanEntrenamiento();
		Long idEquipo=id_equipo;

		model.addAttribute("titulo", "Formulario: Nuevo Plan de Entrenamiento");
		model.addAttribute("plan", plan);
		model.addAttribute("idEquipo", idEquipo);
		

		return "/views/planes/frmCrearPlan";
	}
	
    //MAPEO DE URL MEDIANTE POST PARA INSERTAR EL PLAN
	@PostMapping("/planesentrenamiento/save/{id_equipo}")
	public String guardar(@Validated @ModelAttribute PlanEntrenamiento plan, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_equipo") Long id_equipo) {
		//se guarda el plan con los datos obtenidos del formulario
		planRep.save(plan);
		//Se crea la entidad de la clase intermedia de asociación entre equipo y plan
        EquipoPlan eqplan= new EquipoPlan(equipoRep.findById(id_equipo).get(),planRep.findById(plan.getId_planentrenamiento()).get());
		eqPlanRep.save(eqplan);
		
		System.out.println("Plan de Entrenamiento guardado con exito!");
		attribute.addFlashAttribute("success", "Plan de Entrenamiento guardado con exito!");
		//se retorna la url para mostrar los planes agregados al equipo correspondiente
		return "redirect:/planesentrenamiento/mostrarByEq/"+id_equipo;
	}
	
	//MOSTRAR LOS PLANES DE ENTRENAMIENTO DE UN EQUIPO POR SU ID
	@GetMapping(value = "/planesentrenamiento/mostrarByEq/{id_equipo}")
	public String mostrarMesocicloByPlan(Model model,@PathVariable("id_equipo") Long id_equipo) {
		
		model.addAttribute("titulo", "Lista Planes de Entrenamiento "+equipoRep.findById(id_equipo).get().getNombre());
		model.addAttribute("planes",restPlan.listarPlanesByEquipo(id_equipo));

		return "/views/planes/listarByEq";
	}
	
	
	//------------VISTAS DE MESOCICLOS-------------
	
	//MOSTRAR LOS MESOCICLOS DE UN PLAN POR SU ID
	@GetMapping(value = "mesociclos/mostrarByP/{id_planentrenamiento}")
	public String mostrarMesociclosByPlan(Model model,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
		
		model.addAttribute("titulo", "Lista mesociclos");
		//Se utliza el método de la apiRest para filtrar los mesociclos
		model.addAttribute("mesociclos", restMesociclo.listarMesociclosByPlan(id_planentrenamiento));

		return "/views/mesociclos/listarByP";
	}
	
	//URL QUE REDIRECCIONA AL FORMULARIO DE CREAR UN MESOCICLO
	@GetMapping("/mesociclos/create/{id_planentrenamiento}")
	public String crearMesociclo(Model model,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
        
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

	//URL DONDE SE RECIBE EL MESOCICLO DEL FORMULARIO Y SE GUARDA
	@PostMapping("/mesociclos/save/{id_planentrenamiento}")
	public String guardar(@Validated @ModelAttribute Mesociclo mesociclo, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_planentrenamiento") Long id_planentrenamiento) {
		
		mesociclo.setPlanEntrenamiento(planRep.findById(id_planentrenamiento).get());
		mesoRep.save(mesociclo);
		
		System.out.println("Mesociclo guardado con exito!");
		attribute.addFlashAttribute("success", "Mesociclo guardado con exito!");
		return "redirect:/mesociclos/mostrarByP/"+mesociclo.getPlanEntrenamiento().getId_planentrenamiento().toString();
	}
	
	
	//------------VISTAS MICROCICLOS-----------------
	
	//MOSTRAR LOS MICROCICLOS DE UN MESOCICLO POR SU ID
	@GetMapping(value = "microciclos/mostrarByMe/{id_mesociclo}")
	public String mostrarMicrociclosByPlan(Model model,@PathVariable("id_mesociclo") Long id_mesociclo) {
		
		model.addAttribute("titulo", "Lista microciclos");
		model.addAttribute("microciclos", restMicrociclo.listarMicrociclosByMe(id_mesociclo));

		return "/views/microciclos/listarByMe";
	}
	
	//URL QUE REDIRECCIONA AL FORMULARIO DE CREAR UN MICROCICLO
	@GetMapping("/microciclos/create/{id_mesociclo}")
	public String crearMicrociclo(Model model,@PathVariable("id_mesociclo") Long id_mesociclo) {
        
		Microciclo microciclo = new Microciclo();
		Long id_meso=id_mesociclo;
		List<EstadoMicrociclo> listEstadosMicro = (List<EstadoMicrociclo>) estMicrorep.findAll(); 
		
		model.addAttribute("titulo", "Formulario: Nuevo Microciclo");
		model.addAttribute("microciclo", microciclo);
		model.addAttribute("estados", listEstadosMicro);
		model.addAttribute("id_meso", id_meso);
		

		return "/views/microciclos/frmCrearMicro";
	}
	
	//URL DONDE SE RECIBE EL MICROCICLO DEL FORMULARIO Y SE GUARDA
	@PostMapping("/microciclos/save/{id_mesociclo}")
	public String guardar(@Validated @ModelAttribute Microciclo microciclo, BindingResult result,
			Model model, RedirectAttributes attribute,@PathVariable("id_mesociclo") Long id_mesociclo) {
		
		microciclo.setMesociclo(mesoRep.findById(id_mesociclo).get());
		
		microRep.save(microciclo);
		System.out.println("Microciclo guardado con exito!");
		attribute.addFlashAttribute("success", "Microciclo guardado con exito!");
		return "redirect:/microciclos/mostrarByMe/"+microciclo.getMesociclo().getId_mesociclo().toString();
	}
	
	
	//------------VISTAS SESIONES-----------------
	
	//MOSTRAR LAS SESIONES DE UN MICROCICLO POR SU ID
	@GetMapping(value = "sesiones/mostrarByMi/{id_microciclo}")
	public String mostrarSesionesByPlan(Model model,@PathVariable("id_microciclo") Long id_microciclo) {
		
		model.addAttribute("titulo", "Lista Sesiones de Entrenamiento");
		model.addAttribute("sesiones", sesionRest.listarSesionesByMe(id_microciclo));

		return "/views/sesiones/listarByMi";
	}
}
