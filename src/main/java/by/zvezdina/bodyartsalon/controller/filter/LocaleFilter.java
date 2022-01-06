package by.zvezdina.bodyartsalon.controller.filter;

import by.zvezdina.bodyartsalon.controller.command.RequestAttribute;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


@WebFilter(urlPatterns = {"/jsp/*"})
public class LocaleFilter implements Filter {
    private static final String EN = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (httpRequest.getSession(false) != null
                && httpRequest.getSession(false).getAttribute(RequestAttribute.LOCALE) == null) {
            httpRequest.getSession().setAttribute(RequestAttribute.LOCALE, EN);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
