package com.tranquilmagpie.spring.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to OpenAPI api documentation
 */
@Controller
public class HomeController {

    @RequestMapping("/swagger")
    public String index() {
        return "redirect:swagger-ui/index.html";
    }

}
