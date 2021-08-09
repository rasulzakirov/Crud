

package com.peaksoft.controller;

import com.peaksoft.service.UserService;
import com.peaksoft.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")

public class UserController {
    @Autowired
   private  UserService userService;

    @GetMapping

    public  String getUser(Model model){
        model.addAttribute("users" ,userService.getAllUser());
        return  "users";
    }

    @GetMapping("/add-user")
    public String addUser(User user){
        return "add-user";
    }


    @PostMapping("/save-user")
    public String saveUser(User user, Model model){
        userService.addUser(user);
        model.addAttribute("users", userService.getAllUser());
        return "redirect:/users";
    }


    @RequestMapping(value="/delete-user/{id}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.get(id));
        return "update-user";
    }

    @RequestMapping(value = "/edit-user/{id}",method = RequestMethod.POST)
    public String editUser(@PathVariable("id") Integer id, User user){
        userService.updateUser(user, id);
        return "redirect:/users";
    }

}
