package servlets;

import database.Queries;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("email")
        );

        HttpSession session = request.getSession();
        
        RequestDispatcher rs;
        if (Queries.createUser(user)) {
            request.removeAttribute("registrationError");
            rs = request.getRequestDispatcher("/map.jsp");
            rs.forward(request, response);
        } else {
            request.setAttribute("registrationError", "User with such name exists");
            rs = request.getRequestDispatcher("/register.jsp");
            rs.forward(request, response);
        }


    }

    

}
