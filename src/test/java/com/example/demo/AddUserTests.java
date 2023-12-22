package com.example.demo;

import com.example.demo.controllers.SignUpController;
import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddUserTests {
    @Mock
    private UserService userService;

    @InjectMocks
    private SignUpController signUpController;

    @Test
    void saveUser_WithValidUserSignUpRequest_ShouldSaveUserAndReturnIndexPage() {
        // Arrange
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        ModelAndView model = new ModelAndView();

        // Act
       // ModelAndView result = signUpController.saveUser(userSignUpRequest, bindingResult, model);

        // Assert
        verify(bindingResult).hasErrors();
        verify(userService).saveUser(userSignUpRequest);
    //    assertThat(result.getViewName()).isEqualTo("main-page");
    }

    @Test
    void saveUser_WithInvalidUserSignUpRequest_ShouldReturnSignUpPage() {
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ModelAndView model = new ModelAndView();

        // Act
     //   ModelAndView result = signUpController.saveUser(userSignUpRequest, bindingResult, model);

        // Assert
        verify(bindingResult).hasErrors();
        verify(userService, never()).saveUser(any());
     //   assertThat(result.getViewName()).isEqualTo("signUp");

        verify(userService, never()).saveUser(any());
    }
}
