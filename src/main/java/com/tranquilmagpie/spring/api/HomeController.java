package com.tranquilmagpie.spring.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to OpenAPI api documentation
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    // Further redirects to "http://localhost:8080/swagger-ui/index.html"
    public String index() {
        return "redirect:swagger-ui.html";
    }

}
