package com.klientai.controller;

import com.klientai.model.Klientai;
import com.klientai.model.User;
import com.klientai.validator.UserValidator;
import com.klientai.services.KlientaiService;
import com.klientai.services.SecurityService;
import com.klientai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@EnableAutoConfiguration
@Controller
public class KlientaiController {
    @Autowired
    @Qualifier("KlientaiService")
    public KlientaiService klientaiService;
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping({"/", "/pridejimas"})
    public String welcome(Model model) {
        model.addAttribute("klientai",new Klientai());
        return "pridejimas";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pridėti")
    public String client(@Valid @ModelAttribute("klientai") Klientai klientai, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "pridejimas";
        } else {
            String name = klientai.getName();
            String surname = klientai.getSurname();
            String commentar = klientai.getCommentar();
            String priority= klientai.getPriority();
            switch (priority) {
                case "Sos":
                    break;
                case "Labai svarbu":
                    break;
                case "Svarbu":
                    break;
                case "Neskubu":
                    break;
                case "Gali palaukti":
                    break;
                default:
                    priority="Nedelsiant";
            }
            modelMap.put("name", name);
            modelMap.put("surname", surname);
            modelMap.put("priority", priority);
            modelMap.put("commentar", commentar);
            klientaiService.save(new Klientai(name, surname, priority, commentar));
        }
        return "prideti";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/klientai")
    public String getAllClients(Model model) {
        model.addAttribute("klientai", klientaiService.getAll());
        return "klientai";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rodyti{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("klientas", klientaiService.getById(id));
        return "klientas";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trinti{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        klientaiService.delete(id);
        model.addAttribute("klientai", klientaiService.getAll());
        return "klientai";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/atnaujinti{id}")
    public String update(@RequestParam("id") int id, Klientai klientai, Model model) {
        model.addAttribute("klientas", klientaiService.getById(id));
        return "atnaujinti";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/atnaujintiklienta")
    public String UpdateClient(@Valid @ModelAttribute("klientai") Klientai klientai) {
            klientaiService.update(klientai);
            return "redirect:/rodyti?id=" + klientai.getId();
        }
    @RequestMapping(method = RequestMethod.GET, value = "/ieskoti{name}")
    public String ieskoti (@RequestParam(value = "name") String name,  Model model) {
        model.addAttribute("klientai", klientaiService.search(name));
        return "paieska";
    }

    @GetMapping("/registruoti")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registruoti";
    }

    @PostMapping("/registruoti")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registruoti";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/pridejimas";
    }

    @GetMapping("/prisijungti")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Įvestas prisijungimo vardas arba slaptazodis neteisingi.");

        if (logout != null)
            model.addAttribute("message", "Sekmingai atsijungete");

        return "prisijungti";
    }
    @GetMapping("/pastas")
    public String change(Model model) {
        model.addAttribute("userForm",new User());

        return "pastas";
    }
    @PostMapping("/pastas")
    public String change(@ModelAttribute("userForm")User userForm,BindingResult bindingResult){
        try {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "pastas";
            }
            userService.save(userForm);
            securityService.autologin(userForm.getEmail(), userForm.getEmailConfirm());
        }catch (NullPointerException e) {
        }
        return "redirect:/pridejimas";
    }
    @GetMapping("/slaptazodis")
    public String change2(Model model) {
        model.addAttribute("userForm",new User());

        return "slaptazodis";
    }
    @PostMapping("/slaptazodis")
    public String change2(@ModelAttribute("userForm")User userForm,BindingResult bindingResult) {
        try {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "slaptazodis";
            }
            userService.save(userForm);
            securityService.autologin(userForm.getPassword(), userForm.getPasswordConfirm());
        } catch (NullPointerException e) {
        }
        return "redirect:/pridejimas";
    }


    }











