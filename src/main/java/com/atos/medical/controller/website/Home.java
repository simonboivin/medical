package com.atos.medical.controller.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class Home {

    @RequestMapping()
    public String home() {
        return "redirect:/patients/list";
    }

}
