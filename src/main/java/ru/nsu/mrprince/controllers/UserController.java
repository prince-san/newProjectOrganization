package ru.nsu.mrprince.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.mrprince.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    //@Autowired
    //private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        //model.addAttribute("userForm", new User());

        return "registration";
    }

}
