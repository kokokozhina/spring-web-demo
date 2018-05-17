package com.kokokozhina.validation;

import com.kokokozhina.model.AdminPage;
import com.kokokozhina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminPageValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AdminPage.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        AdminPage adminPage = (AdminPage) o;
        if (userService.findByUsername(adminPage.getUsername()) == null) {
            errors.rejectValue("username", "Incorrect.adminForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "Required");
    }
}
