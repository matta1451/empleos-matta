package com.example.aplicacionempleos.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        FlashMap flashMap = new FlashMap();
        flashMap.put("login_success", "Bienvenido estimado usuario " + ((User)authentication.getPrincipal()).getUsername());
        SessionFlashMapManager flashSession = new SessionFlashMapManager();
        flashSession.saveOutputFlashMap(flashMap, request, response);
        response.sendRedirect("/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
