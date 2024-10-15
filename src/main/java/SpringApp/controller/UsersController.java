package SpringApp.controller;



import SpringApp.model.User;
import SpringApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(User user, Model model) {
        model.addAttribute("user", new User());
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUser(id));
        model.addAttribute("getUsers", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUser(id));
        return "userdata";
    }
}




