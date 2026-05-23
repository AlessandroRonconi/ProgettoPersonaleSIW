package it.uniroma3.siw.progetto_personale_siw.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//intercetta l’errore di login e inietta un messaggio di errore nella sessione così puoi mostrarlo sul form.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession();
        // Salva il messaggio nella sessione
        session.setAttribute("loginError", "Username o password non validi. Riprova.");

        // Redirect al login (la GET /login leggerà il messaggio)
        response.sendRedirect("/login");
    }
}
