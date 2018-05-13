package com.kokokozhina.controller;

import bot.Bot;
import com.kokokozhina.model.AdminPage;
import com.kokokozhina.model.User;
import com.kokokozhina.model.UserPage;
import com.kokokozhina.service.NotificationPropertyService;
import com.kokokozhina.service.SecurityService;
import com.kokokozhina.service.UserService;
import com.kokokozhina.validation.AdminPageValidator;
import com.kokokozhina.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminPageValidator adminPageValidator;


    @RequestMapping(method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("list", userService.getAll());
        model.addAttribute("adminForm", new AdminPage());
        return "admin";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String admin(@ModelAttribute("adminForm") AdminPage adminPage, BindingResult bindingResult, Model model) {
        adminPageValidator.validate(adminPage, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.updateRoleByUsername(adminPage.getUsername(), adminPage.getRole());
        }
        model.addAttribute("list", userService.getAll());
        return "admin";
    }


}
