package com.tranquilmagpie.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class PlaceholderController {
    @GetMapping("/google")
    public RedirectView redirectToExternalUrl() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://www.google.com");
        return redirectView;
    }
}
