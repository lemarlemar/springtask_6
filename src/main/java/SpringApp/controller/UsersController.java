package SpringApp.controller;


import SpringApp.model.User;
import SpringApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "add";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User updatedUser, String name,Model model) {
        usersService.getUserById(id);
        updatedUser.setName(name);
        usersService.editUser(id, updatedUser);
        model.addAttribute("user", usersService.getUserById(id));
        return "redirect:/users";
    }

    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("userinfo/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "userinfo";
    }
}




