package com.kokokozhina.controller;

import com.kokokozhina.model.User;
import com.kokokozhina.service.SecurityService;
import com.kokokozhina.service.UserService;
import com.kokokozhina.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginForm", new User());

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") User loginForm, Model model) {
        try {
            securityService.autoLogin(loginForm.getUsername(), loginForm.getPassword());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals(loginForm.getUsername())) {
                return "redirect:/welcome";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Username or password is incorrect.");
            return "login";
        }

        return "redirect:/welcome";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }


    @RequestMapping(value = "/logoutform", method = RequestMethod.GET)
    public String logout() {
        return "logoutform";
    }

    @RequestMapping(value = "/logoutform", method = RequestMethod.POST)
    public String logout(Model model) {
        SecurityContextHolder.clearContext();
        return "redirect:/welcome";
    }

    @ExceptionHandler(Throwable.class)
    public String handleException(Throwable t) {
        return "redirect:/error";
    }

}
