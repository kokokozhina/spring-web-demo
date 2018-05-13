package com.kokokozhina.controller;

import bot.Bot;
import com.kokokozhina.model.NotificationProperty;
import com.kokokozhina.model.UserPage;
import com.kokokozhina.service.NotificationPropertyService;
import com.kokokozhina.service.SecurityService;
import com.kokokozhina.service.UserService;
import com.kokokozhina.validation.AdminPageValidator;
import com.kokokozhina.validation.NotificationPropertyValidator;
import com.kokokozhina.validation.UserValidator;
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
    private NotificationPropertyValidator notificationPropertyValidator;

    @Autowired
    private NotificationPropertyService notificationPropertyService;

    @Autowired
    private Bot bot;

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
                       @ModelAttribute("gitlabGroup") String gitlabGroup,
                       @ModelAttribute("gitlabProject") String gitlabProject,
                       @ModelAttribute("slackChannel") String slackChannel,
                       @ModelAttribute("action") String action,
                       BindingResult bindingResult,
                       Model model) throws IOException {
        switch (action) {
            case "deleteSetting":
                if (id != null && !id.isEmpty()) {
                    notificationPropertyService.deleteById(Long.parseLong(id));
                }
                model.addAttribute("projects", null);
                break;
            case "chooseProject":
                if (gitlabGroup == null && gitlabGroup.isEmpty()) {
                    bindingResult.rejectValue("gitlabGroup", "This field is required.");
                }

                if (slackChannel == null && slackChannel.isEmpty()) {
                    bindingResult.rejectValue("slackChannel", "This field is required.");
                }
                if (!bindingResult.hasErrors()) {
                    model.addAttribute("projects", bot.getProjectsByGroupName(gitlabGroup));
                } else {
                    model.addAttribute("error", bindingResult.getAllErrors());
                }
                break;
        }


        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", new UserPage());
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("channels", bot.getChannels());

        return "user";
    }

    @RequestMapping(value = "/addSetting", method = RequestMethod.POST)
    public String add(@ModelAttribute("gitlabGroup") String gitlabGroup,
                       @ModelAttribute("gitlabProject") String gitlabProject,
                       @ModelAttribute("slackChannel") String slackChannel,
                       BindingResult bindingResult,
                       Model model) throws IOException {
        UserPage userPage = new UserPage(
                gitlabGroup, gitlabProject, slackChannel);

        notificationPropertyValidator.validate(userPage, bindingResult);
        if (!bindingResult.hasErrors()) {
            notificationPropertyService.save(
                    new NotificationProperty(gitlabGroup, gitlabProject, slackChannel));
        }

        model.addAttribute("list", notificationPropertyService.getAll());
        model.addAttribute("userChangesForm", new UserPage());
        model.addAttribute("groups", bot.getGroups());
        model.addAttribute("channels", bot.getChannels());
        model.addAttribute("projects", null);

        return "user";
    }
}
