package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserServicelmpl;
import ru.kata.spring.boot_security.demo.service.RoleServicelmpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRESTController {
    private final MyUserServicelmpl userService;
    private final RoleServicelmpl roleService;


    @Autowired
    public UsersRESTController(MyUserServicelmpl userService, RoleServicelmpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/allUsers")
    public List<User> showAllUsers(){
        return userService.findAll();
    }




}
