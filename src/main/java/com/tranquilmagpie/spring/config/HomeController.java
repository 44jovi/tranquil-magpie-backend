package com.tranquilmagpie.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

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