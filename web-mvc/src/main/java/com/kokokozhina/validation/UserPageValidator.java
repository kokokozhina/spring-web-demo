package com.kokokozhina.validation;

import com.kokokozhina.model.NotificationProperty;
import com.kokokozhina.model.UserPage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserPageValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserPageValidator.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserPage userPage = (UserPage) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gitlabGroup", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slackChannel", "Required");
    }

}
