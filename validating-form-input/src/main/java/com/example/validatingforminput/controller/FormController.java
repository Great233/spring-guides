package com.example.validatingforminput.controller;

import com.example.validatingforminput.bean.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * @author Great
 */
@Controller
public class FormController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String index(PersonForm personForm) {
        return "index";
    }

    @PostMapping("/")
    public String post(@Valid PersonForm personForm,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "index";
        }
        return "redirect:/results";
    }
}
