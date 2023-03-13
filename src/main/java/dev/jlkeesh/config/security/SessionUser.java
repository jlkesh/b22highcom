package dev.jlkeesh.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import java.util.UUID;

@Component
public class SessionUser {

    private final HttpServletRequest request;
    private final LocaleResolver localeResolver;

    public SessionUser(HttpServletRequest request,
                       LocaleResolver localeResolver) {
        this.request = request;
        this.localeResolver = localeResolver;
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }

    public Locale getLocale(){
        return localeResolver.resolveLocale(request);
    }
}
