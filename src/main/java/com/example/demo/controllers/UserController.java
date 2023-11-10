package com.example.demo.controllers;


import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.services.UserService;
import com.example.demo.services.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping(value = "/")
@AllArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping(value = "/signup")
    public ModelAndView getSignUpPage(ModelAndView model) {
        model.setViewName("signUp");
        model.addObject("userSignUpRequest", new UserSignUpRequest());
        return model;
    }

    @GetMapping("/login")
    public String signInPage(Model model, String error, String logout) {
        if (logout != null) {
            model.addAttribute("logout", true);
        }
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login"; // Змінено на "login"
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
