package controllers;

import models.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.ApplicationRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<ApplicationRequest> applications = applicationRepository.findAll();
        model.addAttribute("applications", applications);
        return "index";
    }

    @GetMapping(value = "/add_app")
    public String addApplicationsPage() {
        return "add_app";
    }

    @PostMapping(value = "/add_app")
    public String addApplication(ApplicationRequest application) {
        application.setHandled(false);
        applicationRepository.save(application);
        return "redirect:/";
    }

    @GetMapping(value = "/new_app")
    public String newApplicationPage(Model model) {
        List<ApplicationRequest> applications = applicationRepository.findAllByHandledIsFalse();
        model.addAttribute("applications", applications);
        return "new_app";
    }

    @GetMapping(value = "/handled_app")
    public String handledAppPage(Model model) {
        List<ApplicationRequest> applications = applicationRepository.findAllByHandledIsTrue();
        model.addAttribute("applications", applications);
        return "handled_app";
    }

    @GetMapping(value = "/detais/{id}")
    public String details(@PathVariable(name = "id") Long id, Model model) {
        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);
        model.addAttribute("application", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updateApplication(ApplicationRequest application) {
        application.setHandled(true);
        applicationRepository.save(application);
        return "redirect:/details/"+application.getId();
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        applicationRepository.deleteById(id);
        return "redirect:/";
    }
}
