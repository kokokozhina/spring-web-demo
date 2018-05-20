package com.kokokozhina.controller;

import com.kokokozhina.model.AdminPage;
import com.kokokozhina.service.UserService;
import com.kokokozhina.validation.AdminPageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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
        model.addAttribute("edit", null);
        return "admin";
    }

//    model.addAttribute("adminForm", new AdminPage());
//    @RequestMapping(method = RequestMethod.POST)
//    public String admin(@ModelAttribute("adminForm") AdminPage adminPage, BindingResult bindingResult, Model model) {
//        adminPageValidator.validate(adminPage, bindingResult);
//        if (!bindingResult.hasErrors()) {
//            userService.updateRoleByUsername(adminPage.getUsername(), adminPage.getRole());
//        }
//        model.addAttribute("list", userService.getAll());
//        return "admin";
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("editId") String id, Model model) {

        if (id != null && !id.isEmpty()) {
            model.addAttribute("list", userService.getAll());
            model.addAttribute("edit", id);
            return "admin";
        }
        model.addAttribute("list", userService.getAll());
        model.addAttribute("edit", null);
        return "admin";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String admin(@ModelAttribute("name") String name,
                        @ModelAttribute("role") String role, Model model) {

        if (name != null && !name.isEmpty() &&
                role != null && !role.isEmpty()) {
            userService.updateRoleByUsername(name, role);
        }
        model.addAttribute("list", userService.getAll());
        model.addAttribute("edit", null);
        return "admin";
    }

}
