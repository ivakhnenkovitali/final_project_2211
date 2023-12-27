package by.itclass.filters;

import by.itclass.model.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Objects;

import static by.itclass.constants.JspConstants.*;


/////••−• •• •−•• − • •−• −•• •−•• •−•− −−•• •− •−• ••−•• −−• ••• − •−• •− •−− •− −• −•−− •••• −•− •− •−• −•−− ••• − •− •−•• −••− −• −•− •−
//

@WebFilter(value = {LOGIN_JSP, REGISTRATION_JSP})
public class RegisteredUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var session = ((HttpServletRequest) servletRequest).getSession();
        var user = (User) session.getAttribute(USER_ATTR);
        if (Objects.nonNull(user)) {
            var req = (HttpServletRequest) servletRequest;
            req.getRequestDispatcher(HOME_JSP).forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}





