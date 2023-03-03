package com.serfinanzas.prestamos.web;

import com.serfinanzas.prestamos.rest.domain.LendInput;
import com.serfinanzas.prestamos.service.LendService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LendCommandWebController extends CommandWebController {

    private final LendService lendService;

    public LendCommandWebController(LendService lendService) {
        this.lendService = lendService;
    }

    @PostMapping(value = "/lend/new")
    @PreAuthorize("isAuthenticated()")
    public String createLend(LendInput input, RedirectAttributes redirectAttributes, Principal principal) {

        input.setUsername(principal.getName());

        lendService.createLend(input);

        redirectAttributes.addFlashAttribute("message", "Prestamo creado con éxito.");

        return "redirect:/loans";
    }
}
