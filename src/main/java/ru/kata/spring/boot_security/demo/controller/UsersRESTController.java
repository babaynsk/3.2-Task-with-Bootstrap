package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserServicelmpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRESTController {
    private final MyUserServicelmpl userService;

    @Autowired
    public UsersRESTController(MyUserServicelmpl userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> showAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/addNewUser")
    public void addNewUser(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/showUser/{id}")
    public User showUser(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
    }

}
