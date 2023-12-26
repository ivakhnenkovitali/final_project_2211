package by.itclass.filters;

import by.itclass.model.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;


import java.io.IOException;
import java.util.Objects;

import static by.itclass.constants.ApplicationConstants.*;
import static by.itclass.constants.JspConstants.*;


@WebFilter(value = {MENU_CONTROLLER, ORDER_CONTROLLER,  HOME_JSP, CART_JSP,})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var session = ((HttpServletRequest)servletRequest).getSession();
        var user = (User)session.getAttribute(USER_ATTR);
        if (Objects.isNull(user)){
            var resp = (HttpServletRequest)servletRequest;

        }

    }
}
