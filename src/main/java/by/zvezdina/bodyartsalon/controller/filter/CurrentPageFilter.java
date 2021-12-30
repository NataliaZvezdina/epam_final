package by.zvezdina.bodyartsalon.controller.filter;

import by.zvezdina.bodyartsalon.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String currentPage = httpRequest.getRequestURL().toString();
        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        } else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null &&
                !httpRequest.getQueryString().contains("command=change_locale")) {
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
            httpRequest.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }

        filterChain.doFilter(httpRequest, servletResponse);
    }
}
