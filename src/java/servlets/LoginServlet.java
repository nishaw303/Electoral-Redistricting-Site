package servlets;

import database.Queries;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (Queries.userExists(username, password)) {
            session.setAttribute("username", username);
            response.sendRedirect("/election/map.jsp");
        } else {
            request.setAttribute("error", "true");
            response.sendRedirect("/election/login.jsp");
        }
    }

}
