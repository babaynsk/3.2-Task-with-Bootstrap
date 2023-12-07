package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserServicelmpl;
import ru.kata.spring.boot_security.demo.service.RoleServicelmpl;

import java.util.List;

@Controller
public class UsersController {

    private final MyUserServicelmpl userService;
    private final RoleServicelmpl roleService;


    @Autowired
    public UsersController(MyUserServicelmpl userService, RoleServicelmpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("user", user); // Добавьте эту строку
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model,Authentication authentication) {
        model.addAttribute("authUser",(User)authentication.getPrincipal());
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }


    @PutMapping("/saveUpdatedUser/{id}")
    public String saveUpdatedUser(@ModelAttribute("updatedUser") User updatedUser, @PathVariable("id") int id) {
        // Проверка, что ID в пути соответствует ID в объекте
        if (updatedUser.getId() != id) {
            // Обработка несоответствия, например, перенаправление на страницу ошибки
            return "redirect:/error";
        }

        // Ваш код обновления пользователя
        userService.save(updatedUser);
        return "redirect:/admin";
    }

    @DeleteMapping ("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String showFirstPage(){
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage(Model model,Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("authUser",user);
        return "user";
    }
}