package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;


@WebServlet("/User")
public class UserS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String pseudo = request.getParameter("pseudo");
		response.setContentType("json");
		try {
			response.getWriter().println(services.User.createUser(login, mdp, pseudo).toString());
		} catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
