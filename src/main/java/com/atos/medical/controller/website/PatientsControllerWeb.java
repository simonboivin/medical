package com.atos.medical.controller.website;

import com.atos.medical.model.services.PatientsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientsControllerWeb {

    private final PatientsService patientsService;

    public PatientsControllerWeb(PatientsService patientsService){
        this.patientsService = patientsService;
    }

    @RequestMapping("/list")
    public String patientsList(Model model) {
        model.addAttribute("patients_list", patientsService.getAllPatients());
        return "patients/list";
    }

}
