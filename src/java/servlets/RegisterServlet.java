package servlets;

import database.Queries;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                    request.getParameter("lastName"),
                    request.getParameter("email")  
            );
            
            if(Queries.createUser(user)) {
                
            
            }
            
            
        
        

    }

    private void register(HttpServletRequest request) {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "divny", "password");
            PreparedStatement ps = con.prepareStatement("insert into Student values(?,?,?)");
            ps.setString(1, firstName + lastName);
            ps.setString(2, email);
            ps.setString(3, password);
            int i = ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
