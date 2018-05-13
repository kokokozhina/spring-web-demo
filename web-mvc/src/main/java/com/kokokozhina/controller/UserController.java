package com.kokokozhina.controller;

import bot.Bot;
import com.kokokozhina.model.NotificationProperty;
import com.kokokozhina.model.UserPage;
import com.kokokozhina.service.NotificationPropertyService;
import com.kokokozhina.validation.UserPageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserPageValidator userPageValidator;

    @Autowired
    private NotificationPropertyService notificationPropertyService;

    @Autowired
    private Bot bot;

    public String redirectWithModel(Model model) throws IOException {
        return "user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String user(Model model) throws IOException {
        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", new UserPage());
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("projects", null);
        model.addAttribute("channels", bot.getChannels());
        return "user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String user(@ModelAttribute("settingId") String id,
                       BindingResult bindingResult,
                       Model model) throws IOException {
        if (id != null && !id.isEmpty()) {
            notificationPropertyService.deleteById(Long.parseLong(id));
        }
        model.addAttribute("projects", null);
        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", new UserPage());
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("channels", bot.getChannels());

        return "user";
    }

    @RequestMapping(value = "/chooseProject", method = RequestMethod.POST)
    public String user(@ModelAttribute("userChangesForm") UserPage userPage,
                       BindingResult bindingResult,
                       Model model) throws IOException {
        userPageValidator.validate(userPage, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("projects", bot.getProjectsByGroupName(userPage.getGitlabGroup()));
        }

        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", userPage);
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("channels", bot.getChannels());
        redirectWithModel(model);
        return "user";
    }

    @RequestMapping(value = "/addSetting", method = RequestMethod.POST)
    public String add(@ModelAttribute("userChangesForm") UserPage userPage,
                      BindingResult bindingResult,
                      Model model) throws IOException {
        userPageValidator.validate(userPage, bindingResult);
        if (!bindingResult.hasErrors()) {
            notificationPropertyService.save(new NotificationProperty(
                    userPage.getGitlabGroup(), userPage.getGitlabProject(), userPage.getSlackChannel()));
            return "redirect:/user";
        }
        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", userPage);
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("channels", bot.getChannels());
        model.addAttribute("projects", null);


        return "user";
    }
}