package com.example.laborator9.services;

import com.example.laborator9.models.UserDTO;
import com.example.laborator9.models.UserRole;
import com.example.laborator9.services.UserService;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("AuthorizationService")
@ApplicationScoped
@Getter
public class AuthorizationService implements Serializable {

    UserDTO currentUser = null;

    List<UserRole> roles;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init(){
        roles = Arrays.asList(UserRole.values());
    }

    public void login(UserDTO userDTO) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)
                externalContext.getRequest();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

        try {
            request.login(userDTO.getUsername(), userDTO.getPassword());
            String userRole = userService.getUserRole(userDTO);
            userDTO.setRole(UserRole.valueOf(userRole));
            currentUser = userDTO;

            if(userRole.equals("admin")) {
                facesContext.addMessage(null, new FacesMessage("Login successfully!"));
                PartialViewContext partialViewContext = facesContext.getPartialViewContext();
                partialViewContext.getRenderIds().add("menubarForm:menubar");
                externalContext.getSessionMap().put("loggedInUser", userDTO);
                navigationHandler.handleNavigation(facesContext, null, "goToUserPage");

            } else if(userRole.equals("user")) {
                facesContext.addMessage(null, new FacesMessage("Login successfully!"));
                PartialViewContext partialViewContext = facesContext.getPartialViewContext();
                partialViewContext.getRenderIds().add("menubarForm:menubar");
                externalContext.getSessionMap().put("loggedInUser", userDTO);
                navigationHandler.handleNavigation(facesContext, null, "goToUserPage");
            }
        } catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error: incorrect username or password!", "Incorrect username or password!"));
        }
    }

    public void logout() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletRequest request = (HttpServletRequest)
                    externalContext.getRequest();
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

            request.logout();
            currentUser = null;
            facesContext.addMessage(null, new FacesMessage("Logout successfully!"));
            navigationHandler.handleNavigation(facesContext, null, "goToLogin");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problem with logout!"));
        }
    }
}
