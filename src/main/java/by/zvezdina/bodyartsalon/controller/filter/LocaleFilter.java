package by.zvezdina.bodyartsalon.controller.filter;

import by.zvezdina.bodyartsalon.command.RequestAttribute;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/controller"})
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);
        String queryString = httpRequest.getQueryString();
        String previousRequest = RequestAttribute.CONTROLLER_URL + queryString;

        if (session.getAttribute(RequestAttribute.LOCALE) == null) {
            session.setAttribute(RequestAttribute.LOCALE, Locale.getDefault());
        }

        if (queryString != null) {
            session.setAttribute(RequestAttribute.PREVIOUS_REQUEST, previousRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
