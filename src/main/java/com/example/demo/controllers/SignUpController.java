package com.example.demo.controllers;

import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class SignUpController {

    final UserService userService;

    @GetMapping(value = "/signup")
    public ModelAndView getSignUpPage(ModelAndView model) {
        model.setViewName("signUp");
        model.addObject("userSignUpRequest", new UserSignUpRequest());
        return model;
    }

    @PostMapping(value = "/signup")
    public ModelAndView saveUser(@Valid @ModelAttribute UserSignUpRequest userSignUpRequest,
                                 BindingResult bindingResult,
                                 ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("signUp");
            bindingResult.addError(validatePassword(userSignUpRequest));
            return model;
        }
        userService.saveUser(userSignUpRequest);
        model.setViewName("index");
        return model;
    }

    private ObjectError validatePassword(UserSignUpRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ObjectError("UserSignUpRequest", "Confirm password doesn't match");
        }
        return null;
    }
}
