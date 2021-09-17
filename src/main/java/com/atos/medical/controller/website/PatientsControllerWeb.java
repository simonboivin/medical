package com.atos.medical.controller.website;

import com.atos.medical.model.services.PatientsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientsControllerWeb {

    private final PatientsService patientsService;

    public PatientsControllerWeb(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @RequestMapping("/list")
    public String patientsList(Model model) {
        model.addAttribute("patients_list", patientsService.getAllPatients());
        return "patients/list";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteGet(Model model, @PathVariable int id) {
        model.addAttribute("title", "Suppresion d'un patient");
        model.addAttribute("message", "Voulez-vous supprimer le patient n°" + id + " ?");
        model.addAttribute("href_retour", "/patients/list");
        return "common/confirm";
    }

    @PostMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable int id){
        patientsService.deletePatientById(id);
        return "redirect:/patients/list";
    }

    }
