package homeWork.SpringSecurity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import homeWork.SpringSecurity.model.Person;
import homeWork.SpringSecurity.model.Role;
import homeWork.SpringSecurity.repositories.RoleRepository;
import homeWork.SpringSecurity.security.PersonDetails;
import homeWork.SpringSecurity.service.PersonService;
import homeWork.SpringSecurity.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class PersonController {
//    private final PersonValidator personValidator;
//    private final RoleRepository roleRepository;
//    private final PersonService personService;
//    @Autowired
//    public PersonController(PersonValidator personValidator, RoleRepository roleRepository, PersonService personService) {
//        this.personValidator = personValidator;
//        this.roleRepository = roleRepository;
//        this.personService = personService;
//    }
//
//    @GetMapping()
//    public String printAllUsers(ModelMap model) {
//        List<Person> people = personService.getAllPersons();
//        model.addAttribute("users", people);
//        return "users";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String printUserById(@PathVariable("id") int id, ModelMap model) {
//        Person person = personService.getPersonById(id);
//        model.addAttribute("person", person);
//        return "show";
//    }
//
//    @GetMapping(value = "/new")
//    public String createUser(ModelMap model) {
//        model.addAttribute("person", new Person());
//        return "newUser";
//    }
//
//    @PostMapping()
//    public String addUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "newUser";
//        }
//        person.getRoles().add(roleRepository.findByRole("USER").get());
//        personService.addNewPerson(person);
//        return "redirect:/admin";
//    }
//
//    @GetMapping(value = "/{id}/edit")
//    public String editUser(ModelMap model, @PathVariable("id") int id) {
//        model.addAttribute("person", personService.getPersonById(id));
//        return "editUser";
//    }
//
//    @PatchMapping(value = "/{id}")
//    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
//        personService.changePersonById(id, person);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public String deleteUser(@PathVariable("id") int id) {
//        personService.deletePersonById(id);
//        return "redirect:/admin";
//    }
//}
@Controller

public class PersonController {
    private final PersonValidator personValidator;
    private final RoleRepository roleRepository;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonValidator personValidator, RoleRepository roleRepository, PersonService personService) {
        this.personValidator = personValidator;
        this.roleRepository = roleRepository;
        this.personService = personService;
    }

    @GetMapping(value = "/admin")
    public String mainPage(ModelMap model) {
        model.addAttribute("users", personService.getAllPersons());
        return "mainPage";
    }

    @GetMapping(value = "/user")
    public String userPage(ModelMap model) {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role user_role = new Role("USER");
        for (Role role : personDetails.getPerson().getRoles()) {
            if (role.getRole().equals("ADMIN")){
                user_role = role;
            }
        }
        model.addAttribute("role", user_role);
        return "userPage";
    }

    @PatchMapping(value = "/admin/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id, @RequestParam("user_role") String role) {
        person.getRoles().add(roleRepository.findByRole(role).get());
        personService.changePersonById(id, person);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin")
    public String addUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @RequestParam("user_role") String role) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        person.getRoles().add(roleRepository.findByRole(role).get());
        personService.addNewPerson(person);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        personService.deletePersonById(id);
        return "redirect:/admin";
    }
}